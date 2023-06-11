package com.ssafy.tripway.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Tripway {
	private int bid;
	private String uid;
	private String title;
	private String content;
	private int numHeart;
	private int day;
	private String imgSrc;
	
	public Tripway(int bid, String uid, String title, String content, int numHeart, int day, String imgSrc) {
		super();
		this.bid = bid;
		this.uid = uid;
		this.title = title;
		this.content = content;
		this.numHeart = numHeart;
		this.day = day;
		this.imgSrc = imgSrc;
	}
	public Tripway() {
		super();
	}
}
