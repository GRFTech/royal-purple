package spring.royalpurple.back.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.royalpurple.back.controller.http.requests.ItemVendaRequest;
import spring.royalpurple.back.controller.http.responses.ItemVendaResponse;
import spring.royalpurple.back.model.ItemVenda;
import spring.royalpurple.back.model.Produto;
import spring.royalpurple.back.model.Venda;
import spring.royalpurple.back.repository.EstoqueRepository;
import spring.royalpurple.back.repository.ItemVendaRepository;
import spring.royalpurple.back.repository.ProdutoRepository;
import spring.royalpurple.back.repository.VendaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemVendaService {

    private final ItemVendaRepository itemVendaRepository;
    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;
    private final EstoqueRepository estoqueRepository;

    @Transactional
    public ItemVendaResponse create(ItemVendaRequest request) {
        Venda venda = vendaRepository.findById(request.getVendaId())
                .orElseThrow(() -> new RuntimeException("Venda não encontrada com id " + request.getVendaId()));

        Produto produto = produtoRepository.findById(request.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com id " + request.getProdutoId()));

        ItemVenda itemVenda = new ItemVenda();
        itemVenda.setQuantidade(request.getQuantidade());
        itemVenda.setPrecoUnitario(request.getPrecoUnitario());
        itemVenda.setVenda(venda);
        itemVenda.setProduto(produto);

        var e = estoqueRepository.findByProdutoId(request.getProdutoId());

        e.setQuantidade(e.getQuantidade() - itemVenda.getQuantidade());
        estoqueRepository.save(e);

        ItemVenda saved = itemVendaRepository.save(itemVenda);
        return toResponse(saved);
    }

    public List<ItemVendaResponse> findAll() {
        return itemVendaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ItemVendaResponse findById(Long id) {
        ItemVenda itemVenda = itemVendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ItemVenda não encontrado com id " + id));
        return toResponse(itemVenda);
    }

    @Transactional
    public ItemVendaResponse update(Long id, ItemVendaRequest request) {
        ItemVenda itemVenda = itemVendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ItemVenda não encontrado com id " + id));

        Venda venda = vendaRepository.findById(request.getVendaId())
                .orElseThrow(() -> new RuntimeException("Venda não encontrada com id " + request.getVendaId()));

        Produto produto = produtoRepository.findById(request.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com id " + request.getProdutoId()));

        itemVenda.setQuantidade(request.getQuantidade());
        itemVenda.setPrecoUnitario(request.getPrecoUnitario());
        itemVenda.setVenda(venda);
        itemVenda.setProduto(produto);

        ItemVenda updated = itemVendaRepository.save(itemVenda);
        return toResponse(updated);
    }

    public void delete(Long id) {
        if (!itemVendaRepository.existsById(id)) {
            throw new RuntimeException("ItemVenda não encontrado com id " + id);
        }
        itemVendaRepository.deleteById(id);
    }

    private ItemVendaResponse toResponse(ItemVenda itemVenda) {
        return new ItemVendaResponse(
                itemVenda.getId(),
                itemVenda.getQuantidade(),
                itemVenda.getPrecoUnitario(),
                itemVenda.getVenda() != null ? itemVenda.getVenda().getId() : null,
                itemVenda.getProduto() != null ? itemVenda.getProduto().getId() : null
        );
    }
}
