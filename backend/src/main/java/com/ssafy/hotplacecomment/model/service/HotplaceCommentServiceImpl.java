package com.ssafy.hotplacecomment.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.hotplacecomment.model.HotplaceComment;
import com.ssafy.hotplacecomment.model.mapper.HotplaceCommentMapper;

@Service
public class HotplaceCommentServiceImpl implements HotplaceCommentService {
	private HotplaceCommentMapper hotplaceCommentMapper;
	
	@Autowired
	public HotplaceCommentServiceImpl(HotplaceCommentMapper hotplaceCommentMapper) {
		this.hotplaceCommentMapper = hotplaceCommentMapper;
	}

	@Override
	public void write(HotplaceComment hotplaceComment) {
		hotplaceCommentMapper.write(hotplaceComment);
	}

	@Override
	public List<HotplaceComment> selectByBid(int bid) {
		return hotplaceCommentMapper.selectByBid(bid);
	}

	@Override
	public List<HotplaceComment> selectByUid(String uid) {
		return hotplaceCommentMapper.selectByUid(uid);
	}

	@Override
	public void update(HotplaceComment hotplaceComment) {
		hotplaceCommentMapper.update(hotplaceComment);

	}

	@Override
	public void delete(int cid) {
		hotplaceCommentMapper.delete(cid);
	}

}
