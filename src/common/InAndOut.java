package common;

/*
 * 작성자 : 나도웅
 * 작성일 : 2021-06-12
 * 용도 : 클래스 전반적인 입출력에 관여하는 인터페이스이다.
 */

public interface InAndOut {
	
	// 숫자제한 입력용 메서드
	public int iLimitNum();
	// yes나 no를 선택하는 메서드
	public boolean iYesOrNo();
	
	// 출력용 메서드
	void pThickLine();	// 굵은 라인
	void pThinLine();	// 얇은 라인
	void pFactor();		// |코드|플랫폼|제목|제작사|가격|수량|
	
	void pUpperMenuName(int code);	// 메뉴 이름
	void pInputMenuNum();	// 메뉴 번호를 입력하세요.
	void pLoginMenu();	// 시작 세부메뉴
	void pCustomerMenu();	// 고객 세부메뉴
	void pManagerMenu();	// 관리자 세부메뉴
	void pInvMenu();	// 상품관리 세부메뉴
	void pCartMenu();	// 장바구니 세부메뉴
	void pOrderMenu();	// 주문관리 세부메뉴
	void pUpperMenu();	// 이전 메뉴로 돌아갑니다
	
	void pId(); 		// 로그인 아이디
	void pPassword();	// 로그인 패스워드
	
	void pReinput();	// 다시 입력하세요.
	void pHasNoId();	// 아이디가 존재하지 않습니다.
	void pNowPresentId();	// 이미 존재하는 아이디입니다.
	void pMissmatchPwd();	// 비밀번호가 틀렸습니다.
	void pWelcome(String str);	// str님 환영합니다.
	void pRegiID();		// 아이디를 성공적으로 등록했습니다.
	
	void pNotExistSN(); 	// 존재하지 않는 시리얼 넘버입니다.
	void pAddGoods(int n);	// n 목록에 상품이 추가되었습니다
	void pDelGoods(int n);	// n에서 n2 번호 상품이 삭제되었습니다.
	void pReturn(int n);	// n이 비어있습니다. 이전으로 돌아갑니다.
}
