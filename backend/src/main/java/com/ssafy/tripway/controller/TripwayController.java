package com.ssafy.tripway.controller;

import java.util.List;
import java.util.Map;

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

import com.ssafy.tripway.model.Tripway;
import com.ssafy.tripway.model.service.TripwayService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/tripway")
@CrossOrigin("*")
@Api("핫플레이스 컨트롤러 api V1")
public class TripwayController {
	private static final Logger logger = LoggerFactory.getLogger(TripwayController.class);
	
	private TripwayService tripwayService;

	public TripwayController(TripwayService tripwayService) {
		this.tripwayService = tripwayService;
	}

	@PostMapping("/write")
	public ResponseEntity<?> write(@RequestBody Tripway tripway){
		logger.debug("write tripway : {}", tripway);
		try {
			tripwayService.write(tripway);
			return new ResponseEntity<Integer>(tripway.getBid(), HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
		@GetMapping("/search")
	public ResponseEntity<?> search(@RequestParam String keyword) {
		List<Tripway> tripway_list;
		try {
			tripway_list = tripwayService.search(keyword);
			return new ResponseEntity<List<Tripway>>(tripway_list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/searchlist")
	public ResponseEntity<?> searchList(@RequestParam Map<String, String> map){
		List<Tripway> tripway_list;
		try {
			tripway_list = tripwayService.searchList(map);
			return new ResponseEntity<List<Tripway>>(tripway_list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/view")
	public ResponseEntity<?> view(@RequestParam int bid){
//		logger.debug("update tripway : {}", tripway);

		try {
			return new ResponseEntity<Tripway>(tripwayService.view(bid), HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody Tripway tripway){
		logger.debug("update tripway : {}", tripway);
		try {
			tripwayService.update(tripway);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam int bid){
//		logger.debug("update tripway : {}", tripway);

		try {
			System.out.println(bid);
			tripwayService.delete(bid);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PostMapping("/updateheart")
	public ResponseEntity<?> updateHeart(@RequestBody Tripway tripway){
//		logger.debug("update tripway : {}", tripway);
		try {
			tripwayService.updateHeart(tripway.getBid());
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
