package com.ssafy.trip.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.trip.model.Trip;

@Mapper
public interface TripMapper {

	List<Trip> searchList(@RequestParam Map<String, Object> map) throws SQLException;

	List<Trip> searchByBid(@RequestParam int bid) throws SQLException;

	Trip searchByContentId(@RequestParam int contentId) throws SQLException;

	void registAttrForBoard(@RequestParam Map<String, String> map) throws SQLException;

	List<Map<Integer, String>> searchSido() throws SQLException;
	
	void deleteAttrForBoard(int bid) throws SQLException;

}
