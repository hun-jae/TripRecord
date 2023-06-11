package com.ssafy.trip.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.trip.model.Trip;

public interface TripService {
	List<Trip> searchList(@RequestParam Map<String, String> map) throws SQLException;

	List<Trip> searchByBid(@RequestParam int bid) throws SQLException;

	Trip searchByContentId(@RequestParam int contentId) throws SQLException;

	void registAttrForBoard(@RequestParam Map<String, String> map) throws SQLException;

	List<Map<Integer, String>> searchSido() throws SQLException;
	void deleteAttrForBoard(@RequestParam int bid) throws SQLException;
}
