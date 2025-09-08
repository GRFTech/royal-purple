package spring.royalpurple.back.controller.http.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class VendaRequest {

    @NotNull(message = "A data da venda é obrigatória")
    private LocalDateTime dataVenda;

    @NotNull(message = "O valor total é obrigatório")
    private Float valorTotal;

    // IDs de produtos relacionados
    private List<Long> produtoIds;
}
