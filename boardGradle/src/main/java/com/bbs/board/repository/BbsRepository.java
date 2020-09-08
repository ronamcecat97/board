package com.bbs.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bbs.board.entity.BbsEntity;

public interface BbsRepository extends CrudRepository<BbsEntity, Integer>{
	List<BbsEntity> findAllByOrderBySeqDesc();

	@Query(nativeQuery = true, value = "SELECT seq, title, user, detl  FROM tb_bbs WHERE limit :startPage, :ppc")
	List<BbsEntity> findPagedata(@Param("startPage") int startPage, @Param("ppc") int ppc); 
		
}
