package com.securityaws.apirest.repositories;


import com.securityaws.apirest.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books, Long> {
}
