package spring.royalpurple.back.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table
@Entity(name = "TIPO_ITEM_TB")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "tipoItem", cascade = CascadeType.ALL)
    private List<Produto> produtoList;

    public TipoItem(String nome) {
        this.nome = nome;
        this.id = null;
        this.produtoList = null;
    }
}
