package org.zerock.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.service.BoardService;
import org.zerock.vo.BoardVO;

@Controller
@RequestMapping("/board/*") // 컨트롤러 자체의 매핑 주소 board 등록
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/board_list", method = RequestMethod.GET) // get으로 접근하는 매핑 주소 board_list
	public String board_list(Model m, HttpServletRequest request, @ModelAttribute BoardVO b) throws Exception {

		/* 페이징 */
		int page = 1;// 현재 쪽번호
		int limit = 10; // 한 페이지에 보여지는 목록개수
		if (request.getParameter("page") != null) {
			// get으로 전달된 쪽번호가 있는 경우 실행
			page = Integer.parseInt(request.getParameter("page"));
			// get으로 전달된 쪽번호를 정수숫자로 바꿔서 저장함.
		}

		b.setStartrow((page - 1) * 10 + 1);// 시작행 번호
		b.setEndrow(b.getStartrow() + limit - 1);// 끝행 번호
		int totalCount = this.boardService.getCount();
		// 총 게시물 수
		List<BoardVO> blist = this.boardService.getList(b);

		/* 페이징 */
		// 총 페이지 수
		int maxpage = (int) ((double) totalCount / limit + 0.95);
		// 현재페이지에 보여질 시작페이지
		int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		// 현재페이지에 보여질 마지막 페이지
		int endpage = maxpage;
		if (endpage > startpage + 10 - 1)
			endpage = startpage + 10 - 1;

		m.addAttribute("totalCount", totalCount);
		// totalCount 키 이름에 totalCount 즉 총 레코드 개수를 저장
		m.addAttribute("blist", blist);
		m.addAttribute("startpage", startpage);
		m.addAttribute("endpage", endpage);
		m.addAttribute("maxpage", maxpage);
		m.addAttribute("page", page);// page키이름에 현재 쪽 번호 저장
		return "board/board_list"; // 뷰 리졸브=> WEB-INF/views/board/board_list.jsp
	}

	// 게시판 글쓰기 폼
	@RequestMapping(value = "/board_write", method = RequestMethod.GET)
	public String board_write(HttpServletRequest request) {
		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		request.setAttribute("page", page);// page키 이름에 쪽번호를 저장

		return "board/board_write";
	}

	// 게시판 저장
	@RequestMapping(value = "/board_write_ok", method = RequestMethod.POST)
	// post로 접근하는 매핑주소를 처리
	public String board_write_ok(BoardVO b, RedirectAttributes rttr) throws Exception {
		/*
		 * BoardVO b는 board_write.jsp의 네임 파라미터 이름과 BoardVO.java변수명이 일치하면 b객체에 글쓴이, 제목,
		 * 내용이 저장되어있다.
		 */

		this.boardService.insertBoard(b); // 게시판 저장
		rttr.addFlashAttribute("msg", "SUCCESS");
		// msg이름에 SUCCESS 문자열을 담아서 다른 매핑 주소로 전달
		// 창에 값 노출이 안되어서 보안이 좋다.

		return "redirect:/board/board_list";
		/*
		 * GET 방식으로 다른 매핑주소인 /board/board_list즉 게시판 목록으로 이동시킴
		 */
	}

	// 게시물 내용 보기
	@GetMapping("/board_cont")
	// board_cont 매핑 주소 등록
	public ModelAndView board_cont(@RequestParam("bno") int bno, int page) throws Exception {
		/*
		 * @RequestParam("bno") int bno는 서블릿 컨트롤의 request.getParameter("bno")와 기능이 같다. 즉
		 * bno파라미터이름에 get이름으로 전달된 번호값을 가져와 bno 매개변수에 저장. 이것을 줄여서 int bno로 사용해도 같은 효과가
		 * 난다.
		 */

		BoardVO b = this.boardService.getCont(bno);

		ModelAndView cm = new ModelAndView();
		cm.addObject("b", b); // b키 이름에 b객체를 저장
		cm.setViewName("board/board_cont");// 뷰 리졸브 경로 설정
		cm.addObject("page",page); //페이지 키이름에 쪽번호 저장
		return cm;
	}

	// 게시물 수정폼
	@GetMapping("/board_edit")
	public String board_edit(int bno, Model m, int page) {
		/*
		 * board_edit?bno=번호 형태의 get방식으로 전달된 번호값은 int bno 매개변수에서 받아서 처리
		 */
		BoardVO eb = this.boardService.getCont2(bno);// 번호에 해당하는 내용을 오라클로부터 가져옴
		m.addAttribute("eb", eb); // eb키 이름에 eb객체를 저장
		m.addAttribute("page",page);

		return "board/board_edit";
	}

	@PostMapping("/board_edit_ok") // get or post로 전달되는 매핑주소를 처리
	public String board_edit_ok(@ModelAttribute BoardVO eb, RedirectAttributes rttr,int page) throws Exception {
		/*
		 * @modelattribute boardVo eb는 board_edit.jsp의 네임파라미터 이름과 boardVO.java의 변수명이
		 * 일치하면 eb객체에 수정한 글쓴이,제목,내용이 저장되어 있다. 동시에 히든 번호값도 저장됨.
		 */
		this.boardService.editBoard(eb);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/board/board_list?page="+page;
	}

	/*
	 * HttpServletRequest는 서블릿자바에서 사용자 폼에서 입력한 값을
	 */

	@RequestMapping("/board_del")
	public ModelAndView board_del(HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		int bno = Integer.parseInt(request.getParameter("bno"));
		// get으로 전달된 번호값을 정수 숫자로 바꿔서 저장.
		this.boardService.delBoard(bno); // 게시물 삭제
		rttr.addFlashAttribute("msg", "SUCCESS");
		return new ModelAndView("redirect:/board/board_list");
	}
}
