package com.ssafy.tripwaycomment.model.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.ssafy.tripwaycomment.model.TripwayComment;

@Mapper
public interface TripwayCommentMapper {
	void write(TripwayComment tripwayComment);
	List<TripwayComment> selectByBid(int bid);
	List<TripwayComment> selectByUid(String uid);
	void update(TripwayComment tripwayComment);
	void delete(int cid);
}
