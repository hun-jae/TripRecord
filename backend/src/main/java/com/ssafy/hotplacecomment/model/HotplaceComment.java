package com.ssafy.hotplacecomment.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HotplaceComment {
	int bid;
	String uid;
	int cid;
	String comment;
	
	public HotplaceComment(int bid, String uid, int cid, String comment) {
		super();
		this.bid = bid;
		this.uid = uid;
		this.cid = cid;
		this.comment = comment;
	}
	public HotplaceComment() {
		super();
	}
}
