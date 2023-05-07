package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.agendamento.ConsultaRepository;
import med.voll.api.domain.consulta.agendamento.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedicoComOutraConsultaNoMesmoHorario implements ValidarAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var medicoPossuiOutraConsultaNoMesmoHorario = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
                if (medicoPossuiOutraConsultaNoMesmoHorario) {
                    throw new ValidacaoException("Médico já possui uma outra consulta agendada neste mesmo horário!");
                }
    }
}
