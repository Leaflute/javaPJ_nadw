package service;

import java.util.Iterator;
import java.util.Scanner;

import common.OutOfRangeException;
import domain.Computer;

/*
 * 작성일: 2021-06-12
 * 작성자: 나도웅
 * 클래스 용도: 게스트 관련 연산 메서드를 모아놓은 클래스
 */

public class GuestImpl extends MapList implements Guest {
	
	// 싱글톤 활용
	private GuestImpl() {};
	private static GuestImpl instance = new GuestImpl();
	
	// getInstance()
	public static GuestImpl getInstance() {
		if(instance == null) {
			instance = new GuestImpl();
		}
		return instance;
	}

	
	// 111 - 장바구니 목록
	@Override
	public void cartList() {
		if (cartMap.isEmpty()) pReturn(CUSTOMER_CART_LIST);
		else {
			pThinLine();
			pFactor();
			Iterator<Integer> itr = cartMap.keySet().iterator();
			while(itr.hasNext()) {	
				int key = itr.next();
				Computer cart = cartMap.get(key);
				System.out.println(cart);	
			}
		} 
		pThinLine();
	}
	
	// 112 - 장바구니 상품 추가
	@Override
	public void cartAdd() throws CloneNotSupportedException {
		if (invMap.isEmpty()) pReturn(MANAGER_GOODS_LIST);
		else {
			HostImpl hi = HostImpl.getInstance();
			while (true) {
				hi.offSupList();
				System.out.print("추가할 상품의 시리얼 넘버를 입력하세요.[이전:0] ");
				int inputSNum = iLimitNum();
				if (inputSNum==0) break;
				
				if (invMap.containsKey(inputSNum)) {
					Computer inven = invMap.get(inputSNum);
					System.out.println("수량이 " + inven.getAmount() + "개 남았습니다.");
					
					while (true) {
						try {
							
							System.out.print("수량 입력 [0.이전]: ");
							int inputAmount = iLimitNum();
							if (inputAmount==0) break;
							
							else if (inputAmount>inven.getAmount()){
								throw new OutOfRangeException("수량입력을 다시하세요");
							
							} else {
								Computer cart;
								if (cartMap.containsKey(inputSNum)) {
									cart = cartMap.get(inputSNum);
									cart.setAmount(cart.getAmount() + inputAmount);
							
								} else {
									cart = new Computer();
									cart = (Computer)inven.clone();
									cart.setAmount(inputAmount);
								}
								cartMap.put(inputSNum, cart);
								System.out.println(cart);
								pAddGoods(CUSTOMER_CART_LIST);
								break;
							}
							
						} catch (OutOfRangeException ore) {
							System.out.print("1부터 " + inven.getAmount() + "의 숫자를 입력하세요.");
							sc = new Scanner(System.in);
						}
					}
					
				} else { 
					pNotExistSN();
				}
			} 
		} 
		pThinLine();
	}
	
	// 113 - 장바구니 상품 삭제
	@Override
	public void cartDel() {
		if (cartMap.isEmpty()) pReturn(CUSTOMER_CART_LIST);
		else {
			cartList();
			int deleteSNum;
			
			while (true) {
				System.out.print("삭제할 상품 코드를 입력하세요. [0:이전] ");
				deleteSNum = iLimitNum();
				if (deleteSNum==0) break;
				
				if (cartMap.containsKey(deleteSNum)) {
					cartMap.remove(deleteSNum);
					pDelGoods(MANAGER_GOODS_LIST);
					pThinLine();
				
				} else pNotExistSN();
			} 
		} 
		pThinLine();
	}	
	
