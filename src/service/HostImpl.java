package service;

import java.util.Iterator;

import domain.Computer;

/*
 * 작성일: 2021-06-12
 * 작성자: 나도웅
 * 클래스 용도: 호스트 관련 연산 메서드를 모아놓은 클래스
 * - 2021-06-14. 시리얼 생성에 있어서 오류 발생
 */

public class HostImpl extends MapList implements Host {
	
	// 결산용 변수
	private int balance; 
	
	// 싱글톤 활용
	private HostImpl() {}
	private static HostImpl instance = new HostImpl();
	
	// getInstance()
	public static HostImpl getInstance() {
		if(instance == null) {
			instance = new HostImpl();
		}
		return instance;
	}
	
	// 211 - 재고목록 조회
	@Override
	public void offSupList() {
		if (invMap.isEmpty()) pReturn(MANAGER_GOODS_LIST);
		else {
			pThinLine();
			pFactor();
			Iterator<Integer> itr = invMap.keySet().iterator();
			while(itr.hasNext()) {	
				int key = itr.next();
				Computer inven = invMap.get(key);
				System.out.println(inven);	
			}
		} 
		pThinLine();
	}
	
	// 212 - 상품 추가
	@Override
	public void offSupAdd() {
		while (true) {
			System.out.println("상품정보를 입력하세요. ");
			
			System.out.print("이름 입력 [0.이전]: ");
			String iName = sc.next();
			if (iName.equals("0")) break;
			
			System.out.print("가격 입력 [0.이전]: ");
			int iPrice = iLimitNum();
			if (iPrice==0) break;
			
			System.out.print("수량 입력 [0.이전]: ");
			int iCount = iLimitNum();
			if (iCount==0) break;
			
			// 새로운 인스턴스 생성하고 HashMap 추가
			Computer inven = new Computer(iName, iPrice, iCount);
			
			// 중복 키값 피하기
			while(invMap.containsKey(inven.getSerialNum())) {
				inven.setSerialNum((int)(Math.random()*1000) + 1000);
			}
			
			// 해시맵에 추가
			invMap.put(inven.getSerialNum(), inven);
			pAddGoods(MANAGER_GOODS_LIST);
			pThinLine();
			break;
		} 
	}
	
	// 213 - 상품 수정
	@Override
	public void offSupUpdate() {
		if (invMap.isEmpty()) pReturn(MANAGER_GOODS_LIST);
		else {
			while (true) {
				offSupList();
				System.out.print("상품 코드 입력 [0:이전]: ");
				int updateSNum = iLimitNum();
				if (updateSNum==0) break;
				
				if (invMap.containsKey(updateSNum)) {
					Computer inven = invMap.get(updateSNum); // 수정할 주소에 접근
					
					while (true) {
						pFactor();
						System.out.println(inven);
						System.out.print("수정할 항목을 선택하세요. ①이름 ②가격 ③수량 [0:이전] ");
						int iMenu = iLimitNum();
						if (iMenu==0) break;

						loop1 :
						while(true) {
							switch(iMenu) {
								case 1:
									System.out.print("이름 수정 [0:이전]: ");
									String updateName = sc.next();
									if (updateName.equals("0")) break loop1;
									inven.setName(updateName);
									break loop1;
								case 2:
									System.out.print("가격 수정 [0:이전]: ");
									int updatePrice = iLimitNum();
									if (updatePrice==0) break loop1;
									inven.setPrice(updatePrice);	
									break loop1;
								case 3:
									System.out.print("수량 수정 [0:이전]: ");
									int updateAmount = iLimitNum();
									if (updateAmount==0) break loop1;
									inven.setAmount(updateAmount);	
									break loop1;
								default:
									System.out.println("다시 입력하세요.");
									break loop1;
							}		
						}
						invMap.put(updateSNum, inven);
						System.out.println("상품수정을 완료했습니다.");
						pThinLine();
					}
				} else pNotExistSN();
			}
			
		} 
		pThinLine();
	}
	
