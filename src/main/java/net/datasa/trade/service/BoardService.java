package net.datasa.trade.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.datasa.trade.domain.dto.BoardDTO;
import net.datasa.trade.domain.entity.BoardEntity;
import net.datasa.trade.domain.entity.MemberEntity;
import net.datasa.trade.repository.BoardRepository;
import net.datasa.trade.repository.MemberRepository;
import net.datasa.trade.domain.dto.ReplyDTO;
import net.datasa.trade.domain.entity.ReplyEntity;
import net.datasa.trade.repository.ReplyRepository;

@RequiredArgsConstructor
@Service
@Transactional
public class BoardService {

	private final BoardRepository boardRepository;
	
	private final MemberRepository memberRepository;
    private final ReplyRepository replyRepository;

	public List<BoardDTO> getListAll() {
		Sort sort = Sort.by(Sort.Direction.DESC, "boardNum");
		List<BoardEntity> entityList = boardRepository.findAll(sort);
		List<BoardDTO> dtoList = new ArrayList<>();
        for (BoardEntity entity : entityList) {
            BoardDTO dto = BoardDTO.builder()
                    .boardNum(entity.getBoardNum())
                    .soldout(entity.getSoldout())
                    .category(entity.getCategory())
                    .title(entity.getTitle())
                    .price(entity.getPrice())
                    .memberId(entity.getMember().getMemberId())
                    .memberName(entity.getMember().getMemberName())
                    .inputDate(entity.getInputDate())
                    .build();
            dtoList.add(dto);
        }

        return dtoList;
    }
	
	public void write(BoardDTO boardDTO) throws IOException {
        MemberEntity memberEntity = memberRepository.findById(boardDTO.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("회원아이디가 없습니다."));
	
        BoardEntity entity = new BoardEntity();
        
        entity.setMember(memberEntity);
        entity.setCategory(boardDTO.getCategory());
        entity.setTitle(boardDTO.getTitle());
        entity.setContents(boardDTO.getContents());
        entity.setPrice(boardDTO.getPrice());
        boardRepository.save(entity);
}
	
	
    private BoardDTO convertToDTO(BoardEntity entity) {
        return BoardDTO.builder()
            .boardNum(entity.getBoardNum())
            .memberId(entity.getMember() != null ? entity.getMember().getMemberId() : null)
            .category(entity.getCategory())
            .title(entity.getTitle())
            .contents(entity.getContents())
            .price(entity.getPrice())
            .soldout(entity.getSoldout())

            .inputDate(entity.getInputDate())
            .build();
    }
    
//    private ReplyDTO convertToReplyDTO(ReplyEntity entity) {
//        return ReplyDTO.builder()
//                .replyNum(entity.getReplyNum())
//                .memberName(entity.getMemberName())
//                .contents(entity.getContents())
//                .createDate(entity.getCreateDate())
//                .build();
//    }
    
	
	public BoardDTO getBoard(int boardNum) {
        BoardEntity entity = boardRepository.findById(boardNum)
                .orElseThrow(() -> new EntityNotFoundException("해당 번호의 글이 없습니다."));

       
        BoardDTO dto = convertToDTO(entity);

//        List<ReplyDTO> replyDTOList = new ArrayList<ReplyDTO>();
//        for (ReplyEntity replyEntity : entity.getReplyList()) {
//            ReplyDTO replyDTO = convertToReplyDTO(replyEntity);
//            replyDTOList.add(replyDTO);
//        }
//        dto.setReplyList(replyDTOList);
        return dto;
    }
	
	public void delete(int boardNum) {
        BoardEntity boardEntity = boardRepository.findById(boardNum)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다."));
        
        boardRepository.delete(boardEntity);
    }
	
	
}
