package com.ssafy.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.user.model.User;
import com.ssafy.user.model.mapper.UserMapper;


public class IUserDaoTest {
	 private static final Logger logger = LoggerFactory.getLogger(IUserDaoTest.class);

   @Autowired
   DataSource ds;
   
   @Autowired
   UserMapper userMapper;
   
   /**
    * DataSource 객체, IBookDao 객체 BeanCreation 확인
    */
   @Test
   public void testBeanCreation() {
       assertNotNull(ds);
       assertNotNull(userMapper);
   }
   
//   @Test
//   public void testSignUp() throws SQLException {
//       //before : book data 생성
//       User user = new User("ssafy1", "ssafy1", "김싸피", "ssafy@ssafy.com", 15);
//       
//       //when
//       userMapper.signup(user);
//       user = userMapper.view("ssafy1");
//       
//       //then
//       assertNotNull(user);
//       assertEquals(user.getName(), "김싸피");
//       assertEquals(user.getAge(), 15);
//       
//       userMapper.delete("ssafy1");
//       user = userMapper.view("ssafy1");
//       assertNull(user);
//   }
   
}
