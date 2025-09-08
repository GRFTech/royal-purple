package spring.royalpurple.back.controller.http.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstoqueRequest {

    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 0, message = "A quantidade não pode ser negativa")
    private Long quantidade;

    @NotNull(message = "O produto é obrigatório")
    private Long produtoId;
}
