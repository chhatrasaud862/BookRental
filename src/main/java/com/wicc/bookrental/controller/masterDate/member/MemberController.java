package com.wicc.bookrental.controller.masterDate.member;
import com.wicc.bookrental.dto.MemberDto;
import com.wicc.bookrental.service.impl.MemberServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member")
public class MemberController {
    private final MemberServiceImpl memberService;

    public MemberController(MemberServiceImpl memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/home")
    public String openMemberHome(Model model)
    {
        model.addAttribute("memberDto",new MemberDto());
        model.addAttribute("memberList",memberService.findAll());
        return "masterData/member/memberHome";
    }
    @GetMapping("/page")
    public String openPage(Model model)
    {
        if(model.getAttribute("memberDto")==null)
        model.addAttribute("memberDto",new MemberDto());
        return "masterData/member/memberPage";
    }
    @PostMapping("/create")
    public String createMember(@ModelAttribute MemberDto memberDto,
                               RedirectAttributes redirectAttributes)
    {
        try{
           memberDto=memberService.saveMember(memberDto);
        }catch (Exception e)
        {
            e.printStackTrace();
            return "redirect:/member/home";
        }
        return "redirect:/member/home";
    }
    @GetMapping("edit/{id}")
    public String editMemberPage(@PathVariable("id")Integer id,
                                 RedirectAttributes redirectAttributes)
    {
        MemberDto memberDto=memberService.findById(id);
        if(memberDto!=null)
            redirectAttributes.addFlashAttribute("memberDto",memberDto);
        return "redirect:/member/page";
    }
    @GetMapping("delete/{id}")
    public String deleteMemberId(@PathVariable("id")Integer memberId)
    {
        memberService.deleteMemberById(memberId);
        return "redirect:/member/home";
    }
    @GetMapping("/view/{id}")
    public String viewMember(@PathVariable("id")Integer id,Model model)
    {
        model.addAttribute("memberDto",memberService.findById(id));
        return "masterData/member/viewMember";
    }
}
