package com.ssafy.hotplace.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Hotplace {
	private int bid;
	private String uid;
	private String title;
	private String content;
	private int numHeart;
	private int contentId;
	private String imgSrc;

	public Hotplace(int bid, String uid, String title, String content, int numHeart, int contentId, String imgSrc) {
		super();
		this.bid = bid;
		this.uid = uid;
		this.title = title;
		this.content = content;
		this.numHeart = numHeart;
		this.contentId = contentId;
		this.imgSrc = imgSrc;
	}

	public Hotplace() {
		super();
	}
}
