package net.datasa.web5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.datasa.web5.domain.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {

}
