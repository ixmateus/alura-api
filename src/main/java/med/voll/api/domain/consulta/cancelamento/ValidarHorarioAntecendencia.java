package med.voll.api.domain.consulta.cancelamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.agendamento.ConsultaRepository;
import med.voll.api.domain.consulta.validacoes.ValidarCancelamentoDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidarHorarioAntecedenciaCancelamento")
public class ValidarHorarioAntecendencia implements ValidarCancelamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DadosCancelamentoConsulta dados) {
        var consulta  = repository.getReferenceById(dados.idConsulta());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("Consulta somente pode ser cancelada com antecedência mínima de 24h!");
        }
    }
}
