package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;

public class ValidarPacienteAtivo {

    private PacienteRepository repository;

    public void validar (DadosAgendamentoConsulta dados) {
        if (dados.idPaciente() == null) {
            return;
        }

        var pacienteAtivo = repository.findAllByAtivoTrue(dados.idPaciente());
        if (!p)
    }
}
