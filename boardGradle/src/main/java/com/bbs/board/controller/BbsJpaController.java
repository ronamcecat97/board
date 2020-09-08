package com.bbs.board.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bbs.board.dto.BbsDto;
import com.bbs.board.entity.BbsEntity;
import com.bbs.board.service.BbsJpaService;

@Controller
public class BbsJpaController {

	@Autowired
	BbsJpaService bbsJpaService;

	@Value("${fileupload.path}")
	private String uploadPath;	
	
	@GetMapping("/jpa/list")
	protected ModelAndView getList() {
		List<BbsEntity> list = bbsJpaService.getList();
		return new ModelAndView("bbsListJpa", "list", list);
	}
	
	@GetMapping("/jpa/detail/{seq}")
	public ModelAndView detail(@PathVariable("seq") int seq) {
		BbsEntity bbsEntity = bbsJpaService.getDetail(seq);
		return new ModelAndView("detailJpa", "info", bbsEntity);
	}
	
	@GetMapping("/jpa/add")
	protected String add() {
		return "addJpa";
	}
	
	
	@PostMapping("/jpa/add")
	public String add(BbsEntity bbsEntity, @RequestParam("attach") MultipartFile attach) throws Exception {
		String uuid = UUID.randomUUID().toString();
		String filePath = uploadPath + "\\" + uuid;
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
		stream.write(attach.getBytes());
		stream.close();

		bbsEntity.setFileuuid(uuid);
		bbsEntity.setFilename(attach.getOriginalFilename());
		
		bbsJpaService.add(bbsEntity);
		return "bbsListJpa";
	}
}









