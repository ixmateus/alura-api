package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.cancelamento.DadosCancelamentoConsulta;

public interface ValidarCancelamentoDeConsulta {
    void validar(DadosCancelamentoConsulta dados);
}
