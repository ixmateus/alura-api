package med.voll.api.domain.paciente;

import jakarta.validation.Valid;

public record DadosAtualizacaoPaciente<DadosAtualizacaoEndereco>(
        Long id,
        String nome,
        String telefone,
        @Valid DadosAtualizacaoEndereco endereco
) {
}