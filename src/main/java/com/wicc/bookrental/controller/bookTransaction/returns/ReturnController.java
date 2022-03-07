package com.wicc.bookrental.controller.bookTransaction.returns;

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
@RequestMapping("/return")
public class ReturnController {
    @Autowired
    private BookService bookService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private BookTransactionService bookTransactionService;
    @GetMapping("/home")
    public String openReturnHome(Model model)
    {
        model.addAttribute("returnList",bookTransactionService.findAllRentStatus(RentStatus.RETURN));
        return "bookTransaction/return/returnHome";
    }
    @GetMapping("/page")
    public String openReturnPage(Model model)
    {
        model.addAttribute("bookList",bookService.findAll());
        model.addAttribute("memberList",memberService.findAll());
        model.addAttribute("returnList",bookTransactionService.findAll());
        model.addAttribute("returnDto",new BookTransactionDto());
        return "bookTransaction/return/returnPage";
    }
    @PostMapping("/create")
    public String createReturn(@ModelAttribute BookTransactionDto bookTransactionDto)
    {
        bookTransactionDto.setRentStatus(RentStatus.RETURN);
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
