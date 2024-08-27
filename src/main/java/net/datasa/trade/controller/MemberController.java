package net.datasa.trade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.trade.domain.dto.MemberDTO;
import net.datasa.trade.service.MemberService;


@RequiredArgsConstructor
@Controller
@RequestMapping("member")
public class MemberController {
	
	private final MemberService memberservice;
	
    @GetMapping("login")
    public String login() {
        return "memberView/loginForm";
    }
    
    @GetMapping("registerForm")
    public String registerForm() {
        return "memberView/registerForm";
    }
    
    @PostMapping("register")
    public String join(@ModelAttribute MemberDTO member) {

        memberservice.register(member);
        return "redirect:/";
    }
    
    
}
