package com.example.openapi.pagination.client;

import com.example.openapi.pagination.api.Clients1Api;
import com.example.openapi.pagination.api.model.PagedClient;
import com.example.openapi.pagination.api.model.Paging;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller with pageable spread across explicit query parameters (in the OpenAPI file)
 */
@RestController
@Api(value = "Clients1", description = "the Clients1 API")
public class PagingClient1Controller implements Clients1Api {

    @Autowired
    private PagingClientRepository repository;

    @Override
    public ResponseEntity<PagedClient> findAll1(@Valid Integer page, @Valid Integer size, @Valid List<String> sort) {
        Pageable pageable = pageable(page, size, sort);
        PagedClient resource = convert(repository.findAll(pageable));
        return ResponseEntity.ok(resource);
    }

    private Pageable pageable(Integer page, Integer size, List<String> sort) {
        // TODO: sort
        return PageRequest.of(page, size);
    }

    private PagedClient convert(Page<Client> page) {
        PagedClient resource = new PagedClient()
                        .items(page.get().map(it -> convert(it))
                            .collect(Collectors.toList())
                        );
        return convert(page, resource);
    }

    private <E extends Paging> E convert(Page<?> src, E dest) {
        dest
                .number(src.getNumber())
                .size(src.getSize())
                .totalElements(src.getTotalElements())
                .totalPages(src.getTotalPages());
        return dest;
    }

    private com.example.openapi.pagination.api.model.Client convert(Client client) {
        com.example.openapi.pagination.api.model.Client clientResource = new com.example.openapi.pagination.api.model.Client();
        clientResource.setId(client.getId());
        clientResource.setVersion(client.getVersion());
        clientResource.setFirstname(client.getFirstname());
        clientResource.setLastname(client.getLastname());
        return clientResource;
    }
}
