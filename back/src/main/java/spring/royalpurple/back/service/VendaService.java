package spring.royalpurple.back.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.royalpurple.back.controller.http.requests.VendaRequest;
import spring.royalpurple.back.controller.http.responses.VendaResponse;
import spring.royalpurple.back.model.Produto;
import spring.royalpurple.back.model.Venda;
import spring.royalpurple.back.repository.ProdutoRepository;
import spring.royalpurple.back.repository.VendaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;

    public VendaService(VendaRepository vendaRepository, ProdutoRepository produtoRepository) {
        this.vendaRepository = vendaRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public VendaResponse create(VendaRequest request) {
        Venda venda = new Venda();
        venda.setDataVenda(request.getDataVenda());
        venda.setValorTotal(request.getValorTotal());

        if (request.getProdutoIds() != null && !request.getProdutoIds().isEmpty()) {
            List<Produto> produtos = produtoRepository.findAllById(request.getProdutoIds());
            venda.setVendas(produtos);
        }

        Venda saved = vendaRepository.save(venda);
        return toResponse(saved);
    }

    public List<VendaResponse> findAll() {
        return vendaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public VendaResponse findById(Long id) {
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada com id " + id));
        return toResponse(venda);
    }

    @Transactional
    public VendaResponse update(Long id, VendaRequest request) {
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada com id " + id));

        venda.setDataVenda(request.getDataVenda());
        venda.setValorTotal(request.getValorTotal());

        if (request.getProdutoIds() != null) {
            List<Produto> produtos = produtoRepository.findAllById(request.getProdutoIds());
            venda.setVendas(produtos);
        }

        Venda updated = vendaRepository.save(venda);
        return toResponse(updated);
    }

    public void delete(Long id) {
        if (!vendaRepository.existsById(id)) {
            throw new RuntimeException("Venda não encontrada com id " + id);
        }
        vendaRepository.deleteById(id);
    }

    private VendaResponse toResponse(Venda venda) {
        List<Long> produtoIds = venda.getVendas() != null
                ? venda.getVendas().stream().map(Produto::getId).collect(Collectors.toList())
                : null;

        return new VendaResponse(
                venda.getId(),
                venda.getDataVenda(),
                venda.getValorTotal(),
                produtoIds
        );
    }
}
