package com.wicc.bookrental.controller.bookTransaction.returns;

import com.wicc.bookrental.dto.BookTransactionDto;
import com.wicc.bookrental.service.impl.BookServiceImpl;
import com.wicc.bookrental.service.impl.BookTransactionServiceImpl;
import com.wicc.bookrental.service.impl.MemberServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/return")
public class ReturnBookController {
    private final BookServiceImpl bookService;
    private final MemberServiceImpl memberService;
    private final BookTransactionServiceImpl transactionService;

    public ReturnBookController(BookServiceImpl bookService,
                                MemberServiceImpl memberService,
                                BookTransactionServiceImpl transactionService) {
        this.bookService = bookService;
        this.memberService = memberService;
        this.transactionService = transactionService;
    }

    @GetMapping("/home")
    public String getReturnBookHomePage(Model model){
        model.addAttribute("transactionDto",new BookTransactionDto());
        model.addAttribute("transactionList",
                transactionService.findAllReturnTransaction());
        return "bookTransaction/return/returnHome";
    }

    @GetMapping("/page")
    public String getRentPage(Model model){
        model.addAttribute("transactionDtoList",new BookTransactionDto());
        model.addAttribute("rentedCode",transactionService.findAllRent());
        model.addAttribute("bookList1", bookService.findAll());
        model.addAttribute("memberList1",memberService.findAll());
        return "bookTransaction/return/returnBook";
    }

    @PostMapping("/create")
    public String showReturn(@ModelAttribute BookTransactionDto bookTransactionDto, Model model) {

        model.addAttribute("transactionDetails",
                transactionService.findTransactionByBookCode(bookTransactionDto.getBookCode()));
        return "bookTransaction/return/returnBookDetail";

    }
    @PostMapping("/add")
    public String getAddRentPage(@ModelAttribute BookTransactionDto bookTransactionDto,
                                 RedirectAttributes redirectAttributes) {
        try {
            transactionService.saveReturnTransaction(bookTransactionDto);
            System.out.println("saved successfully");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Transaction unable to complete .");
            return "redirect:/return/home";
        }
        return "redirect:/return/home";
    }
}