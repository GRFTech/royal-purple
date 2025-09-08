package spring.royalpurple.back.controller.http.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoItemRequest {
    @NotBlank(message = "O nome não pode estar vazio")
    private String nome;
}
