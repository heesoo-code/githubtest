package net.datasa.web5.domain.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
	String memberId;		//회원 아이디
	String memberPassword;	//비밀번호
	String memberName;		//이름
	String email;			//이메일
	String phone;			//전화번호
	String address;			//주소
	Boolean enabled;		//계정상태
	String rolename;		//권한명
}
