package spring.royalpurple.back.controller.http.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class VendaResponse {
    private Long id;
    private LocalDateTime dataVenda;
    private Float valorTotal;
    private List<Long> itemVendaIds;
}
