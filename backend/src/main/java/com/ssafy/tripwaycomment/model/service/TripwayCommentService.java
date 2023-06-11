package com.ssafy.tripwaycomment.model.service;

import java.util.List;

import com.ssafy.tripwaycomment.model.TripwayComment;

public interface TripwayCommentService {

	void write(TripwayComment tripwayComment);

	List<TripwayComment> selectByBid(int bid);

	List<TripwayComment> selectByUid(String uid);

	void update(TripwayComment tripwayComment);

	void delete(int cid);

}
