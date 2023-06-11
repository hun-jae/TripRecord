package com.ssafy.hotplace.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.hotplace.model.Hotplace;
import com.ssafy.hotplace.model.mapper.HotplaceMapper;
import com.ssafy.hotplace.util.SizeConstant;

@Service
public class HotplaceServiceImpl implements HotplaceService {
	private HotplaceMapper hotplaceMapper;

	public HotplaceServiceImpl(HotplaceMapper hotplaceMapper) {
		super();
		this.hotplaceMapper = hotplaceMapper;
	}

	@Override
	public List<Hotplace> searchList(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String search_code = map.get("search_code");
		param.put("search_code", search_code == null ? "" : search_code);
		param.put("keyword", map.get("keyword") == null ? "" : map.get("keyword"));
		int pgNo = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);
		// System.out.println(param.get("search_code"));
		// System.out.println(param.get("keyword"));
		return hotplaceMapper.searchList(param);
	}

	@Override
	public void write(Hotplace hotplace) throws Exception {
		hotplaceMapper.write(hotplace);
	}

	@Override
	public Hotplace view(int bid) throws Exception {
		return hotplaceMapper.view(bid);
	}

	@Override
	public void update(Hotplace hotplace) throws Exception {
		hotplaceMapper.update(hotplace);

	}

	@Override
	public void delete(int bid) throws Exception {
		hotplaceMapper.delete(bid);

	}

	@Override
	public void updateHeart(int bid) throws Exception {
		hotplaceMapper.updateHeart(bid);

	}

	@Override
	public List<Hotplace> search(String keyword) throws Exception {
		return hotplaceMapper.search(keyword);
	}

	@Override
	public List<Hotplace> searchByContentId(int contentId) throws Exception {
		return hotplaceMapper.searchByContentId(contentId);

	}

}
