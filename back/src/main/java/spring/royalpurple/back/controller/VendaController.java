package spring.royalpurple.back.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.royalpurple.back.controller.http.requests.VendaRequest;
import spring.royalpurple.back.controller.http.responses.VendaResponse;
import spring.royalpurple.back.service.VendaService;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @PostMapping
    public ResponseEntity<VendaResponse> create(@Valid @RequestBody VendaRequest request) {
        return ResponseEntity.ok(vendaService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<VendaResponse>> findAll() {
        return ResponseEntity.ok(vendaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(vendaService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendaResponse> update(@PathVariable Long id,
                                                @Valid @RequestBody VendaRequest request) {
        return ResponseEntity.ok(vendaService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vendaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
