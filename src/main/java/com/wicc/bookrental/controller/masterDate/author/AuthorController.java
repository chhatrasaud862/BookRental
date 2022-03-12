package com.wicc.bookrental.controller.masterDate.author;

import com.wicc.bookrental.component.EmailSenderComponent;
import com.wicc.bookrental.dto.AuthorDto;
import com.wicc.bookrental.service.impl.AuthorServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/author")
public class AuthorController {
    private final EmailSenderComponent emailSenderComponent;
    private final AuthorServiceImpl authorService;

    public AuthorController(EmailSenderComponent emailSenderComponent, AuthorServiceImpl authorService) {
        this.emailSenderComponent = emailSenderComponent;
        this.authorService = authorService;
    }

    @GetMapping("/home")
    public String openAuthorHome(Model model) {
        model.addAttribute("authorDto", new AuthorDto());
        model.addAttribute("authorList", authorService.findAll());
        return "masterData/author/authorHome";
    }

    @GetMapping("/page")
    public String openAuthorPage(Model model) {
        if (model.getAttribute("authorDto") == null)
            model.addAttribute("authorDto", new AuthorDto());
        return "masterData/author/authorPage";

    }

    @PostMapping("/create")
    public String createAuthor(@ModelAttribute AuthorDto authorDto,
                                RedirectAttributes redirectAttributes
            ) {
            try {
                authorDto = authorService.saveAuthor(authorDto);
                String message = emailSenderComponent.sendEmail(authorDto);
                redirectAttributes.addFlashAttribute("messege", message);
            } catch (Exception e) {
                e.printStackTrace();
                return "redirect:/author/page";
            }
              return "redirect:/author/page";
        }

    @GetMapping("/edit/{id}")
    public String editAuthor(@PathVariable("id") Integer id,
                             RedirectAttributes redirectAttributes) {
        AuthorDto authorDto=authorService.findById(id);
        if(authorDto!=null)
            redirectAttributes.addFlashAttribute("authorDto",authorDto);
        return "redirect:/author/page";
    }
    @GetMapping("/delete/{id}")
            public String deleteAuthor(@PathVariable("id") Integer authorId)
    {
        authorService.deleteAuthorById(authorId);
        System.out.println("delete successfully");
        return "redirect:/author/home";

    }
    @GetMapping("/view/{id}")
    public String viewAuthor(@PathVariable("id")Integer id,Model model)
    {
           model.addAttribute("authorDto",authorService.findById(id));
           return "masterData/author/viewAuthor";
    }
}