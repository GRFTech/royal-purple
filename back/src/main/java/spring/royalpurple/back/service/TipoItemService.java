package spring.royalpurple.back.service;

import org.springframework.stereotype.Service;
import spring.royalpurple.back.controller.http.requests.TipoItemRequest;
import spring.royalpurple.back.controller.http.responses.TipoItemResponse;
import spring.royalpurple.back.model.TipoItem;
import spring.royalpurple.back.repository.TipoItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoItemService {

    private final TipoItemRepository repository;

    public TipoItemService(TipoItemRepository repository) {
        this.repository = repository;
    }

    public TipoItemResponse create(TipoItemRequest request) {
        TipoItem tipoItem = new TipoItem(request.getNome());
        TipoItem saved = repository.save(tipoItem);
        return toResponse(saved);
    }

    public List<TipoItemResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public TipoItemResponse findById(Long id) {
        TipoItem tipoItem = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("TipoItem não encontrado com id " + id));
        return toResponse(tipoItem);
    }

    public TipoItemResponse update(Long id, TipoItemRequest request) {
        TipoItem tipoItem = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("TipoItem não encontrado com id " + id));

        tipoItem.setNome(request.getNome());
        TipoItem updated = repository.save(tipoItem);
        return toResponse(updated);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("TipoItem não encontrado com id " + id);
        }
        repository.deleteById(id);
    }

    private TipoItemResponse toResponse(TipoItem tipoItem) {
        return new TipoItemResponse(tipoItem.getId(), tipoItem.getNome());
    }
}
