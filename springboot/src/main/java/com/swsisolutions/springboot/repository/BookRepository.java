package com.swsisolutions.springboot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swsisolutions.springboot.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // add query methods if needed
}

