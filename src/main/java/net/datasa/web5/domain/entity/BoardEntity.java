package net.datasa.web5.domain.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@EntityListeners(AuditingEntityListener.class)
@Builder //추가
@Data //Entity 최소 조건
@NoArgsConstructor
@AllArgsConstructor
@Entity //Entity 최소 조건
@Table(name="web5_board") 
public class BoardEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="board_num")
	private Integer boardNum;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_id", referencedColumnName = "member_id")
	MemberEntity member;

//	@Column(name="member_id")
//	private String memberId;
//	
	@Column(name="title", nullable = false, length = 1000)
	private String title;
	
	@Column(name="contents", nullable = false)
	private String contents;
	
	// int, boolean, long
	// Integer, Long, String
	
	@ColumnDefault("0")
	@Column(name="view_count")
	private Integer viewCount;
	
	@ColumnDefault("0")
	@Column(name="like_count")
	private Integer likeCount;

	@Column(name="original_name", length = 300)
	private String originalName;
	
	@Column(name="file_name", length = 100)
	private String file_name;
	
	@CreatedDate
	@Column(name="create_date", columnDefinition = "timestamp defualt current_timestamp")
	private LocalDateTime createDate;
	
	@LastModifiedDate
	@Column(name="update_date", columnDefinition = "timestamp defualt current_timestamp")
	private LocalDateTime updateDate;
	
}
