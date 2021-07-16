package common;



public class MemberIdDuplicationException extends RuntimeException {
	
	/*
	 * 작성자: 나도웅
	 * 작성일: 2021-06-10
	 * 클래스 설명: 회원 아이디 중복 예외처리 클래스
	 */
	private static final long serialVersionUID = 146546545689489L;
	
	public MemberIdDuplicationException(String message) {
		super(message);
	}
}
