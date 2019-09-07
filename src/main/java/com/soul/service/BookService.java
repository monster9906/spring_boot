package com.soul.service;

import com.soul.domain.BookRepository;
import com.soul.domain.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author soul
 * @company 湖南机电--符浩灵
 * @create 2019-05-21  10:34
 */
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    /**
     * 查询所有书单列表
     * @return
     */
    public List<Books> findAll() {
        return bookRepository.findAll();
    }

    public Page<Books> findAllBypage(Pageable pageable){
        return  bookRepository.findAll(pageable);
    }

    public  Books save(Books books){
        return bookRepository.save(books);
    }
    public Books findOne(Long id){
        return bookRepository.findById(id).get();
    };
    public void deleteOne(Long id){
        bookRepository.deleteById(id);
    }
    public List<Books> findByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }

    /**
     *  根据描述模糊查询
     * @param des
     * @return
     */
    public List<Books> findByContians(String des){
        return  bookRepository.findByDesContains(des);
    }

    /**
     *  自定义查询
     * @param len
     * @return
     */
    public List<Books> Myquery(int len){
        return bookRepository.findByJPQL(len);
    }

    /**
     *  自定义更新语句
     * @param des
     * @param id
     * @return
     */
    @Transactional
    public int updateByJPQL(String des , long id){
        return bookRepository.updateByJPQL(des,id);
    }

}
