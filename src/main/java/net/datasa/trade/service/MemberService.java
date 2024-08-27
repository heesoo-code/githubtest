package net.datasa.trade.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.datasa.trade.domain.dto.MemberDTO;
import net.datasa.trade.domain.entity.MemberEntity;
import net.datasa.trade.repository.MemberRepository;


@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {

	   private final MemberRepository memberRepository;
	   
		private final BCryptPasswordEncoder passwordEncoder;
	   
	    public void register(MemberDTO dto) {
	        MemberEntity entity = MemberEntity.builder()
	                .memberId(dto.getMemberId())
	                .memberPw(passwordEncoder.encode(dto.getMemberPw()))
	                .memberName(dto.getMemberName())
	                .phone(dto.getPhone())
	                .build();

	        memberRepository.save(entity);
	    }
}
