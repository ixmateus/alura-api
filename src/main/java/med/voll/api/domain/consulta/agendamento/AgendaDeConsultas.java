package med.voll.api.domain.consulta.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.cancelamento.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.validacoes.ValidarAgendamentoDeConsulta;
import med.voll.api.domain.consulta.validacoes.ValidarCancelamentoDeConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidarAgendamentoDeConsulta> validadores;

    @Autowired
    private List<ValidarCancelamentoDeConsulta> validarCancelamento;


    public DadosDetalhamentoConsulta agendar (DadosAgendamentoConsulta dados) {
        if(!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("id do paciente não encontrado!");
        }
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("id do medico não encontrado!");
        }

        validadores.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        if (medico == null) {
            throw new ValidacaoException("Não possui nenhum médico disponível nesta data!");
        }
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);

    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade obrigatoria!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public void cancelar (DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("id da consulta informado não existe!");
        }

        validarCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }
}