	// 214 - 상품 삭제
	@Override
	public void offSupDel() {
		if (invMap.isEmpty()) pReturn(MANAGER_GOODS_LIST);
		else {
			offSupList();
			int deleteSNum;
			
			while (true) {
				System.out.print("상품 코드 입력 [0:이전] ");
				deleteSNum = iLimitNum();
				if (deleteSNum==0) break;
				
				if (invMap.containsKey(deleteSNum)) {
					invMap.remove(deleteSNum);
					pDelGoods(MANAGER_GOODS_LIST);
					break;
				} else {
					pNotExistSN();
				}
			} 
		} 
		pThinLine();
	}
	
	// 221 - 주문 목록
	@Override
	public void orderList() {
		if (orderMap.isEmpty()) pReturn(MANAGER_ORDER_LIST);
		else {
			pThinLine();
			pFactor();
			Iterator<Integer> itr = orderMap.keySet().iterator();
			while(itr.hasNext()) {	
				int key = itr.next();
				Computer order = orderMap.get(key);
				System.out.println(order);	
			}
		} 
		pThinLine();
	}
	
	// 222 - 주문 승인
	@Override
	public void orderConfirm() throws CloneNotSupportedException {
		if (orderMap.isEmpty()) pReturn(MANAGER_ORDER_LIST); 
		else {
			while (true) {
				orderList();
				System.out.print("승인할 코드 번호를 입력하세요. [0:이전] ");
				int inputSNum = iLimitNum();
				if (inputSNum==0) break;
				
				if (orderMap.containsKey(inputSNum)) {
					Computer order = orderMap.get(inputSNum);

					if (invMap.containsKey(inputSNum)) {
						Computer inven = invMap.get(inputSNum);
						
						if (inven.getAmount() < order.getAmount()) {
							System.out.println("재고가 주문량보다 적습니다. 구매결정을 보류합니다.");
							break;
							
						} else {	
							Computer fin;
							if (finMap.containsKey(inputSNum)) {
								fin = finMap.get(inputSNum);
								fin.setAmount(fin.getAmount() + order.getAmount());
						
							} else {
								fin = new Computer();
								fin = (Computer)order.clone();
							}
							
							balance += (order.getPrice())*(order.getAmount());
							inven.setAmount(inven.getAmount() - order.getAmount());
							
							finMap.put(inputSNum, fin);
							orderMap.remove(inputSNum);

							pAddGoods(FINISHED_ORDER_LIST);
							break;
						}		
					} else {
						System.out.println("상품 목록이 재고에 존재하지 않습니다. 주문 리스트에서 삭제합니다.");
						orderMap.remove(inputSNum);
						break;
					}
				} else {
					pNotExistSN();
				}
			}
		}
		pThinLine();
	}

	// 223 - 환불 승인
	@Override
	public void orderCancel() throws CloneNotSupportedException {
		if (refundMap.isEmpty()) pReturn(CUSTOMER_REFUND);
		else {
			while (true) {
				pRefundMap();
				System.out.print("환불할 주문 번호를 입력하세요. [0: 이전] ");
				int inputSNum = iLimitNum();
				if (inputSNum==0) break;
				
				if (refundMap.containsKey(inputSNum)) {
					Computer refund = refundMap.get(inputSNum);
					Computer inven;
					
					if (invMap.containsKey(inputSNum)) {
						inven = invMap.get(inputSNum);
						inven.setAmount(inven.getAmount() + refund.getAmount());
					} else {
						inven = new Computer();
						inven = (Computer)refund.clone();
					}
					
					balance -= (refund.getPrice())*(refund.getAmount());
					
					invMap.put(inputSNum,inven);
					refundMap.remove(inputSNum);
					System.out.println("환불 승인을 완료했습니다.");
				} else {
					pNotExistSN();
				}
			}
		} 
		pThinLine();	 
	}	
	
	// 224 - 결산
	@Override
	public void saleTotal() {
		System.out.println("결산액은 " + balance + "입니다.");
		pThinLine();
	}
	
	// 223 sub - 환불리스트 출력
	public void pRefundMap() {
		pThinLine();
		pFactor();
		Iterator<Integer> itr = refundMap.keySet().iterator();
		while(itr.hasNext()) {	
			int key = itr.next();
			Computer refund = refundMap.get(key);
			System.out.println(refund);	
		}	
		pThinLine();
	}

}
