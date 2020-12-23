package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController2 {

	@RequestMapping("doC") //doC매핑주소 등록
	public String doC(@ModelAttribute("msg") String msg) {
		//@ModelAttribute("msg")는 msg파라미터 이름에 인자값을 문자열로 전달한다.
		//웹 주소창에서 doC?msg=문자열로 형태의 노출되는 get방식으로 전달됨. 보안 취약.
		return "result"; //뷰리졸브경로=>WEB-INF/views/result.jsp
	}
}
