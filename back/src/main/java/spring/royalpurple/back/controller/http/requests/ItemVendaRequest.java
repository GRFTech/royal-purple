package spring.royalpurple.back.controller.http.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemVendaRequest {

    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
    private Integer quantidade;

    @NotNull(message = "O preço unitário é obrigatório")
    @Min(value = 0, message = "O preço unitário não pode ser negativo")
    private BigDecimal precoUnitario;

    @NotNull(message = "A venda é obrigatória")
    private Long vendaId;

    @NotNull(message = "O produto é obrigatório")
    private Long produtoId;
}
