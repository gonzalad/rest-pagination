package com.example.openapi.pagination.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class PageableConverter implements Converter<com.example.openapi.pagination.api.model.Pageable, Pageable> {

    @Override
    public Pageable convert(com.example.openapi.pagination.api.model.Pageable source) {
        // todo sort conversion
        return PageRequest.of(
                source.getPage(),
                source.getSize()
        );
    }
}
