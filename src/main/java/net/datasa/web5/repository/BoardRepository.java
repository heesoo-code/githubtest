package net.datasa.web5.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import net.datasa.web5.domain.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
	List<BoardEntity> findByTitleContaining(String s, Sort sort);
}
