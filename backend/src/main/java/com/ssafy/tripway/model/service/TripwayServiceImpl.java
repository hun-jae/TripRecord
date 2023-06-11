package com.ssafy.tripway.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.tripway.util.SizeConstant;
import com.ssafy.tripway.model.Tripway;
import com.ssafy.tripway.model.mapper.TripwayMapper;

@Service
public class TripwayServiceImpl implements TripwayService {
	private TripwayMapper tripwayMapper;

	public TripwayServiceImpl(TripwayMapper tripwayMapper) {
		super();
		this.tripwayMapper = tripwayMapper;
	}

	@Override
	public List<Tripway> searchList(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String search_code = map.get("search_code");
		param.put("search_code", search_code == null ? "" : search_code);
		param.put("keyword", map.get("keyword") == null ? "" : map.get("keyword"));
		int pgNo = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);
		
		return tripwayMapper.searchList(param);
	}

	@Override
	public void write(Tripway tripway) throws Exception {
		tripwayMapper.write(tripway);
	}

	@Override
	public Tripway view(int bid) throws Exception {
		return tripwayMapper.view(bid);
	}

	@Override
	public void update(Tripway tripway) throws Exception {
		tripwayMapper.update(tripway);

	}

	@Override
	public void delete(int bid) throws Exception {
		tripwayMapper.delete(bid);

	}

	@Override
	public void updateHeart(int bid) throws Exception {
		tripwayMapper.updateHeart(bid);
		
	}

		@Override
	public List<Tripway> search(String keyword) throws Exception {
		return tripwayMapper.search(keyword);
	}

}
