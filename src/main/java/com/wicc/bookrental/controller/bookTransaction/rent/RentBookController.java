package com.wicc.bookrental.controller.bookTransaction.rent;

import com.wicc.bookrental.dto.BookTransactionDto;
import com.wicc.bookrental.service.impl.BookServiceImpl;
import com.wicc.bookrental.service.impl.BookTransactionServiceImpl;
import com.wicc.bookrental.service.impl.MemberServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.text.ParseException;

@Controller
@RequestMapping("/rent")
public class RentBookController {
    private final BookServiceImpl bookService;
    private final MemberServiceImpl memberService;
    private final BookTransactionServiceImpl transactionService;

    public RentBookController(BookServiceImpl bookService, MemberServiceImpl memberService, BookTransactionServiceImpl transactionService) {
        this.bookService = bookService;
        this.memberService = memberService;
        this.transactionService = transactionService;
    }

    @GetMapping("/home")
    public String getRentBookHomePage(Model model){
        model.addAttribute("bookTransactionDto",new BookTransactionDto());
        model.addAttribute("transactionList",transactionService.findAllRent());
        return "bookTransaction/rent/rentHome";
    }

    @GetMapping("/page")
    public String getRentPage(Model model){
        model.addAttribute("bookTransactionDto",new BookTransactionDto());
        model.addAttribute("bookList", bookService.findAll());
        model.addAttribute("memberList",memberService.findAll());
        return "bookTransaction/rent/rentbook";
    }
    @PostMapping("/create")
    public String getAddRentPage(@ModelAttribute BookTransactionDto bookTransactionDto,
                                 RedirectAttributes redirectAttributes){
        try {
             bookTransactionDto = transactionService.saveBookTransaction(bookTransactionDto);
            System.out.println("saved successfully");

        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","Transaction unable to complete .");
            return "redirect:/rent/home";
        }
        return "redirect:/rent/home";
    }

    @GetMapping("/view/{id}")
    public String getViewPage(@PathVariable("id") Integer id, Model model) throws IOException, ParseException {
        model.addAttribute("transactionList",transactionService.findById(id));
        try {
            model.addAttribute("memberDetails",memberService.findById
                    (transactionService.findById(id).getMember()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        model.addAttribute("bookDetails",
                bookService.findById(transactionService.findById(id).getBook()));
        return "bookTransaction/rent/view";
    }
}