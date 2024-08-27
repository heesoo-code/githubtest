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
public class BoardDTO {
	private Integer boardNum;		
	private String memberId;
	private String memberName;
	private String title;		
	private String contents;			
	private Integer viewCount;			
	private Integer likeCount;			
	private String originalName;			
	private String fileName;		
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
}
