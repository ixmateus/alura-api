package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import java.time.Duration;
import java.time.LocalDateTime;

public class ValidarHorarioAntecedencia {

    public void validar (DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaMinutos <30) {
            throw new ValidacaoException("Consulta deve ser agendada com pelo menos 30 minutos de antecedência!");
        }
    }
}
