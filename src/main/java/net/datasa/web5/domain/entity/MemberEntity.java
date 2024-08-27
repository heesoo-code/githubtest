package net.datasa.web5.domain.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Builder //추가
@Data //Entity 최소 조건
@NoArgsConstructor
@AllArgsConstructor
@Entity //Entity 최소 조건
@Table(name="web5_member") //Entity 최소 조건
public class MemberEntity {
	@Id
	@Column(name="member_id", length = 30)
	private String memberId;
	
	@Column(name="member_password", nullable = false, length = 100)
	private String memberPassword;
	
	@Column(name="member_name", nullable = false, length = 100)
	private String memberName;
	
	@Column(name="email", length = 50)
	private String email;
	
	@Column(name="phone", length = 30)
	private String phone;

	@Column(name="address", length = 200)
	private String address;
	

	@ColumnDefault("1")
	@Column(name="enabled", columnDefinition = "tinyint(1)")
	private boolean enabled;
	
	@ColumnDefault("ROLE_USER")
	@Column(name="rolename", length = 30)
	private String rolename;
	
}
