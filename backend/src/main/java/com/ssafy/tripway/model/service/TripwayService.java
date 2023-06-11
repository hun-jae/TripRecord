package com.ssafy.tripway.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.tripway.model.Tripway;

public interface TripwayService {
	List<Tripway> searchList(Map<String, String> map) throws Exception;
	void write(Tripway tripway) throws Exception;
	Tripway view(int bid) throws Exception;
	void update(Tripway tripway) throws Exception;
	void delete(int bid) throws Exception;
	void updateHeart(int bid) throws Exception;
	List<Tripway> search(String keyword) throws Exception;
}
