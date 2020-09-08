package com.bbs.board.dao;

import java.util.List;
import com.bbs.board.dto.BbsDto;

public interface BbsDao {
	public List<BbsDto> getList(BbsDto dto);
	public int addDoc(BbsDto dto);
	public List<BbsDto> getSearchList(BbsDto bbsDto);		
	public BbsDto getDetail(BbsDto bbsDto);
	public int revDoc(BbsDto dto);
	public BbsDto getFileInfo(String seq);
}

