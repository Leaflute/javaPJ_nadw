package common;

public class OutOfRangeException extends Exception {
	/**
	 * 작성자: 나도웅
	 * 작성일: 2021-06-10
	 * 클래스 설명: 정해진 정수 범위 밖으로 나가는 예외를 처리하는 클래스
	 */
	private static final long serialVersionUID = 198877997889419198L;

	public OutOfRangeException(String message) {
		super(message);
	}
}
