package com.beyond.board.post.repository;

import com.beyond.board.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyPostRepository extends JpaRepository<Post, Long> {
//    jpql을 적용하여 네이밍룰을 통한 방식이 아닌 메서드 생성
//    select p.*, a.* from Post p left join Author a on p.author_id = a.id; (raw쿼리)
    @Query("select p from Post p left join fetch p.author")
    List<Post> findAllFetch();

//    select p.* from post p left join author a on p.author_id = a.id;
//    아래의 join 문은 author 객체를 통한 조건문으로 post를 필터링 할 때 사용
    @Query("select p from Post p left join p.author")
    List<Post> findAllLeftJoin();
}
