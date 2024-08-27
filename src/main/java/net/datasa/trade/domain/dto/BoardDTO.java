package net.datasa.trade.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Integer boardNum;                       
    private String memberId;  
    private String memberName;
    private String category;                    
    private String title;                          
    private String contents;                
    private boolean soldout ; 
    private Integer price;
    private String buyerId;                    
    private LocalDateTime inputDate;                       
    
}
