package com.wicc.bookrental.controller.masterDate.book;

import com.wicc.bookrental.dto.BookDto;
import com.wicc.bookrental.service.impl.AuthorServiceImpl;
import com.wicc.bookrental.service.impl.BookServiceImpl;
import com.wicc.bookrental.service.impl.CategoryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookServiceImpl bookService;
    private final CategoryServiceImpl categoryService;
    private final AuthorServiceImpl authorService;

    public BookController(BookServiceImpl bookService, CategoryServiceImpl categoryService, AuthorServiceImpl authorService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.authorService = authorService;
    }

    @GetMapping("/home")
    public String getBookHomePage(Model model) {
        model.addAttribute("bookDto", new BookDto());
        model.addAttribute("bookList", bookService.findAll());
        return "masterData/book/bookHome";
    }

    @GetMapping("/page")
    public String getBookAddPage(Model model) {
//        if(model.getAttribute("bookDto")==null)
        model.addAttribute("bookDto", new BookDto());
        model.addAttribute("categoryList", categoryService.findAll());
        model.addAttribute("authorList", authorService.findAll());
        return "masterData/book/bookPage";
    }
    @PostMapping("/create")
    public String createBook(@ModelAttribute BookDto bookDto,
                               RedirectAttributes redirectAttributes) {
        try {
            bookDto = bookService.saveBook(bookDto);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/book/home";
        }
        return "redirect:/book/home";
    }
    @GetMapping("/edit/{id}")
        public String updateBook(@PathVariable("id")Integer id,
    RedirectAttributes redirectAttributes) throws IOException {
        BookDto bookDto=bookService.findById(id);
        if(bookDto!=null)
            redirectAttributes.addFlashAttribute("bookDto",bookDto);
        return "redirect:/book/page";
    }
    @GetMapping("/view/{id}")
    public String getViewPage(@PathVariable("id") Integer id, Model model) throws IOException {
        System.out.println(bookService.findById(id).getCategory().getId());
        model.addAttribute("bookDetail", bookService.findById(id));
        model.addAttribute("categoryData", categoryService.findById(bookService.findById(id).getCategory().getId()));
        model.addAttribute("authorData", bookService.findById(id).getAuthorList());
        model.addAttribute("coverPhotoPath",bookService.findById(id).getCoverPhotoPath());
        return "masterData/book/viewBook";
    }
    @GetMapping("/delete/{id}")
    public String getDelete(@PathVariable("id") Integer id) throws IOException {
        bookService.deleteBookById(id);
        return "redirect:/book/home";
    }
}
