package net.datasa.trade.domain.entity;

import jakarta.persistence.*;
import lombok.*;


@Builder
@Getter
@Setter
//@ToString(exclude = "board")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "market_reply")
public class ReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_num")
    private Integer replyNum; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_num", referencedColumnName = "board_num")
    private BoardEntity Board;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private MemberEntity member;

    @Column(name = "reply_text", nullable = false, length = 500)
    private String replyText;

}
