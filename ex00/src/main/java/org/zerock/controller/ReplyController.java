package org.zerock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.service.ReplyService;
import org.zerock.vo.ReplyVO;

@RestController
@RequestMapping("/replies")
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	//댓글 등록
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> register(
			@RequestBody ReplyVO vo) {
		/*
		 * @RequetBody 애노테이션은 전송된 JSON 데이터를 객체로 변환한다.
		 * 데이터 전송 방식은 JSON을 이용한다. JSON으로 보내진 자료를 ReplyVO타입으로 바꿔준다.
		 */
		ResponseEntity<String> entity=null;
		
		try {
			this.replyService.addReply(vo);//댓글 추가
			entity=new ResponseEntity<String>("success",
					HttpStatus.OK); //댓글 저장 성공시 success 문자열이 반환되고
			//정상 상태코드 200이 반환
		} catch(Exception e) {
			e.printStackTrace();//예외 오류가 발생하면 족적을 남김
			entity = new ResponseEntity<String>(e.getMessage(),
					HttpStatus.BAD_REQUEST);
			//예외 오류가 발생하면 나쁜 상태코드 문자열이 반환
		}
		return entity;
	}
	
	//게시물 번호에 해당하는 댓글 목록
	@RequestMapping(value="/all/{bno}", method=RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(
			@PathVariable("bno") int bno) {
		/*
		 * @PathVariable("bno")는 매핑주소의 게시물 번호값을 추출하는 용도로 활용됨
		 */
		
		ResponseEntity<List<ReplyVO>> entity=null;
		
		try {
			entity = new ResponseEntity<>(
					this.replyService.listReply(bno),HttpStatus.OK);
			//게시물번호에 해당하는 댓글목록을 반환
		} catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//댓글 수정
	@RequestMapping(value="/{rno}", method= {RequestMethod.PUT, RequestMethod.PATCH})
	//PUT은 전체자료를 수정, PATCH는 일부 자료만 수정
	public ResponseEntity<String> update(@PathVariable("rno") int rno,
			@RequestBody ReplyVO vo){
		ResponseEntity<String> entity = null;
		
		try {
			vo.setRno(rno); //댓글 번호 저장
			this.replyService.updateReply(vo);//댓글 수정
			entity = new ResponseEntity<String>("SUCCESS",
					HttpStatus.OK);
			//수정성공하면 success문자열과 200코드가 반환
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//댓글 삭제
	@RequestMapping(value="{rno}", method=RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("rno") int rno) {
		ResponseEntity<String> entity = null;
		
		try {
			this.replyService.remove(rno);//댓글 삭제
			entity=new ResponseEntity<String>("success", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
