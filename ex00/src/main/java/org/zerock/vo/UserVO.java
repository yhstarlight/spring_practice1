package org.zerock.vo;

public class UserVO {
	private String uid2;//회원 아이디
	private String upw;//회원 비번
	private String uname;//회원이름
	private int upoint;//메시지를 보낸 사람에게 포인트 점수 10점 업
	
	public String getUid2() {
		return uid2;
	}
	public void setUid2(String uid2) {
		this.uid2 = uid2;
	}
	public String getUpw() {
		return upw;
	}
	public void setUpw(String upw) {
		this.upw = upw;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getUpoint() {
		return upoint;
	}
	public void setUpoint(int upoint) {
		this.upoint = upoint;
	}
	
	
}