	// 114 - 장바구니 구매 신청
	@Override
	public void cartBuy() throws CloneNotSupportedException {
		if (cartMap.isEmpty()) pReturn(CUSTOMER_CART_LIST);
		else {
			cartList();
			System.out.print("구매하시겠습니까? [y/n] ");	
			boolean doBuy = iYesOrNo();
			
			if(doBuy==true) {
				CartToOrder();
				cartMap.clear();
				pAddGoods(MANAGER_ORDER_LIST);
			
			} else pUpperMenu();
		} 
		pThinLine();
	}
		
		
	// 120 - 바로구매
	@Override
	public void buyNow() throws CloneNotSupportedException {
		if (invMap.isEmpty()) pReturn(MANAGER_GOODS_LIST);
		else {
			HostImpl hi = HostImpl.getInstance();
			
			while (true) {
				hi.offSupList();
				System.out.print("구매할 상품 코드를 입력하세요. [이전:0] ");
				int inputSNum = iLimitNum();
				if (inputSNum==0) break;
				if (invMap.containsKey(inputSNum)) {
					Computer inven = invMap.get(inputSNum);
					System.out.println("수량이 " + inven.getAmount() + "개 남았습니다.");
					while (true) {
						
						try {
							System.out.print("수량 입력 [0.이전]: ");
							int inputAmount = iLimitNum();
							if (inputAmount==0) break;
							
							else if (inputAmount>inven.getAmount()){
								throw new OutOfRangeException("수량입력을 다시하세요.");
							
							} else {
								Computer order;
								if (orderMap.containsKey(inputSNum)) {
									order = orderMap.get(inputSNum);
									order.setAmount(order.getAmount() + inputAmount);
							
								} else {
									order = new Computer();
									order = (Computer)inven.clone();
									order.setAmount(inputAmount);
								}
								orderMap.put(inputSNum, order);
								pAddGoods(MANAGER_ORDER_LIST);
								break;
							}
							
						} catch (OutOfRangeException ore) {
							System.out.print("1부터 " + inven.getAmount() + "의 숫자를 입력하세요. ");
							sc = new Scanner(System.in);
						}
						
					}		
				} else {
					pNotExistSN();
				}
			}
		}
		pThinLine();
	}
	
	// 130 - 환불신청
	@Override
	public void refund() throws CloneNotSupportedException {
		if (finMap.isEmpty()) pReturn(FINISHED_ORDER_LIST); 
		else {
			while (true) {
				pfinMap();
				System.out.print("환불할 상품 코드를 입력하세요. [0: 이전]");
				int inputSNum = iLimitNum();
				if (inputSNum==0) break;
				if (finMap.containsKey(inputSNum)) {
					Computer fin = finMap.get(inputSNum);
					Computer refund;
					
					if (refundMap.containsKey(inputSNum)) {
						refund = refundMap.get(inputSNum);
						refund.setAmount(refund.getAmount() + fin.getAmount());

					} else {
						refund = new Computer();
						refund = (Computer)fin.clone();
					}
					refundMap.put(inputSNum, refund);
					
					pAddGoods(CUSTOMER_REFUND);
					System.out.println(fin.getName() + " 환불요청했습니다.");
					
					finMap.remove(inputSNum);
					break;
				} else {
					pNotExistSN();
				}
			} 
		} 
		pThinLine();
	}
	
	// 114 sub - 장바구니 목록 to 주문요청 목록
	public void CartToOrder() throws CloneNotSupportedException {
		Iterator<Integer> itr = cartMap.keySet().iterator();
		while(itr.hasNext()) {	
			int key = itr.next();
			Computer cart = cartMap.get(key);
			Computer order;
			if (orderMap.containsKey(key)) {
				order = orderMap.get(key);
				order.setAmount(order.getAmount() + cart.getAmount());
			} else {
				order = new Computer();
				order = (Computer)cart.clone();
				order.setAmount(cart.getAmount());
			}
			orderMap.put(key,order);
		}
	}
	
	// 130 sub - 구매완료된 상품 리스트 출력
	public void pfinMap() {
		pThinLine();
		pFactor();
		Iterator<Integer> itr = finMap.keySet().iterator();
		while(itr.hasNext()) {	
			int key = itr.next();
			Computer refund = finMap.get(key);
			System.out.println(refund);	
		}
		pThinLine();
	}
}
