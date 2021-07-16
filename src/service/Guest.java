package service;

/*
 * 작성자: 나도웅
 * 작성일: 2021-06-7
 * 인터페이스설명: Guest 메뉴 구현에 필요한 메서드를 정의하는 인터페이스
 */

public interface Guest {

	// 110 - 장바구니 메뉴
	public void cartList();	// 111 - 장바구니 조회
	public void cartAdd() throws CloneNotSupportedException;	// 112 - 장바구니 물품 추가
	public void cartDel();	// 113 - 장바구니 물품 삭제
	public void cartBuy() throws CloneNotSupportedException;	// 114 - 장바구니 물품 구매
	
	// 120 - 바로구매
	public void buyNow() throws CloneNotSupportedException;	
	
	// 130 - 환불
	public void refund() throws CloneNotSupportedException;	
	
}
