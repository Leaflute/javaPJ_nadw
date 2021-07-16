package view;


import java.util.Optional;
import java.util.Scanner;

import common.Code;
import common.InAndOutImpl;
import common.MemberIdDuplicationException;
import domain.Member;
import service.GuestImpl;
import service.Host;
import service.HostImpl;
import service.MapList;

/*
 * 작성일: 2021-06-12
 * 작성자: 나도웅
 * 클래스 용도: 각종 메뉴 출력을 위한 클래스
 * - 상수값을 통해 메뉴 호출
 */

public class Menu extends InAndOutImpl implements Code {
	
	// 인스턴스 생성
	GuestImpl gi = GuestImpl.getInstance();
	HostImpl hi = HostImpl.getInstance();
	
	// Depth1
	// 999 - 시작메뉴
	public void startMenu() {
		int inputNum;
		do {
			pUpperMenuName(SHOP_LOGIN);
			pLoginMenu();
			pThickLine();
			pInputMenuNum();
			inputNum = iLimitNum();
			
			switch (inputNum) {
				case 1 :
					customerLogIn();
					break;
				case 2 :
					ManagerLogIn();
					break;
				case 3 :
					menuCall(MEMBER_REGISTER);
					break;
				default :
					break;
			}
		} while (inputNum!=4);
	}
	
	// Depth2
	// to 100 - 고객 로그인
	public void customerLogIn() {
		pId(); 
		String gId = sc.next();
		pPassword(); 
		String gPwd = sc.next();
		pThinLine();
		
		if (MapList.memMap.containsKey(gId)) {
			
			if(MapList.memMap.get(gId).equals(gPwd)) {
				pWelcome(gId);
				pThinLine();
				menuCall(CUSTOMER_MENU);
				
			} else pMissmatchPwd();
			
		} else pHasNoId();
		
		pThinLine();
	}
	
	// to 200 - 관리 로그인
	public void ManagerLogIn() {
		pId(); 
		String mId = sc.next();
		pPassword(); 
		String mPwd = sc.next();
		pThinLine();
		
		if(mId.equals(Host.Id)) {
			if(mPwd.equals(Host.PASSWORD)) {
				pWelcome(mId);
				menuCall(MANAGER_MENU);
			
			} else 	pMissmatchPwd();
			
		} else pHasNoId();

		pThinLine();
	}
	
	// 100 - 고객 메뉴
	public void guestMenu() {
		int inputNum;
		
		do {
			pUpperMenuName(CUSTOMER_MENU);
			pCustomerMenu();
			pThickLine();
			pInputMenuNum();
			inputNum = iLimitNum();
			
			switch (inputNum) {
				case 1:
					menuCall(CUSTOMER_CART_MENU);
					break;
				case 2:
					menuCall(CUSTOMER_BUYNOW);
					break;
				case 3:
					menuCall(CUSTOMER_REFUND);
					break;
			}
			
		} while (inputNum != 4);		
	}
	
	// 200 - 관리메뉴
	public void hostMenu() {
		int inputNum;
		do {
			pUpperMenuName(MANAGER_MENU);
			pManagerMenu();
			pThickLine();
			pInputMenuNum();
			inputNum = iLimitNum();
			
			switch (inputNum) {
				case 1:
					menuCall(MANAGER_GOODS_MENU);
					break;
				case 2:
					menuCall(MANAGER_ORDER_MENU);
					break;
			} 
			
		} while (inputNum != 3);
	}
	
