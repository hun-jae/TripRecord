package com.ssafy.tripwaycomment.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TripwayComment {
	int bid;
	String uid;
	int cid;
	String comment;
	
	public TripwayComment(int bid, String uid, int cid, String comment) {
		super();
		this.bid = bid;
		this.uid = uid;
		this.cid = cid;
		this.comment = comment;
	}
	public TripwayComment() {
		super();
	}
}
