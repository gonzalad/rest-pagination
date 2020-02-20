package com.example.openapi.pagination.client;

import com.example.openapi.pagination.api.Clients2Api;
import com.example.openapi.pagination.api.model.PagedClient;
import com.example.openapi.pagination.api.model.Paging;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

/**
 * Controller with pageable spread across explicit query parameters (in the OpenAPI file)
 */
@RestController
@Api(value = "Clients2", description = "the Clients2 API")
public class PagingClient2Controller implements Clients2Api {

    @Autowired
    private PagingClientRepository repository;

    @Autowired
    private Converter<com.example.openapi.pagination.api.model.Pageable, Pageable> pageableConverter;

    @Override
    public ResponseEntity<PagedClient> findAll2(com.example.openapi.pagination.api.model.@Valid Pageable pageable) {
        PagedClient resource = convert(repository.findAll(pageableConverter.convert(pageable)));
        return ResponseEntity.ok(resource);
    }

    private PagedClient convert(Page<Client> page) {
        PagedClient resource = new PagedClient()
                .items(page.get().map(it -> convert(it))
                        .collect(Collectors.toList())
                );
        return convert(page, resource);
    }

    // can be made generic
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
