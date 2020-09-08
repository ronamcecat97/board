package com.bbs.board.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bbs.board.dto.BbsDto;
import com.bbs.board.service.BbsService;

@Controller
public class BbsController {

	@Autowired
	BbsService bbsService;
	
	@Value("${fileupload.path}")
	private String uploadPath;
	
	@GetMapping({ "/", "/{page}"})
	public ModelAndView list(@PathVariable(value = "page", required=false) String page) throws Exception {
		long nPage = 1;
		try {
			nPage = Integer.parseInt(page);
		} catch(Exception ex) {
			nPage = 1;
		}
		
		List<BbsDto> list = bbsService.getList(nPage);
		return new ModelAndView("bbsList", "list", list);
	}
	
	@GetMapping("/add")
	public String add() {
		return "add";
	}
	
	@PostMapping("/add")
	public ModelAndView add(BbsDto dto, @RequestParam("attach") MultipartFile attach) throws Exception {
		String uuid = UUID.randomUUID().toString();
		String filePath = uploadPath + "\\" + uuid;
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
		stream.write(attach.getBytes());
		stream.close();

		dto.setFileUuid(uuid);
		dto.setFileName(attach.getOriginalFilename());
		
		int nCnt = bbsService.addDoc(dto);

		
		List<BbsDto> list = bbsService.getList(1);
		return new ModelAndView("bbsList", "list", list);
	}
	
	
	@GetMapping("/filedown/{seq}")
	public void filedown(@PathVariable("seq") String seq, HttpServletResponse response) throws Exception {
		BbsDto bbsDto = bbsService.getFileUuid(seq);
		String filename = uploadPath + "\\" + bbsDto.getFileUuid();
		String oriName = URLEncoder.encode(bbsDto.getFileName(), "UTF-8").replaceAll("\\+", "%20");
		
		response.setHeader("Content-Disposition", "attachment;filename=" + oriName + ";");
		response.setContentType("text/plain");
		
		File file = new File(filename);
		FileInputStream stream = new FileInputStream(file);
		FileCopyUtils.copy(stream, response.getOutputStream());
		
		response.flushBuffer();
	}
	
	
	
	@PostMapping("/rest/searchList")
	@ResponseBody
	List<BbsDto> getSearchList(@RequestBody BbsDto bbsDto) {
		List<BbsDto> list = bbsService.getSearchList(bbsDto);
		return list;
	}
	
	@GetMapping("/detail/{seq}")
	public ModelAndView detail(@PathVariable("seq") String seq) {
		return new ModelAndView("detail", "seq", seq);
	}

	@GetMapping("/rev")
	public ModelAndView rev(@RequestParam("seq") int seq, Model model) {
		BbsDto inDto = new BbsDto();
		inDto.setSeq(seq);
		BbsDto bbsDto = bbsService.getDetail(inDto);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("seq", seq);
		modelAndView.addObject("info", bbsDto);
		
		modelAndView.setViewName("rev");
		return modelAndView;
	}
}












