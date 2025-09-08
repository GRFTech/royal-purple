package spring.royalpurple.back.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.royalpurple.back.controller.http.requests.VendaRequest;
import spring.royalpurple.back.controller.http.responses.VendaResponse;
import spring.royalpurple.back.model.ItemVenda;
import spring.royalpurple.back.model.Venda;
import spring.royalpurple.back.repository.ItemVendaRepository;
import spring.royalpurple.back.repository.VendaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ItemVendaRepository itemVendaRepository;

    public VendaService(VendaRepository vendaRepository,
                        ItemVendaRepository itemVendaRepository) {
        this.vendaRepository = vendaRepository;
        this.itemVendaRepository = itemVendaRepository;
    }

    @Transactional
    public VendaResponse create(VendaRequest request) {
        Venda venda = new Venda();
        venda.setDataVenda(request.getDataVenda());
        venda.setValorTotal(request.getValorTotal());

        venda.setItemVendaList(new ArrayList<>());

        Venda saved = vendaRepository.save(venda);

        if (request.getItemVendaIds() != null && !request.getItemVendaIds().isEmpty()) {
            for (Long itemId : request.getItemVendaIds()) {
                ItemVenda item = itemVendaRepository.findById(itemId)
                        .orElseThrow(() -> new RuntimeException("Item de venda não encontrado com id " + itemId));

                item.setVenda(saved);
                itemVendaRepository.save(item);

                saved.getItemVendaList().add(item);
            }
        }

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

        if (request.getItemVendaIds() != null) {
            List<ItemVenda> itemsToRemove = venda.getItemVendaList().stream()
                    .filter(item -> !request.getItemVendaIds().contains(item.getId()))
                    .collect(Collectors.toList());
            itemVendaRepository.deleteAll(itemsToRemove);

            List<ItemVenda> newItems = request.getItemVendaIds().stream()
                    .map(itemId -> itemVendaRepository.findById(itemId)
                            .orElseThrow(() -> new RuntimeException("ItemVenda não encontrado com id " + itemId)))
                    .toList();

            newItems.forEach(item -> item.setVenda(venda));

            venda.getItemVendaList().clear();
            venda.getItemVendaList().addAll(newItems);
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
        return new VendaResponse(
                venda.getId(),
                venda.getDataVenda(),
                venda.getValorTotal(),
                venda.getItemVendaList() != null
                        ? venda.getItemVendaList().stream()
                        .map(ItemVenda::getId)
                        .collect(Collectors.toList())
                        : null
        );
    }
}
