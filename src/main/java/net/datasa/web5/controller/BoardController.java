package net.datasa.web5.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web5.domain.dto.BoardDTO;
import net.datasa.web5.service.BoardService;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("board")
@Controller
public class BoardController {
	private final BoardService service;
	
	
	@GetMapping("list")
	public String list(Model model, @RequestParam("searchWord") String searchWord) {
		//서비스에서 전체 글목록 전달받음
		List<BoardDTO> boardList = service.getList();
		//글목록을 모델에 저장하고 HTML로 포워딩하여 출력
		model.addAttribute("boardList", boardList);
		
		return "boardView/list";
	}
	
	@GetMapping("write")
	public String write() {
		return "boardView/write";
	}
	
	@PostMapping("write")
	public String write(@ModelAttribute BoardDTO dto) {
		service.write(dto);
		
		
		return "redirect:/";
	}
}
