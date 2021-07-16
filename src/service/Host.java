package service;

/*
 * 작성자: 나도웅
 * 작성일: 2021-06-7
 * 인터페이스 설명: 관리자 메뉴구현에 필요한 메서드와 관리자 정보를 보존하는 인터페이스이다
 */

public interface Host {
	
	public static final String Id = "host";
	public static final String PASSWORD = "host";
	
	// 210 - 재고관리 구현부
	public void offSupList();	// 211 - 목록 조회
	public void offSupAdd();	// 212 - 상품 추가
	public void offSupUpdate();	// 213 - 상품 수정
	public void offSupDel();	// 214 - 상품 삭제
	
	// 2-2. 주문관리 구현부
	public void orderList();	// 221 - 주문 목록	
	public void orderConfirm() throws CloneNotSupportedException;	// 222 - 주문 승인
	public void orderCancel() throws CloneNotSupportedException;	// 223 - 주문 취소
	public void saleTotal();	// 224 - 결산			
	
}
