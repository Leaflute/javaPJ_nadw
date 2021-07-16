package common;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * 작성자 : 나도웅
 * 작성일 : 2021-06-12
 * 용도 : 클래스 전반적인 입출력을 도와주는 최상위 부모 클래스
 */

public class InAndOutImpl implements Code, InAndOut {
	
	// Scanner 부르기
	public Scanner sc = new Scanner(System.in);
	
	// 숫자를 제한해 각종 오류를 방지하는 입력 메서드
	@Override
	public int iLimitNum() {
		int inputNum;
		while (true) {
			try {
				inputNum = sc.nextInt();
				if (inputNum<0) 
					throw new OutOfRangeException("");
				else break;
				
			} catch ( InputMismatchException ime ) {
				pReinput();
				sc = new Scanner(System.in);
				
			} catch ( OutOfRangeException ne) {
				pReinput();
				sc = new Scanner(System.in);
			} 
		}
		return inputNum;
	}
	
	// yes나 no를 선택하는 메서드
	@Override
	public boolean iYesOrNo() {
		String inputStr = null;
		inputStr = sc.next();
		boolean yn = false;
		
		loop1:	
		while (true) {
			
			switch (inputStr) {	
				case "y" : case "Y" :
					yn = true;
					break loop1;
				case "n" : case "N" :
					break loop1;
				default :
					System.out.println("Y,y 또는 N,n을 입력하세요.");
					inputStr = sc.next();
					break;
				}		
			}			
		return yn;
	}
	
	// 굵은 라인 출력
	@Override
	public void pThickLine() {
		for (int i = 0 ; i < 36 ; i++) {
			System.out.print("━");
		}
		System.out.println();
	}
	
	// 얇은 라인 출력
	@Override
	public void pThinLine() {
		for (int i = 0 ; i < 36 ; i++) {
			System.out.print("─");
		}
		System.out.println();
	}
	
	// 요소 출력
	@Override
	public void pFactor() {
		System.out.println("코드\t이름\t가격\t수량");
	}
	
	// 메뉴 출력
	@Override
	public void pUpperMenuName(int code) {
		for (int i = 0 ; i < 12 ;i++) {
			System.out.print("━");
		}
		switch (code) {
		case SHOP_LOGIN:
			System.out.print("　시　작　메　뉴　");
			break;
		case CUSTOMER_MENU:
			System.out.print("　고　객　메　뉴　");
			break;
		case MANAGER_MENU:
			System.out.print("　관　리　메　뉴　");
			break;
		case MANAGER_GOODS_MENU:
			System.out.print("　재　고　관　리　");
			break;
		case CUSTOMER_CART_MENU:
			System.out.print("　장　바　구　니　");
			break;
		case MANAGER_ORDER_MENU:
			System.out.print("　주　문　관　리　");
			break;
		}
		for (int i = 0 ; i < 12 ;i++) {
			System.out.print("━");
		}
		System.out.println();
	}
	
	@Override
	public void pInputMenuNum() {
		System.out.print("메뉴 번호를 입력하세요. ");
	}

	
	// 로그인 세부메뉴 출력
	@Override
	public void pLoginMenu() {
		System.out.println("①고객메뉴\t②관리메뉴\t③회원가입\t④종료");
	}
	
	// 고객 세부메뉴 출력
	@Override
	public void pCustomerMenu() {
		System.out.println("①장바구니\t②바로구매\t③환불요청\t④로그아웃");
	}
	
	// 관리자 세부메뉴 출력
	@Override
	public void pManagerMenu() {
		System.out.println("①재고관리\t②주문관리\t③로그아웃");
	}
	
	// 장바구니 세부메뉴 출력
	@Override
	public void pCartMenu() {
		System.out.println("①목록보기\t②상품추가\t③상품삭제\t④구매하기\t⑤이전");
	}
	
	// 재고관리 세부메뉴 출력
	@Override
	public void pInvMenu() {
		System.out.println("①재고목록\t②상품추가\t③상품수정\t④상품삭제\t⑤이전");
	}
	
	// 주문관리 세부메뉴 출력
	@Override
	public void pOrderMenu() {
		System.out.println("①주문목록\t②주문확정\t③환불승인\t④결산\t⑤이전");
	}
	
	// 이전메뉴
	@Override
	public void pUpperMenu() {
		System.out.println("이전 메뉴로 돌아갑니다.");
	}
	
	// 입력관련
	@Override
	public void pReinput() {
		System.out.print("다시 입력하세요. ");
	}
	
	// 로그인 관련
	@Override
	public void pId() {
		System.out.print("아이디: ");
	}

	@Override
	public void pPassword() {
		System.out.print("비밀번호: ");
	}

	@Override
	public void pHasNoId() {
		System.out.println("존재하지 않는 아이디입니다. 아이디를 확인해주세요.");
	}

	@Override
	public void pMissmatchPwd() {
		System.out.println("비밀번호가 틀렸습니다.");
	}

	@Override
	public void pNowPresentId() {
		System.out.println("이미 존재하는 아이디입니다.");
	}

	@Override
	public void pWelcome(String str) {
		System.out.println(str + "님 환영합니다.");
	}

	@Override
	public void pRegiID() {
		System.out.println("아이디를 성공적으로 등록했습니다.");
	}
	
	
	// 시리얼이 없을 때
	@Override
	public void pNotExistSN() {
		System.out.println("존재하지 않는 시리얼 넘버입니다.");
	}
	
	// 상품 추가
	@Override
	public void pAddGoods(int code) {
		String str = null;
		switch (code) {
			case MANAGER_GOODS_LIST :
				str = "재고";
				break;
			case CUSTOMER_CART_LIST :
				str = "장바구니";
				break;
			case MANAGER_ORDER_LIST :
				str = "주문요청";
				break;
			case CUSTOMER_REFUND :
				str = "환불요청";
				break;
			case FINISHED_ORDER_LIST :
				str = "완료된 구매";
				break;
		}
		System.out.println(str + " 목록에 상품이 추가되었습니다.");
	}
	
	// 상품 삭제 완료
	@Override
	public void pDelGoods(int code) {
		String str = null;
		switch (code) {
		case MANAGER_GOODS_LIST :
			str = "재고";
			break;
		case CUSTOMER_CART_LIST :
			str = "장바구니";
			break;
		case MANAGER_ORDER_LIST :
			str = "주문요청";
			break;
		case CUSTOMER_REFUND :
			str = "환불요청";
			break;
		case FINISHED_ORDER_LIST :
			str = "완료된 구매";
			break;
		}
		System.out.println(str + " 목록에서 상품을 삭제했습니다.");
	}
	
	// 리스트 요소 없을때 반환
	@Override
	public void pReturn(int n) {
		String str = null;
		switch (n) {
		case MANAGER_GOODS_LIST :
			str = "재고";
			break;
		case CUSTOMER_CART_LIST :
			str = "장바구니";
			break;
		case MANAGER_ORDER_LIST :
			str = "구매요청";
			break;
		case CUSTOMER_REFUND :
			str = "환불요청";
			break;
		case FINISHED_ORDER_LIST :
			str = "완료된 구매";
			break;
		}
		System.out.println(str + " 목록이 비어있습니다. 이전으로 돌아갑니다.");
	}




}
