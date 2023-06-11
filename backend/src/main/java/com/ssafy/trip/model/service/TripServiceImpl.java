package com.ssafy.trip.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.trip.model.Trip;
import com.ssafy.trip.model.mapper.TripMapper;

@Service
public class TripServiceImpl implements TripService {

	private TripMapper tripMapper;

	public TripServiceImpl(TripMapper tripMapper) {
		super();
		this.tripMapper = tripMapper;
	}

	@Override
	public List<Trip> searchList(Map<String, String> map) throws SQLException {
		Map<String, Object> param = new HashMap<String, Object>();
		int sido_code = Integer.parseInt(
				(map.get("sido_code") == null || map.get("sido_code").equals("")) ? "0" : map.get("sido_code"));
		int content_type_id = Integer
				.parseInt((map.get("content_type_id") == null || map.get("content_type_id").equals("")) ? "0"
						: map.get("content_type_id"));

		param.put("sido_code", sido_code);
		param.put("content_type_id", content_type_id);
		param.put("keyword", map.get("keyword") == null ? "" : map.get("keyword"));

		return tripMapper.searchList(param);
	}

	@Override
	public List<Trip> searchByBid(int bid) throws SQLException {
		return tripMapper.searchByBid(bid);
	}

	@Override
	public void registAttrForBoard(Map<String, String> map) throws SQLException {
		tripMapper.registAttrForBoard(map);
	}

	@Override
	public List<Map<Integer, String>> searchSido() throws SQLException {
		return tripMapper.searchSido();
	}

	@Override
	public Trip searchByContentId(int contentId) throws SQLException {
		return tripMapper.searchByContentId(contentId);
	}

	@Override
	public void deleteAttrForBoard(int bid) throws SQLException {
		tripMapper.deleteAttrForBoard(bid);
	}


}
