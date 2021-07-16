package service;

import java.util.HashMap;
import java.util.Map;

import common.InAndOutImpl;
import domain.Member;
import domain.Computer;

/*
 * 작성일: 2021-06-12
 * 작성자: 나도웅
 * 클래스 용도: 리스트와 맵 객체를 정적 상수로 선언한 곳이 닮긴 서비스 마더 클래스.
 */

public class MapList extends InAndOutImpl {

	// 맵과 리스트
	final public static Map<String, Member> memMap = new HashMap<String, Member>();	//회원 맵
	final public static Map<Integer, Computer> invMap = new HashMap<Integer, Computer>();	//재고 맵
	final public static Map<Integer, Computer> cartMap = new HashMap<Integer, Computer>();	//장바구니맵
	final public static Map<Integer, Computer> orderMap = new HashMap<Integer, Computer>();	//주문요청맵
	final public static Map<Integer, Computer> finMap = new HashMap<Integer, Computer>();	//구매완료맵
	final public static Map<Integer, Computer> refundMap = new HashMap<Integer, Computer>();//환불요청맵
		
}
