package common;

/*
 * 작성자 : 나도웅
 * 작성일 : 2021-06-06
 * 용도 : 메뉴를 코드화 시키려는 상수 인터페이스
 */

public interface Code {
// 메뉴구성 활용
	
   // 상수
   static final int SHOP_LOGIN = 999; // 시작메뉴
   
   // 손님
   static final int CUSTOMER_MENU = 100;	//회원메뉴

   // 손님 - 장바구니
   static final int CUSTOMER_CART_MENU = 110;		// 장바구니 메뉴
   static final int CUSTOMER_CART_LIST = 111;      // 장바구니 목록
   static final int CUSTOMER_CART_ADD = 112;      // 장바구니 추가
   static final int CUSTOMER_CART_DELETE = 113;   // 장바구니 삭제
   static final int CUSTOMER_CART_BUY = 114;      // 장바구니 구매
   
   // 손님 - 구매 및 환불
   static final int CUSTOMER_BUYNOW = 120;         // 바로구매
   static final int CUSTOMER_REFUND = 130;         // 환불요청
   
   
   // 관리자
   static final int MANAGER_MENU = 200;	 // 관리메뉴
   
   // 관리자 - 재고관리
   static final int MANAGER_GOODS_MENU = 210;       // 재고관리
   static final int MANAGER_GOODS_LIST = 211;      // 상품목록
   static final int MANAGER_GOODS_ADD = 212;       // 상품추가
   static final int MANAGER_GOODS_UPDATE = 213;    // 상품수정
   static final int MANAGER_GOODS_DELETE = 214;   // 상품삭제
   
   // 관리자 - 주문관리
   static final int MANAGER_ORDER_MENU = 220;      // 주문관리
   static final int MANAGER_ORDER_LIST = 221;      // 주문목록
   // 관리자 - 결제기능
   static final int MANAGER_ORDER_CONFIRM = 222;   // 구매승인
   static final int MANAGER_ORDER_CANCEL = 223;   // 환불승인
   
   // 관리자 - 결산
   static final int MANAGER_SALE_TOTAL = 224;      // 결산하기
   
   // 회원가입 
   static final int MEMBER_REGISTER = 300;			// 회원가입

   // 완료된 구매목록
   static final int FINISHED_ORDER_LIST = 400;		// 완료된 구매 목록
}

