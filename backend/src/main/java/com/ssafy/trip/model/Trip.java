package com.ssafy.trip.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Trip {
	private int contentId;
	private int contentTypeId;
	private String title;
	private int sidoCode;
	private String imgSrc;
	private String addr;
	private String tel;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private int day;
	private int idx;
	
	@Builder
	public Trip(int contentId, int contentTypeId, String title, int sidoCode, String imgSrc, String addr, String tel,
			BigDecimal latitude, BigDecimal longitude, int day, int idx) {
		this.contentId = contentId;
		this.contentTypeId = contentTypeId;
		this.title = title;
		this.sidoCode = sidoCode;
		this.imgSrc = imgSrc;
		this.addr = addr;
		this.tel = tel;
		this.latitude = latitude;
		this.longitude = longitude;
		this.day = day;
		this.idx = idx;
	}
	
	public Trip() {
	
	}
	
	
}
