package com.ssafy.hotplace.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.hotplace.model.Hotplace;

@Mapper
public interface HotplaceMapper {
	List<Hotplace> searchList(Map<String, Object> param) throws SQLException;

	void write(Hotplace hotplace) throws SQLException;

	Hotplace view(int bid) throws SQLException;

	List<Hotplace> search(String keyword) throws SQLException;

	List<Hotplace> searchByContentId(int contentId) throws Exception;

	void update(Hotplace hotplace) throws SQLException;

	void delete(int bid) throws SQLException;

	void updateHeart(int bid) throws SQLException;
}
