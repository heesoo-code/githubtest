package net.datasa.trade.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "market_member")
public class MemberEntity {
    @Id
    @Column(name = "member_id", length = 20)
    String memberId;	

    @Column(name = "member_pw", nullable = false, length = 100)
    String memberPw;

    @Column(name = "member_name", nullable = false, length = 20)
    String memberName;

    @Column(name = "phone",  nullable = false, length = 20)
    String phone;
}
