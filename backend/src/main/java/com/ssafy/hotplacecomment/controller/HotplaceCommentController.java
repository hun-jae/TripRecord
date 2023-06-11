package com.ssafy.hotplacecomment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.hotplace.controller.HotplaceController;
import com.ssafy.hotplacecomment.model.HotplaceComment;
import com.ssafy.hotplacecomment.model.service.HotplaceCommentService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/hotplacecomment")
@CrossOrigin("*")
@Api("핫플레이스 댓글 컨트롤러 api V1")
public class HotplaceCommentController {
	private static final Logger logger = LoggerFactory.getLogger(HotplaceController.class);
	
	private HotplaceCommentService hotplaceCommentService;

	@Autowired
	public HotplaceCommentController(HotplaceCommentService hotplaceCommentService) {
		this.hotplaceCommentService = hotplaceCommentService;
	}
	
	@PostMapping("/write")
	public ResponseEntity<?> write(@RequestBody HotplaceComment hotplaceComment){
		logger.debug("write hotplaceComment : {}", hotplaceComment);
		try{
			hotplaceCommentService.write(hotplaceComment);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e){
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/selectbybid")
	public ResponseEntity<?> selectByBid(@RequestParam int bid){
		try{
			List<HotplaceComment> hotplaceComment = hotplaceCommentService.selectByBid(bid);
			return new ResponseEntity<List<HotplaceComment>>(hotplaceComment, HttpStatus.OK);
		} catch(Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/selectbyuid")
	public ResponseEntity<?> selectByUid(@RequestParam String uid){
		try{
			List<HotplaceComment> hotplaceComment = hotplaceCommentService.selectByUid(uid);
			return new ResponseEntity<List<HotplaceComment>>(hotplaceComment, HttpStatus.OK);
		} catch(Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody HotplaceComment hotplaceComment){
		try {
			hotplaceCommentService.update(hotplaceComment);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch(Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam int cid){
		try {
			hotplaceCommentService.delete(cid);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch(Exception e) {
			return exceptionHandling(e);
		}
	}
	
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
