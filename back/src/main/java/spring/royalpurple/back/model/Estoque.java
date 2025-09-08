package spring.royalpurple.back.model;

import jakarta.persistence.*;
import lombok.*;

@Table
@Entity(name = "ESTOQUE_TB")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long quantidade;

    @OneToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
}
