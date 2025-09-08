package spring.royalpurple.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.royalpurple.back.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
