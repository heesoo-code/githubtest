package net.datasa.trade.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.datasa.trade.security.AuthenticatedUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.trade.domain.dto.BoardDTO;
import net.datasa.trade.service.BoardService;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("board")
public class BoardController {

	private final BoardService boardService;

	@GetMapping("list")
	public String listAll(Model model) {
		List<BoardDTO> boardList = boardService.getListAll();
		model.addAttribute("boardList", boardList);
		return "boardView/list";
	}

	@GetMapping("write")
	public String write() {
		return "boardView/writeForm";
	}

	@PostMapping("write")
	public String write(@ModelAttribute BoardDTO boardDTO, @AuthenticationPrincipal AuthenticatedUser user) {

		boardDTO.setMemberId(user.getUsername());

		try {
			boardService.write(boardDTO);
			return "redirect:list";
		} catch (Exception e) {
			e.printStackTrace();
			return "boardView/writeForm";
		}
	}

	@GetMapping("read")
	public String read(Model model, @RequestParam("boardNum") int boardNum) {
		log.debug("조회할 글번호 : {}", boardNum);

		try {
			BoardDTO boardDTO = boardService.getBoard(boardNum);

			model.addAttribute("board", boardDTO);
			return "boardView/read";
		} catch (Exception e) {
			return "redirect:list";
		}

	}

	@GetMapping("delete")
	public String delete(@RequestParam("boardNum") int boardNum, @AuthenticationPrincipal AuthenticatedUser user) {

		try {
			boardService.delete(boardNum);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:list";
	}

}
