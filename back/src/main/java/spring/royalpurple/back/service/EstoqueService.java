package spring.royalpurple.back.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.royalpurple.back.controller.http.requests.EstoqueRequest;
import spring.royalpurple.back.controller.http.responses.EstoqueResponse;
import spring.royalpurple.back.model.Estoque;
import spring.royalpurple.back.model.Produto;
import spring.royalpurple.back.repository.EstoqueRepository;
import spring.royalpurple.back.repository.ProdutoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final ProdutoRepository produtoRepository;

    public EstoqueService(EstoqueRepository estoqueRepository, ProdutoRepository produtoRepository) {
        this.estoqueRepository = estoqueRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public EstoqueResponse create(EstoqueRequest request) {
        Produto produto = produtoRepository.findById(request.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com id " + request.getProdutoId()));

        Estoque estoque = new Estoque();
        estoque.setQuantidade(request.getQuantidade());
        estoque.setProduto(produto);

        return toResponse(estoqueRepository.save(estoque));
    }

    public List<EstoqueResponse> findAll() {
        return estoqueRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public EstoqueResponse findById(Long id) {
        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado com id " + id));
        return toResponse(estoque);
    }

    @Transactional
    public EstoqueResponse update(Long id, EstoqueRequest request) {
        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado com id " + id));

        Produto produto = produtoRepository.findById(request.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com id " + request.getProdutoId()));

        estoque.setQuantidade(request.getQuantidade());
        estoque.setProduto(produto);

        Estoque updated = estoqueRepository.save(estoque);
        return toResponse(updated);
    }

    public void delete(Long id) {
        if (!estoqueRepository.existsById(id)) {
            throw new RuntimeException("Estoque não encontrado com id " + id);
        }
        estoqueRepository.deleteById(id);
    }

    private EstoqueResponse toResponse(Estoque estoque) {
        return new EstoqueResponse(
                estoque.getId(),
                estoque.getQuantidade(),
                estoque.getProduto().getId()
        );
    }
}
