package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.Endereco;

public record AtualizarDadosMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        Endereco endereco) {
}