	// 300 - 회원가입
	public void memRegister() {
		pId(); 
		String newId = sc.next();
		pPassword(); 
		String newPwd = sc.next();
		
		if (newId.equals(Host.Id)) {
			pNowPresentId();
	
		} else {
			Member newMem = new Member(newId, newPwd);
			Optional.ofNullable(MapList.memMap.get(newMem.getMemberId()))
				.ifPresent(targetNewMem -> {
					pNowPresentId();
					throw new MemberIdDuplicationException("이미 존재하는 아이디 입니다.");
			});
			MapList.memMap.put(newMem.getMemberId(), newMem);
			pThinLine();
			pRegiID();
		}
		pThinLine();
	}
	
	
	// Depth3
	// 110 - 장바구니 메뉴
	public void cartMenu() {
		int inputNum;
		do {
			pUpperMenuName(CUSTOMER_CART_MENU);
			pCartMenu();
			pThickLine();
			pInputMenuNum();
			inputNum = iLimitNum();
			
			switch (inputNum) {
				case 1:
					menuCall(CUSTOMER_CART_LIST);
					break;
				case 2:
					menuCall(CUSTOMER_CART_ADD);
					break;
				case 3:
					menuCall(CUSTOMER_CART_DELETE);
					break;
				case 4:
					menuCall(CUSTOMER_CART_BUY);
					break;
			} 		
		} while(inputNum!=5);
	}
	
	// 210 - 재고관리 메뉴
	public void invMenu() {
		int inputNum;
		do {
			pUpperMenuName(MANAGER_GOODS_MENU);
			pInvMenu();
			pThickLine();
			pInputMenuNum();
			inputNum = iLimitNum();
			
			switch (inputNum) {
				case 1:
					menuCall(MANAGER_GOODS_LIST);
					break;
				case 2:
					menuCall(MANAGER_GOODS_ADD);
					break;
				case 3:
					menuCall(MANAGER_GOODS_UPDATE);
					break;
				case 4:
					menuCall(MANAGER_GOODS_DELETE);
					break;
			} 		
		} while(inputNum!=5);
	}
	
	// 220 - 주문관리 메뉴
	public void orderMenu() {
		int inputNum;
		do {
			pUpperMenuName(MANAGER_ORDER_MENU);
			pOrderMenu();
			pThickLine();
			pInputMenuNum();
			inputNum = iLimitNum();
			
			switch (inputNum) {
				case 1:
					menuCall(MANAGER_ORDER_LIST);
					break;
				case 2:
					menuCall(MANAGER_ORDER_CONFIRM);
					break;
				case 3:
					menuCall(MANAGER_ORDER_CANCEL);
					break;
				case 4:
					menuCall(MANAGER_SALE_TOTAL);
					break;
			} 		
		} while(inputNum!=5);
	}

	// 코드 상수를 매개변수로 하는 메뉴 콜 메서드
	public void menuCall(int code) {
		switch (code) {
			case 999:
				startMenu();
				break;
			case 100:
				guestMenu();
				break;
			case 110:
				cartMenu();
				break;
			case 111:
				gi.cartList();
				break;
			case 112:
				try {
					gi.cartAdd();
				} catch (CloneNotSupportedException e) {}
				break;
			case 113:
				gi.cartDel();
				break;
			case 114:
				try {
					gi.cartBuy();
				} catch (CloneNotSupportedException e1) {}
				break;
			case 120:
				try {
					gi.buyNow();
				} catch (CloneNotSupportedException e) {}
				break;
			case 130:
				try {
					gi.refund();
				} catch (CloneNotSupportedException e1) {}
				break;
			case 200:
				hostMenu();
				break;
			case 210:
				invMenu();
				break;
			case 211:
				hi.offSupList();
				break;
			case 212:
				hi.offSupAdd();
				break;
			case 213:
				hi.offSupUpdate();
				break;
			case 214:
				hi.offSupDel();
				break;
			case 220:
				orderMenu();
				break;
			case 221:
				hi.orderList();
				break;
			case 222:
				try {
					hi.orderConfirm();
				} catch (CloneNotSupportedException e) {}
				break;
			case 223:
				try {
					hi.orderCancel();
				} catch (CloneNotSupportedException e) {}
				break;
			case 224:
				hi.saleTotal();
				break;
			case 300:
				try {
					memRegister();
				} catch (MemberIdDuplicationException mde) {
					sc = new Scanner(System.in);
				}
				break;
		}
	}
}
