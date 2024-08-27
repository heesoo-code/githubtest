package net.datasa.trade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.datasa.trade.domain.entity.ReplyEntity;

@Repository
public interface ReplyRepository extends JpaRepository<ReplyEntity, Integer> {

//	List<ReplyEntity> findByBoard_BoardNumOrderByReplyNum(Integer n);
}
