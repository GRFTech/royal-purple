package spring.royalpurple.back.controller.http.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProdutoResponse {

    private Long id;
    private String nome;
    private Float preco;
    private Long tipoItemId;
}
