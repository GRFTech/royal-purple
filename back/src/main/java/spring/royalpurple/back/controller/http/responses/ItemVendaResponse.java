package spring.royalpurple.back.controller.http.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ItemVendaResponse {

    private Long id;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private Long vendaId;
    private Long produtoId;
}
