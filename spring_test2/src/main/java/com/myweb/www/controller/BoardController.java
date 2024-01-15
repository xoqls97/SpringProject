package com.myweb.www.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.FileHandler;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequestMapping("/board/*")
@RequiredArgsConstructor
@Controller
public class BoardController {
	
	private final BoardService bsv;
	private final FileHandler fh;
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String insert(BoardVO bvo, @RequestParam(name="file", required = false) MultipartFile[] file) {
		log.info(">>>bvo>>>{}",bvo);
		List<FileVO> flist = null;
		//fileHandler 생성 - multipartfile 값이 input 되면 => flist가 output 될 수 있게
		if(file[0].getSize()>0) {
			flist = fh.uploadFiles(file);
		}
		int isOk= bsv.insert(new BoardDTO(bvo,flist));
		
		return "index";
		
	}

	@GetMapping("/list")
	public void list(Model m, PagingVO pgvo) {
		
		//페이징 처리
		List<BoardVO> list = bsv.getList(pgvo);
		//totalCount 구하기
		int totalCount = bsv.getTotalCount(pgvo);
		PagingHandler ph = new PagingHandler(pgvo,totalCount);
		m.addAttribute("ph",ph);
		m.addAttribute("list",list);
	}
	
	@GetMapping({"/detail", "/modify"})
	public void detail(Model m, @RequestParam("bno") int bno) {
		log.info(">>>>bno>"+bno);
		m.addAttribute("bdto", bsv.getDetail(bno));
	}
	@PostMapping("/modify")
	public String modify(BoardVO bvo, RedirectAttributes re,
			@RequestParam(name="file", required= false)MultipartFile[] file
			) {
		List<FileVO> flist = null;
		if(file[0].getSize()>0) {
			flist = fh.uploadFiles(file);
		}
		int isOk=bsv.modify(new BoardDTO (bvo,flist));
		re.addAttribute("bno",bvo.getBno());
		return "redirect:/board/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("bno") int bno) {
		
		int isOk=bsv.delete(bno);
		
		return "redirect:/board/list";
	}
	
	@DeleteMapping(value="/deletefile" ,consumes="application/json",produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deletefile(@RequestBody FileVO fvo){
		log.info(">>> uuid>>{}",fvo.getUuid());
		int isOk = bsv.deletefile(fvo.getUuid());
		return isOk>0? new ResponseEntity<String>("1",HttpStatus.OK) 
				: new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
}
