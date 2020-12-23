package org.zerock.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.vo.SampleVO;

@RestController // 스프링 4.0 버전 이후부터는 @RestController 애노테이션으로 jsp뷰페이지를 만들지 않고도
//Rest방식의 데이터 처리를 위해서 사용한다.
//객체는 문자열, json, xml이다.
@RequestMapping("/sample") // 컨트롤러 자체에 매핑주소 sample등록
public class SampleController6 {

	@RequestMapping("/hello") // get or post
	public String hello() {
		return "REST START"; // 문자열 객체 반환
	}

	@GetMapping("/sendVO")
	// sendVO.json을 입력하면 원하는 JSON 데이터에 키,값을 확인할 수 있다.
	public SampleVO sendVO() {

		SampleVO vo = new SampleVO();

		vo.setFirstName("이");
		vo.setLastName("순신");
		vo.setMno(10);
		return vo;
	}

	@RequestMapping("/sendList")
	public List<SampleVO> sendList() {
		List<SampleVO> list = new ArrayList<>();
		/*
		 * java.util.List 인터페이스 컬렉션 자료구조 특징 1. 가변적인 복수개의 데이터를 저장한다. 2. 복수개의 원소값을 순서있게
		 * 저장한다. 중복원소값을 허용한다. 3. jdk 1.5부터는 <SampleVO>>라는 컬렉션 제네릭타입이 추가되어서 지정된 SampleVO
		 * 타입만 저장가능하다. 4. jdk 1.7부터는 <>제네릭 타입은 생략가능다하
		 * 
		 */

		for (int i = 1; i <= 10; i++) {
			SampleVO vo = new SampleVO();
			vo.setMno(i);
			vo.setFirstName("세종");
			vo.setLastName("대왕");

			list.add(vo);
		} // for
		return list;
	}

	// 키, 값쌍의 Map타입 Json
	@GetMapping("/sendMap")
	public Map<Integer,SampleVO> sendMap(){ 
		Map<Integer,SampleVO> map=new HashMap<>(); 
		for(int i=1;i<=10;i++) { 
			SampleVO vo=new SampleVO(); 
			vo.setMno(i);
			vo.setFirstName("이"); 
			vo.setLastName("순신"); 
			map.put(i,vo);//컬렉션에 키,값 저장
		}//for 
		return map;
	}//sendMap() 
	
	@RequestMapping("/sendError")
	public ResponseEntity<Void> sendListAuth(){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		/*
		 * 1. @RestController는 별도의 jsp파일을 만들지 않고도  Rest 서비스를 실행하기 때문에
		 * 결과 데이터에 예외적인 문제가 발생할 수 있다.
		 * 스프링에서 제공하는 ResponseEntity 타입은 개발자가 문제가 되는 나쁜 상태 404, 500 같은 Http 나쁜 상태코드를 데이터와 함께
		 * 전송할 수 있기 때문에 좀 더 세밀한 제어가 필요한 경우 사용해 볼 수 있다.
		 * BAD_REQUEST는 400 나쁜상태코드가 전송된다.
		 * 200상태코드가 전송되면 정상적으로 데이터가 수행성공했다는 뜻이다.
		 */
	}
	
	//정상적인 json데이터와 404(자원을 찾기 못했을 때) 나쁜상태코드를 함께 전송 F12개발툴에서 확인 가능
	@RequestMapping("/sendListNot")
	public ResponseEntity<List<SampleVO>> sendListNot(){
		List<SampleVO> list = new ArrayList<>();
		
		for(int i=1; i<=10; i++) {
			SampleVO vo = new SampleVO();
			
			vo.setMno(i);
			vo.setFirstName("이");
			vo.setLastName("순신");
			
			list.add(vo);
		}
		return new ResponseEntity<List<SampleVO>>(list,
				HttpStatus.NOT_FOUND);
	}
}
