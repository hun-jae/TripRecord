package com.ssafy.trip.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.model.Trip;
import com.ssafy.trip.model.service.TripService;

@RestController
@RequestMapping("/trip")
@CrossOrigin("*")
public class TripController {
	private final Logger logger = LoggerFactory.getLogger(TripController.class);
	private TripService tripService;

	public TripController(TripService tripService) {
		super();
		this.tripService = tripService;
	}

	@GetMapping("/search")
	private ResponseEntity<?> search(@RequestParam Map<String, String> map) {
		List<Trip> trip_list;
		try {
			trip_list = tripService.searchList(map);
			return new ResponseEntity<List<Trip>>(trip_list, HttpStatus.OK);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/search/{bid}")
	private ResponseEntity<?> searchByBid(@PathVariable int bid) {
		List<Trip> trip_list;
		try {
			trip_list = tripService.searchByBid(bid);
			return new ResponseEntity<List<Trip>>(trip_list, HttpStatus.OK);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/searchattr/{contentId}")
	private ResponseEntity<?> searchByContentId(@PathVariable int contentId) {
		Trip trip;
		try {
			trip = tripService.searchByContentId(contentId);
			return new ResponseEntity<Trip>(trip, HttpStatus.OK);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping("/regist")
	private ResponseEntity<?> registAttrForBoard(@RequestBody Map<String, String> map) {
		try {
			System.out.println(map.get("content_id"));
			tripService.registAttrForBoard(map);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/searchsido")
	private ResponseEntity<?> searchSido() {
		List<Map<Integer, String>> sido_code_list;
		try {
			sido_code_list = tripService.searchSido();
			return new ResponseEntity<List<Map<Integer, String>>>(sido_code_list, HttpStatus.OK);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}
	@GetMapping("/deleteattr/{bid}")
	private ResponseEntity<?> deleteAttr(@PathVariable int bid) {
		try {
			tripService.deleteAttrForBoard(bid);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (SQLException e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}