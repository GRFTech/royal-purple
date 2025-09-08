package spring.royalpurple.back.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.royalpurple.back.controller.http.requests.ItemVendaRequest;
import spring.royalpurple.back.controller.http.responses.ItemVendaResponse;
import spring.royalpurple.back.service.ItemVendaService;

import java.util.List;

@RestController
@RequestMapping("/itens-venda")
public class ItemVendaController {

    private final ItemVendaService itemVendaService;

    public ItemVendaController(ItemVendaService itemVendaService) {
        this.itemVendaService = itemVendaService;
    }

    @PostMapping
    public ResponseEntity<ItemVendaResponse> create(@Valid @RequestBody ItemVendaRequest request) {
        return ResponseEntity.ok(itemVendaService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<ItemVendaResponse>> findAll() {
        return ResponseEntity.ok(itemVendaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemVendaResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(itemVendaService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemVendaResponse> update(@PathVariable Long id,
                                                    @Valid @RequestBody ItemVendaRequest request) {
        return ResponseEntity.ok(itemVendaService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        itemVendaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
