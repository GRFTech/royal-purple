package spring.royalpurple.back.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table
@Entity(name = "PRODUTO_TB")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Float preco;

    @ManyToOne
    @JoinColumn(name = "tipo_item_id")
    private TipoItem tipoItem;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<ItemVenda> itemVendaList;
}
