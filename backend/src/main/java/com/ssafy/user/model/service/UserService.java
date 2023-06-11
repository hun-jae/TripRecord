package com.ssafy.user.model.service;

import java.util.Map;

import com.ssafy.user.model.User;

public interface UserService {
	User view(String uid) throws Exception;
	User loginCheck(Map<String, String> map) throws Exception;
	void update(Map<String, String> map) throws Exception;
	void updatePassword(Map<String, String> map) throws Exception;
	User findPassword(Map<String, String> map) throws Exception;
	void delete(String uid) throws Exception;
	void signup(User user) throws Exception;
	
	// 토큰
	void saveRefreshToken(String uid, String refreshToken) throws Exception;
	Object getRefreshToken(String uid) throws Exception;
	void deleRefreshToken(String uid) throws Exception;
}
