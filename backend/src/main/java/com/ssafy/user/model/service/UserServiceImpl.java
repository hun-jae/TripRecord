package com.ssafy.user.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.user.model.User;
import com.ssafy.user.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	private UserMapper userMapper;

	public UserServiceImpl(UserMapper userMapper) {
		super();
		this.userMapper = userMapper;
	}


	@Override
	public User view(String uid) throws Exception {
		return userMapper.view(uid);
	}

	@Override
	public User loginCheck(Map<String, String> map) throws Exception {
		return userMapper.loginCheck(map);
	}
	
	@Override
	public void update(Map<String, String> map) throws Exception {
		userMapper.update(map);
	}

	@Override
	public void updatePassword(Map<String, String> map) throws Exception {
		userMapper.updatePassword(map);
	}

	@Override
	public void delete(String uid) throws Exception {
		userMapper.delete(uid);
	}

	@Override
	public void signup(User user) throws Exception {
		userMapper.signup(user);
	}

	@Override
	public User findPassword(Map<String, String> map) throws Exception {
		return userMapper.findPassword(map);
	}

	@Override
	public void saveRefreshToken(String uid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("uid", uid);
		map.put("token", refreshToken);
		userMapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String uid) throws Exception {
		return userMapper.getRefreshToken(uid);

	}

	@Override
	public void deleRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", null);
		userMapper.deleteRefreshToken(map);
	}



	
}
