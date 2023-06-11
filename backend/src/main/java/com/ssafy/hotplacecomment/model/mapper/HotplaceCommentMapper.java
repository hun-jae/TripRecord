package com.ssafy.hotplacecomment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.hotplacecomment.model.HotplaceComment;

@Mapper
public interface HotplaceCommentMapper {
	void write(HotplaceComment hotplaceComment);
	List<HotplaceComment> selectByBid(int bid);
	List<HotplaceComment> selectByUid(String uid);
	void update(HotplaceComment hotplaceComment);
	void delete(int cid);
}
