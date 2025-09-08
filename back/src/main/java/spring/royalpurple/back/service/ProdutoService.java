package spring.royalpurple.back.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.royalpurple.back.controller.http.requests.ProdutoRequest;
import spring.royalpurple.back.controller.http.responses.ProdutoResponse;
import spring.royalpurple.back.model.Produto;
import spring.royalpurple.back.model.TipoItem;
import spring.royalpurple.back.repository.ProdutoRepository;
import spring.royalpurple.back.repository.TipoItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final TipoItemRepository tipoItemRepository;

    public ProdutoService(ProdutoRepository produtoRepository, TipoItemRepository tipoItemRepository) {
        this.produtoRepository = produtoRepository;
        this.tipoItemRepository = tipoItemRepository;
    }

    @Transactional
    public ProdutoResponse create(ProdutoRequest request) {
        TipoItem tipoItem = tipoItemRepository.findById(request.getTipoItemId())
                .orElseThrow(() -> new RuntimeException("TipoItem não encontrado com id " + request.getTipoItemId()));

        Produto produto = new Produto();
        produto.setNome(request.getNome());
        produto.setPreco(request.getPreco());
        produto.setTipoItem(tipoItem);

        Produto saved = produtoRepository.save(produto);
        return toResponse(saved);
    }

    public List<ProdutoResponse> findAll() {
        return produtoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ProdutoResponse findById(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com id " + id));
        return toResponse(produto);
    }

    @Transactional
    public ProdutoResponse update(Long id, ProdutoRequest request) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com id " + id));

        TipoItem tipoItem = tipoItemRepository.findById(request.getTipoItemId())
                .orElseThrow(() -> new RuntimeException("TipoItem não encontrado com id " + request.getTipoItemId()));

        produto.setNome(request.getNome());
        produto.setPreco(request.getPreco());
        produto.setTipoItem(tipoItem);

        Produto updated = produtoRepository.save(produto);
        return toResponse(updated);
    }

    public void delete(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado com id " + id);
        }
        produtoRepository.deleteById(id);
    }

    private ProdutoResponse toResponse(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getTipoItem() != null ? produto.getTipoItem().getId() : null
        );
    }
}
