package com.bbs.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bbs.board.dto.BbsDto;
import com.bbs.board.service.BbsService;

@RestController
public class BbsRestController {

	@Autowired
	BbsService bbsService;
	
	@PostMapping("/rest/searchList2")
	List<BbsDto> getSearchList(@RequestBody BbsDto bbsDto) {
		List<BbsDto> list = bbsService.getSearchList(bbsDto);
		return list;
	}
	
	@GetMapping("/rest/detail/{seq}")
	BbsDto getSearchList(@PathVariable("seq") int seq) {
		BbsDto inDto = new BbsDto();
		inDto.setSeq(seq);
		
		BbsDto bbsDto = bbsService.getDetail(inDto);
		return bbsDto;
	}
	
	@PutMapping("/rest/rev/{seq}")
	public int rev(@RequestBody BbsDto dto) {
		int nCnt = bbsService.revDoc(dto);
		return nCnt;
	}
}


