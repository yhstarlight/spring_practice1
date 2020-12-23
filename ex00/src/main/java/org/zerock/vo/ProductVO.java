package org.zerock.vo;

public class ProductVO { //데이터 저장빈 클래스
	//변수명은 private을 정의
	private String name; //상품명
	private double price; //상품 가격
	
	public ProductVO(String name,double price) {
		this.name=name;
		this.price=price;
	} //생성자 오버로딩

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	} //getter 메서드 정의

	@Override
	public String toString() {
		return "ProductVO [name="+name+",price="+price+"]";
	}
	
	
}
