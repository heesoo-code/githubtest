package net.datasa.trade.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
//import java.util.List;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "market_board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_num")
    private Integer boardNum;                       //게시글 일련번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private MemberEntity member;
    
    @Column(name = "category", nullable = false, length = 50)
    private String category;
    
    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "contents", nullable = false, columnDefinition = "text", length=2000)
    private String contents;

    @Column(name = "price", columnDefinition = "default 0")
    private Integer price;
    
    @Column(name = "soldout", columnDefinition = "tinyint(1)")
    Boolean soldout = false;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", referencedColumnName = "member_id")
    private MemberEntity buyer;
    
    @CreatedDate
    @Column(name = "input_date", columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime inputDate;


//    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ReplyEntity> replyList;

}
