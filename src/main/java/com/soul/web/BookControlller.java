package com.soul.web;

import com.soul.domain.Books;
import com.soul.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author soul
 * @company 湖南机电--符浩灵
 * @create 2019-05-24  10:40
 */
@Controller
public class BookControlller {
    @Autowired
    private BookService bookService;

 @GetMapping("/books")
    public String getAll(@PageableDefault(size = 5,sort = {"id"},direction =Sort.Direction.DESC )Pageable pageable, Model model){
         Page<Books> allBypage = bookService.findAllBypage(pageable);
         model.addAttribute("list",allBypage);
         return "books";
    }

    @GetMapping("/books/{id}")
    public String detail(@PathVariable long id, Model model){
        Books book = bookService.findOne(id);
        if( book == null){
            book = new Books();
        }
        model.addAttribute("book",book);
        return "book";
    };

    @GetMapping("/books/input")
    public String input(Model model){
        model.addAttribute("book" ,new Books());
        return "input";
    }
    @GetMapping("/books/{id}/input")
    public String edit(@PathVariable long id,Model model){
        Books book = bookService.findOne(id);
        model.addAttribute("book",book);
        return "input";
    }

    @PostMapping("/books")
    public String save(Books book, RedirectAttributes attributes){
        bookService.save(book);
        attributes.addFlashAttribute("message","《"+book.getName()+"》信息更新成功！");
        return "redirect:/books";
    }

    @RequestMapping("/exception")
    public String exception(){
        throw new RuntimeException("测试的错误信息");
    }
}
