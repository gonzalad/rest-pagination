package com.example.openapi.pagination.client;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagingClientRepository extends PagingAndSortingRepository<Client, Long> {
}
