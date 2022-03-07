package com.wicc.bookrental.controller.masterDate.book;

import com.wicc.bookrental.dto.AuthorDto;
import com.wicc.bookrental.dto.BookDto;
import com.wicc.bookrental.dto.CategoryDto;
import com.wicc.bookrental.service.author.AuthorService;
import com.wicc.bookrental.service.book.BookService;
import com.wicc.bookrental.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AuthorService authorService;
    @GetMapping("/home")
    public String openBookHome(Model model)
    {
        model.addAttribute("bookDto",new BookDto());
        model.addAttribute("bookList",bookService.findAll());
        return "masterData/book/bookHome";
    }
    @GetMapping("/page")
    public String openBookPage(Model model)
    {
        model.addAttribute("bookDto",new BookDto());
        model.addAttribute("categoryList",categoryService.findAll());
        model.addAttribute("authorList",authorService.findAll());
        model.addAttribute("bookDtoList",bookService.findAll());
        return "masterData/book/bookPage";
    }
    @GetMapping("/create")
    public String createBook(@ModelAttribute BookDto bookDto,
                             RedirectAttributes redirectAttributes) throws IOException {
      try {
          bookDto = bookService.saveBook(bookDto);
      }catch (Exception e)
      {
          e.printStackTrace();
          return "redirect:/book/page";
      }
      return "redirect:/book/page";
    }
    @GetMapping("/view/{id}")
    public String viewBook(@PathVariable("id") Integer id,Model model) throws IOException {
        model.addAttribute("bookDto",bookService.findById(id));
        return "masterData/book/viewBook";
    }
    @GetMapping("/delete/{id}")
    public String deleteBookId(@PathVariable("id")Integer bookId)
    {
        bookService.deleteBookById(bookId);
        return "redirect:/book/home";
    }
}
