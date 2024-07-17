package com.example.board.author.repository;

import com.example.board.author.domain.Author;
import com.example.board.author.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyPostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findById (Long id);
    List<Post> findByTitle(String title);
}
