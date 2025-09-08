package spring.royalpurple.back.controller.http.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TipoItemResponse {
    private Long id;
    private String nome;
}
