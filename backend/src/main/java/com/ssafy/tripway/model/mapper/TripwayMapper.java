package com.ssafy.tripway.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.tripway.model.Tripway;

@Mapper
public interface TripwayMapper {
	List<Tripway> searchList(Map<String, Object> param) throws SQLException;
	void write(Tripway tripway) throws SQLException;
	Tripway view(int bid) throws SQLException;
	void update(Tripway tripway) throws SQLException;
	void delete(int bid) throws SQLException;
	void updateHeart(int bid) throws SQLException;
		List<Tripway> search(String keyword) throws SQLException;

}
