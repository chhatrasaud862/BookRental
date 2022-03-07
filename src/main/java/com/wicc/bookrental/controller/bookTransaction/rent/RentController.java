package com.wicc.bookrental.controller.bookTransaction.rent;

import com.wicc.bookrental.dto.BookTransactionDto;
import com.wicc.bookrental.enums.RentStatus;
import com.wicc.bookrental.service.book.BookService;
import com.wicc.bookrental.service.bookTransaction.BookTransactionService;
import com.wicc.bookrental.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rent")
public class RentController {
    @Autowired
    public BookService bookService;
    @Autowired
    public MemberService memberService;
    @Autowired
    public BookTransactionService bookTransactionService;
    @GetMapping("/home")
    public String openHomeRent(Model model){
        model.addAttribute("rentList", bookTransactionService.findAllRentStatus(RentStatus.RENT));
        return "bookTransaction/rent/rentHome";
    }

    @GetMapping("/page")
    public String openPageRent(Model model){
        model.addAttribute("bookList", bookService.findAll());
        model.addAttribute("memberList", memberService.findAll());
        model.addAttribute("rentDto", new BookTransactionDto());
        return "bookTransaction/rent/rentPage";
    }
    @PostMapping("/create")
    public String createRent(@ModelAttribute
                                         BookTransactionDto bookTransactionDto){
        bookTransactionDto.setRentStatus(RentStatus.RENT);
       bookTransactionService.saveBookTransaction(bookTransactionDto);
        return "redirect:/rent/home";
    }

    @GetMapping("edit/{id}")
    public String updateRent(@PathVariable("id")Integer id, Model model){
        model.addAttribute("rentDto", bookTransactionService.findById(id));
        model.addAttribute("listBook", bookService.findAll());
        model.addAttribute("listMember", memberService.findAll());
        return "redirect:/rent/page";
    }

    @GetMapping("delete/{id}")
    public String deleteRent(@PathVariable("id")Integer id){
        bookTransactionService.deleteBookTransactionById(id);
        return "redirect:/rent/home";
    }
}