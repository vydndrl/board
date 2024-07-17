package com.example.board.author.repository;

import com.example.board.author.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyAuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByEmail(String email);
    List<Author> findByName(String name);
}
