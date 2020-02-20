package com.example.demorestpagination.client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {

    // as an alternative to exteding from PagingAndSortingRepository,
    // you can also extend from CrudRepository and implement your own Page<Client> findAll(Pageable) method
    //    Page<Client> findAll(Pageable page);
}
