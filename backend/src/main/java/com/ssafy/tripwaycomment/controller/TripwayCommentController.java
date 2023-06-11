package com.ssafy.tripwaycomment.controller;

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

import com.ssafy.tripwaycomment.model.TripwayComment;
import com.ssafy.tripwaycomment.model.service.TripwayCommentService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/tripwaycomment")
@CrossOrigin("*")
@Api("핫플레이스 댓글 컨트롤러 api V1")
public class TripwayCommentController {
	private static final Logger logger = LoggerFactory.getLogger(TripwayCommentController.class);
	
	private TripwayCommentService tripwayCommentService;

	public TripwayCommentController(TripwayCommentService tripwayCommentService) {
		this.tripwayCommentService = tripwayCommentService;
	}
	
	@PostMapping("/write")
	public ResponseEntity<?> write(@RequestBody TripwayComment tripwayComment){
		logger.debug("write tripwayComment : {}", tripwayComment);
		try{
			tripwayCommentService.write(tripwayComment);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e){
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/selectbybid")
	public ResponseEntity<?> selectByBid(@RequestParam int bid){
		try{
			List<TripwayComment> tripwayComment = tripwayCommentService.selectByBid(bid);
			return new ResponseEntity<List<TripwayComment>>(tripwayComment, HttpStatus.OK);
		} catch(Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/selectbyuid")
	public ResponseEntity<?> selectByUid(@RequestParam String uid){
		try{
			List<TripwayComment> tripwayComment = tripwayCommentService.selectByUid(uid);
			return new ResponseEntity<List<TripwayComment>>(tripwayComment, HttpStatus.OK);
		} catch(Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody TripwayComment tripwayComment){
		try {
			tripwayCommentService.update(tripwayComment);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch(Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam int cid){
		try {
			tripwayCommentService.delete(cid);
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
