package org.zerock.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.service.AdminGongjiService;
import org.zerock.vo.GongjiVO;

@Controller
public class AdminGongjiController {

	@Autowired
	private AdminGongjiService adminGongjiService;
	
	@GetMapping("/admin_gongji_write")//get으로 접근하는 매핑주소를 등록
	public ModelAndView admin_gongji_write(
			HttpServletResponse response, HttpSession session) throws Exception {
		
		response.setContentType("text/html; charset=UTF-8");
		//웹 브라우저에 응답하는 문자/태그, 언어코딩 타입을 설정
		PrintWriter out=response.getWriter();
		
		String admin_id=(String)session.getAttribute("admin_id");
		//명시적인 다운캐스팅 하면서 관리자 세션 아이디값을 구함
		
		if(admin_id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='admin_login';");
			out.println("</script>");
		} else { //관리자가 로그인 상태
			ModelAndView gm = new ModelAndView("admin/admin_gongji_write");
			
			return gm;
		}
		return null;
	}
	
	//관리자 공지 저장
	@PostMapping("/admin_gongji_write_ok")
	public String admin_gongji_write_ok(
			GongjiVO g, HttpServletResponse response, HttpSession session) throws Exception {
		
		/*admin_gongji_write_jsp의 네임 파라미터 이름과 GongjiVO.java의 변수명이 일치하면
		 * GongjiVO g에서 g객체에 작성자, 제목, 비번, 내용이 함께 저장됨.
		 */
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String admin_id=(String)session.getAttribute("admin_id");
		//세션 아이디 값 구함
		
		if(admin_id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='admin_login';");
			out.println("</script>");
		} else {
			this.adminGongjiService.insertG(g);//공지 저장
			return "redirect:/admin_gongji_list";//관리자 공지 목록으로 이동
		}
		return null;
	}
}
