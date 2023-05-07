package med.voll.api.domain.consulta.validacoes;


import med.voll.api.domain.consulta.agendamento.DadosAgendamentoConsulta;

public interface ValidarAgendamentoDeConsulta {

    void validar (DadosAgendamentoConsulta dados);
}
