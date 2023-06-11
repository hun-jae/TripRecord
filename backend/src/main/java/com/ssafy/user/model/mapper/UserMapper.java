package com.ssafy.user.model.mapper;

import java.sql.SQLException;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.ssafy.user.model.User;

@Mapper
public interface UserMapper {
	User view(String uid) throws SQLException;
	User loginCheck(Map<String, String> map) throws SQLException;

	void update(Map<String, String> map);

	void updatePassword(Map<String, String> map) throws SQLException;

	User findPassword(Map<String, String> map) throws SQLException;
	
	void delete(String uid) throws SQLException;

	void signup(User user) throws SQLException;
	
	void saveRefreshToken(Map<String, String> map) throws SQLException;
	Object getRefreshToken(String uid) throws SQLException;
	void deleteRefreshToken(Map<String, String> map) throws SQLException;
}