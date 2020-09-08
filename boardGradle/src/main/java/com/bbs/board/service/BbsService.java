package com.bbs.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.board.dao.BbsDao;
import com.bbs.board.dto.BbsDto;

@Service
public class BbsService {
	@Autowired
	BbsDao bbsDao;
	
	public List<BbsDto> getList(long nPage) {

		BbsDto inDto = new BbsDto();
		inDto.setPage(nPage);
		
		return bbsDao.getList(inDto);
	}
	
	public List<BbsDto> getSearchList(BbsDto bbsDto) {
		return bbsDao.getSearchList(bbsDto);
	}
	
	public int addDoc(BbsDto dto) {
		return bbsDao.addDoc(dto);
	}

	public BbsDto getDetail(BbsDto bbsDto) {
		return bbsDao.getDetail(bbsDto);
	}
	
	public int revDoc(BbsDto dto) {
		return bbsDao.revDoc(dto);
	}
	
	public BbsDto getFileUuid(String seq) {
		return bbsDao.getFileInfo(seq);
	}
}

 