package spring.royalpurple.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.royalpurple.back.model.TipoItem;

@Repository
public interface TipoItemRepository extends JpaRepository<TipoItem, Long> {
    TipoItem getById(Long id);
}
