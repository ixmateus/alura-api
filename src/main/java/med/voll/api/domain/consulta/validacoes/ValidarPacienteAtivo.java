package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.agendamento.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarPacienteAtivo implements ValidarAgendamentoDeConsulta {

    @Autowired
    private PacienteRepository repository;

    public void validar (DadosAgendamentoConsulta dados) {
        var pacienteEstaAtivo = repository.findAtivoByid(dados.idPaciente());
        if (!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído!");
        }
    }
}
