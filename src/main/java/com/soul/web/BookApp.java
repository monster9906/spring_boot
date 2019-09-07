package com.soul.web;

import com.soul.domain.Books;
import com.soul.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;

/**
 * @author soul
 * @company 湖南机电--符浩灵
 * @create 2019-05-21  10:34
 */
@RestController
@RequestMapping("/api/v1")
public class BookApp {
    @Autowired
    private BookService bookService;

    /**
     * 获取所有书籍
     * @return
     */
    @GetMapping("/books")
    public List<Books> getAll(){
        return bookService.findAll();
    }

    /**
     *  新增书籍
     * @param name
     * @param des
     * @param author
     * @return
     */
    @PostMapping("/books")
    public Books save(@RequestParam String name,@RequestParam String des ,@RequestParam String author ){
        Books books = new Books();
        books.setAuthor(author);
        books.setDes(des);
        books.setName(name);
        return  bookService.save(books);
    }

    /**
     * 获取单条书籍
     * @param id
     * @return
     */
    @GetMapping("/books/{id}")
    public Books findOne(@PathVariable long id){
        return bookService.findOne(id);
    }
    /**
     * 更新某条信息
     */
    @PutMapping("/books")
    public Books putBooks( @RequestParam Long id,@RequestParam String name,@RequestParam String des ,@RequestParam String author){
        Books books = new Books();
        books.setAuthor(author);
        books.setId(id);
        books.setDes(des);
        books.setName(name);
        return  bookService.save(books);
    }
    @DeleteMapping("/books/{id}")
    public void delete(@PathVariable Long id){
        bookService.deleteOne(id);
    }

    @PostMapping("/books/by")
    public List<Books> findBy(@RequestParam int len){
        //return bookService.findByAuthor(author);
       // return bookService.findByContians(des);
        return bookService.Myquery(len);
    }

    @PostMapping("/books/update")
    public int uodateByJPQL(@RequestParam String des , @RequestParam long id){
        return bookService.updateByJPQL(des,id);
    }

}
