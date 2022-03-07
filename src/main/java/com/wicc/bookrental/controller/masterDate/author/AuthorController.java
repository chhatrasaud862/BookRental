package com.wicc.bookrental.controller.masterDate.author;

import com.wicc.bookrental.dto.AuthorDto;
import com.wicc.bookrental.service.author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping("/home")
    public String openAuthorHome(Model model) {
        model.addAttribute("authorDto", new AuthorDto());
        model.addAttribute("authorList",authorService.findAll());
        return "masterData/author/authorHome";
    }
         @GetMapping("/page")
    public String openAuthorPage(Model model) {
             if(model.getAttribute("authorDto")==null)
        model.addAttribute("authorDto", new AuthorDto());
        return "masterData/author/authorPage";

    }

    @PostMapping("/create")
    public String createAuthor(@ModelAttribute AuthorDto authorDto,
                               RedirectAttributes redirectAttributes) {
        try{
            authorDto = authorService.saveAuthor(authorDto);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/author/home";
        }
        return "redirect:/author/home";
    }
    @GetMapping("/edit/{id}")
    public String editAuthor(@PathVariable("id") Integer authorId ,RedirectAttributes redirectAttributes)
    {
        AuthorDto authorDto=authorService.findById(authorId);
        if(authorDto!=null)
            redirectAttributes.addFlashAttribute("authorDto",authorDto);
        return "redirect:/author/page";
    }
    @GetMapping("/delete/{id}")
            public String deleteAuthor(@PathVariable("id") Integer authorId,
                                       RedirectAttributes redirectAttributes)
    {
        authorService.deleteAuthorById(authorId);
        return "redirect:/author/home";

    }
}