package com.example.openapi.pagination.client;

import com.example.openapi.pagination.api.Clients3Api;
import com.example.openapi.pagination.api.model.PagedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller with pageable spread across explicit query parameters (in the OpenAPI file)
 */
@RestController
public class PagingClient3Controller implements Clients3Api {

    @Autowired
    private PagingClientRepository repository;

    @Autowired
    private Converter<com.example.openapi.pagination.api.model.Pageable, Pageable> pageableConverter;

    @Override
    public ResponseEntity<PagedClient> findAll3(@Valid String range) {
        return null;
    }
}
