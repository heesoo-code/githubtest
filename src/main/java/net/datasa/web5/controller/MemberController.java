package net.datasa.web5.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web5.domain.dto.MemberDTO;
import net.datasa.web5.security.AuthenticatedUser;
import net.datasa.web5.service.MemberService;

//회원정보 관련 콘트롤러

@Slf4j
@RequiredArgsConstructor
@RequestMapping("member")
@Controller
public class MemberController {
	
	private final MemberService Memberservice;
	//회원 가입 양식으로 이동
	@GetMapping({"joinForm"})
	public String join() {
		return "memberView/joinForm";
	}
	
	@PostMapping("join")
	public String join(@ModelAttribute MemberDTO dto) {
		log.debug("전달된 회원정보 : {}", dto);
		Memberservice.join(dto);
		
		return "redirect:/";
	}
	
	@GetMapping("idCheck")
	public String idCheck() {
		return "memberView/idCheck";
	}
	
	@PostMapping("idCheck")
	public String idCheck(Model model, @RequestParam("Id") String Id) {
		
		log.debug("검색할 아아디 : {}", Id);
		
		
		boolean result = Memberservice.search(Id);
		
		model.addAttribute("Id", Id);
		model.addAttribute("result", result);
		
		return "memberView/idCheck";
	}
	
	@GetMapping("loginForm")
	public String loginForm() {
		return "memberView/loginForm";
	}

	
	@GetMapping("info")
	public String info(Model model, @AuthenticationPrincipal AuthenticatedUser user) {
		//현재 사용자의 아이디를 서비스로 전다랗여 해당 사용자 정보를 MemberDTO객체로 리턴받는다
		MemberDTO memberDTO = Memberservice.getMember(user.getUsername());
		//memberDTO객체를 모델에 저장하고 HTML폼으로 이동
		model.addAttribute("member", memberDTO);
		return "memberView/info";
	}
	@PostMapping("info")
	public String info(@AuthenticationPrincipal AuthenticatedUser user, @ModelAttribute MemberDTO memberDTO) {
		//수정폼에서 전달한 값들을 MemberDTO로 받는다.
		//현재 로그인한 사용자의 아이디를 MemberDTO객체에 추가한다.
		memberDTO.setMemberId(user.getUsername());
		//MemberDTO객체를 서비스로 전달하여 DB를 수정한다.
		Memberservice.update(memberDTO);
		//메인화면으로 리다이렉트한다.
		return "redirect:/";
	}
	
	
	
	

	
	
	
	
}
