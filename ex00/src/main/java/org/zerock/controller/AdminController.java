package org.zerock.controller;

import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.service.AdminService;
import org.zerock.vo.AdminVO;

@Controller
public class AdminController {

	@Inject
	private AdminService adminService;
	
	@GetMapping("/admin_login")
	public ModelAndView admin_login() {
		ModelAndView am = new ModelAndView();
		am.setViewName("admin/admin_login");
		return am;
		//뷰페이지 경로가 /web-inf로 잡힌 이유는 jsp파일이 직접 웹브라우저에서 실행되지 않는다 => 보안효과
		//스프링 개발자 입사시>기술면접
	}
	
	@PostMapping("/admin_login_ok")//post방식으로 접근하는 매핑주소를 처리
	public String admin_login_ok(AdminVO ab, HttpServletResponse response, HttpSession session)
	throws Exception {
		/* 1. AdminVO 빈클래스의 변수명과 admin_login.jsp네임 파라미터 이름이 같으면
		 * ab객체에 관리자 아이디와 비번이 저장됨.
		 * 2. HttpServletResponse는 서버의 가공된 정보를 사용자 웹브라우저에 응답할 때 사용.
		 * 3. session은 로그인 인증과 로그아웃할 때 사용하고 서버에서 구동되고 보안성이 우수하다.
		 */
		
		response.setContentType("text/html; charset=UTF-8" );
		
		PrintWriter out = response.getWriter();//출력 스트림 객체 생성
		
		AdminVO admin_pwd = this.adminService.admin_Login(ab.getAdmin_id());
		//관리자 정보를 가져옴
		//브라우저에 응답하는 문자/태크, 언어코딩 타입을 지정
		
		if(admin_pwd==null) {
			out.println("<script>");
			out.println("alert('관리자 정보가 없습니다!');");
			out.println("history.back();");//history는 자바스크립트에서 과거형 객체로서 이전 주소 정보를 담고있음
			//go(-1)은 뒤로 한칸이동
			out.println("</script>");
		} else {
			if(!admin_pwd.getAdmin_pwd().equals(ab.getAdmin_pwd())) {
				out.println("<script>");
				out.println("alert('관리자 비번이 다릅니다!');");
				out.println("history.go(-1);");
				out.println("</script>");
			} else {
				session.setAttribute("admin_id", ab.getAdmin_id());
				//세션키이름 admin_id에 관리자 아이디를 저장
				session.setAttribute("admin_name", admin_pwd.getAdmin_name());
				//admin_name 키이름에 오라클로부터 가져온 관리자 이름을 저장
				return "redirect:/admin_main"; //관리자 메인화면으로 이동 redierct는 get방식
			}
		}
		return null;
	}
	
	//관리자 메인화면
	@RequestMapping("admin_main")
	public String admin_main(HttpServletResponse response,
			HttpSession session) throws Exception {
		response.setContentType("text/html; charset=UTF-8" );
		PrintWriter out = response.getWriter();
		
		String admin_id=(String)session.getAttribute("admin_id");
		//업캐스팅을 한것에 한해서 다운캐스팅을 허용함.
		//세션 관리자 아이디값을 명시적인 다운캐스팅을 하면서 좌측변수에 저장해서 구함.
		
		if(admin_id==null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='admin_login';");
			out.println("</script>");
		} else {
			return "admin/admin_main";// /WEB-INF/admin/admin_main.jsp
		}
		return null;
	}
	
	//관리자 로그아웃
	@PostMapping("/admin_logout")
	public String admin_logout(HttpServletResponse response,
			HttpSession session) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		session.invalidate();
		
		out.println("<script>");
		out.println("alert('관리자 로그아웃 되었습니다');");
		out.println("location='admin_login';");
		out.println("</script>");

		return null;
	}
	
}
