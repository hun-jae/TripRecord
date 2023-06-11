package com.ssafy.hotplacecomment.model.service;

import java.util.List;

import com.ssafy.hotplacecomment.model.HotplaceComment;

public interface HotplaceCommentService {

	void write(HotplaceComment hotplaceComment);

	List<HotplaceComment> selectByBid(int bid);

	List<HotplaceComment> selectByUid(String uid);

	void update(HotplaceComment hotplaceComment);

	void delete(int cid);

}
