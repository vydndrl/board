package com.beyond.board.author.repository;

import com.beyond.board.author.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyAuthorRepository extends JpaRepository<Author, Long> {
//    findBy 컬럼명의 규칙으로 자동으로 where 조건문을 사용한 쿼리 생성
//    그 외: findBy컬럼1 And 컬럼, findBy컬럼1Or컬럼2
//    findByAgeBetween(int start, int end)
//    findByAgeLessThan(int age), findByAgeGreaterThan(int age)
//    findByAgeIsNull, findByAgeIsNotNull
//    List<Author> findByNameIsNull();
//    findAllOrderByEmail
    Optional<Author> findByEmail(String email);
}
