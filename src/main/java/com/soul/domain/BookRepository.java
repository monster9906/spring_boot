package com.soul.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author soul
 * @company 湖南机电--符浩灵
 * @create 2019-05-21  10:32
 */
public interface BookRepository extends JpaRepository<Books,Long> {

    // 分页查询
    Page<Books> findAll(Pageable pageable);

    List<Books> findByAuthor(String author);

    List<Books> findByDesContains(String des);

    @Query("select  b from Books b where  length(b.author ) > ?1 ")
    List<Books> findByJPQL(int len);

    @Modifying
    @Query("update  Books b set b.des = ?1 where b.id = ?2")
    int updateByJPQL(String des,long id);
}
