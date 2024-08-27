package net.datasa.web5.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web5.domain.dto.BoardDTO;
import net.datasa.web5.domain.entity.BoardEntity;
import net.datasa.web5.domain.entity.MemberEntity;
import net.datasa.web5.repository.BoardRepository;
import net.datasa.web5.repository.MemberRepository;


@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class BoardService {
	private final BoardRepository boardrepository;
	private final MemberRepository memberrepository;
	
	public void write(BoardDTO boardDTO) {
		if (boardDTO.getMemberId() == null) {
            throw new IllegalArgumentException("Member ID must not be null");
        }
		
		MemberEntity memberEntity = memberrepository.findById(boardDTO.getMemberId()).orElseThrow(() -> new EntityNotFoundException("아이디가 없습니다"));
		
		
		BoardEntity entity = new BoardEntity();
		entity.setMember(memberEntity);
		entity.setTitle(boardDTO.getTitle());
		entity.setContents(boardDTO.getContents());
				
				
				
		boardrepository.save(entity);
	}
	
	public List<BoardDTO> getList() {
		Sort sort = Sort.by(Sort.Direction.DESC, "boardNum");
		 List<BoardEntity> entityList = boardrepository.findAll();
		 List<BoardDTO> dtoList = new ArrayList<>();
		 
	     for (BoardEntity entity : entityList) {
	    	 BoardDTO dto = BoardDTO.builder()
	    			 .boardNum(entity.getBoardNum())
	    			 .memberId(entity.getMember().getMemberId())
	    			 .memberName(entity.getMember().getMemberName())
	    			 .title(entity.getTitle())
	    			 .viewCount(entity.getViewCount())
	    			 .createDate(entity.getCreateDate())
	    			 .build();
	    	 dtoList.add(dto);
	     }
	     return dtoList;
		}
		
	}		
