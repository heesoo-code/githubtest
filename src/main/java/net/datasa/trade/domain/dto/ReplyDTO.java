package net.datasa.trade.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {
    private Integer replyNum;                     
    private Integer boardNum;                       
    private String memberId;                       
    private String replyText;                                          
}
