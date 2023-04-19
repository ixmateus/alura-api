package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import java.time.DayOfWeek;

public class ValidarHorarioFuncionamentoClinica {

    public void validar (DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var deposDoEncerramentoDaClinica = dataConsulta.getHour() > 18;

        if (domingo || antesDaAberturaDaClinica || deposDoEncerramentoDaClinica) {
             throw new ValidacaoException("Consulta marcada fora do horario de funcionamento!");
        }
    }
}
