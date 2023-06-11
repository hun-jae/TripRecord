package com.ssafy.hotplace.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.hotplace.model.Hotplace;

public interface HotplaceService {
	List<Hotplace> searchList(Map<String, String> map) throws Exception;

	void write(Hotplace hotplace) throws Exception;

	Hotplace view(int bid) throws Exception;

	List<Hotplace> search(String keyword) throws Exception;

	List<Hotplace> searchByContentId(int contentId) throws Exception;

	void update(Hotplace hotplace) throws Exception;

	void delete(int bid) throws Exception;

	void updateHeart(int bid) throws Exception;
}
