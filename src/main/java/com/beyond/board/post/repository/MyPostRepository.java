package com.beyond.board.post.repository;

import com.beyond.board.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyPostRepository extends JpaRepository<Post, Long> {
}
