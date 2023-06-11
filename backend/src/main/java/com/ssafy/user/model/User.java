package com.ssafy.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {
	private String uid;
	private String pw;
	private String name;
	private String email;
	private int age;	
}
