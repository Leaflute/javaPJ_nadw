package domain;

/*
 * 작성자: 나도웅
 * 작성일: 2021-06-10
 * 클래스 설명: 회원정보가 저장되는 클래스이다
 * 수정이력: 2021-06-15 equals 재정의
 */

public class Member {
	
	// 멤버변수
	private String memberId;
	private String memberPwd;
	
	// 생성자
	public Member() {}
	public Member(String memberId, String memberPwd) {
		this.memberId = memberId;
		this.memberPwd = memberPwd;
	}
	
	// getter setter
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPwd() {
		return memberPwd;
	}
	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Member) {
			Member member = (Member)obj;
			if(this.memberPwd == member.memberPwd) {
				return true;
			}
			else return false;
		}
		return true;
	}
}
