package spring.royalpurple.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.royalpurple.back.model.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
}
