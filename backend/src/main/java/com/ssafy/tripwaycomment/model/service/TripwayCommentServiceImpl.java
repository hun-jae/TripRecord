package com.ssafy.tripwaycomment.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.tripwaycomment.model.TripwayComment;
import com.ssafy.tripwaycomment.model.mapper.TripwayCommentMapper;

@Service
public class TripwayCommentServiceImpl implements TripwayCommentService {
	
	private TripwayCommentMapper tripwayCommentMapper;
	
	public TripwayCommentServiceImpl(TripwayCommentMapper tripwayCommentMapper) {
		this.tripwayCommentMapper = tripwayCommentMapper;
	}

	@Override
	public void write(TripwayComment tripwayComment) {
		tripwayCommentMapper.write(tripwayComment);
	}

	@Override
	public List<TripwayComment> selectByBid(int bid) {
		return tripwayCommentMapper.selectByBid(bid);
	}

	@Override
	public List<TripwayComment> selectByUid(String uid) {
		return tripwayCommentMapper.selectByUid(uid);
	}

	@Override
	public void update(TripwayComment tripwayComment) {
		tripwayCommentMapper.update(tripwayComment);

	}

	@Override
	public void delete(int cid) {
		tripwayCommentMapper.delete(cid);
	}

}
