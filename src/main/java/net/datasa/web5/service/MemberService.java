package net.datasa.web5.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web5.domain.dto.MemberDTO;
import net.datasa.web5.domain.entity.MemberEntity;
import net.datasa.web5.repository.MemberRepository;
import net.datasa.web5.domain.dto.MemberDTO;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	private final BCryptPasswordEncoder Encoder;
	
	public void join(MemberDTO dto) {
		MemberEntity entity = MemberEntity.builder()
				.memberId(dto.getMemberId())
				.memberPassword(Encoder.encode(dto.getMemberPassword()))
				.memberName(dto.getMemberName())
				.email(dto.getEmail())
				.phone(dto.getPhone())
				.address(dto.getAddress())
				.enabled(true)
				.rolename("ROLE_USER")
				.build();
		
		memberRepository.save(entity);
	}
	
	public MemberDTO getMember(String username) {
		MemberEntity entity = memberRepository.findById(username)
				.orElseThrow(() -> new EntityNotFoundException("아이디가 없습니다."));
		
		MemberDTO dto = MemberDTO.builder()
				.memberId(entity.getMemberId())
				.memberName(entity.getMemberName())
				.email(entity.getEmail())
				.phone(entity.getPhone())
				.address(entity.getAddress())
				.build();
		
		return dto;
	}
	
	public void update(MemberDTO memberDTO) {
		MemberEntity entity = memberRepository.findById(memberDTO.getMemberId()).
				orElseThrow(() -> new EntityNotFoundException("아이디가 없습니다."));
		
		
		entity.setMemberName(memberDTO.getMemberName());
		entity.setPhone(memberDTO.getPhone());
		entity.setEmail(memberDTO.getEmail());
		entity.setAddress(memberDTO.getAddress());
		
		//비밀번호는 전달된 값이 있을때에만 암호화해서 수정
		if (memberDTO.getMemberPassword() != null && !memberDTO.getMemberPassword().isEmpty()) {
		    entity.setMemberPassword(Encoder.encode(memberDTO.getMemberPassword()));
		}
			
		//리턴할 때 DB에 저장됨
		
	}
	

	
	public boolean search(String Id) {			
		
		return !memberRepository.existsById(Id);
	}
}
