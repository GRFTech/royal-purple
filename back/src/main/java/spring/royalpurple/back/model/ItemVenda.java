package spring.royalpurple.back.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table
@Entity(name = "ITEM_VENDA_TB")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precoUnitario;

    @ManyToOne
    @JoinColumn(name = "venda_id")
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
}
