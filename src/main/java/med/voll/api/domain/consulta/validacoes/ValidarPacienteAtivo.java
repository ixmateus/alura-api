package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;

public class ValidarPacienteAtivo {

    private PacienteRepository repository;

    public void validar (DadosAgendamentoConsulta dados) {
        var pacienteEstaAtivo = repository.findAtivoByid(dados.idPaciente());
        if (!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído!");
        }
    }
}
