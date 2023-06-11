package com.ssafy.user.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.jwt.JwtServiceImpl;
import com.ssafy.user.model.User;
import com.ssafy.user.model.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private JwtServiceImpl jwtService;

	@PostMapping(value = "/signup")
	public ResponseEntity<?> signUp(@RequestBody User user) {
		System.out.println("회원가입 " + user);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		try {
			userService.signup(user);
			resultMap.put("userInfo", user);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> map) {
		logger.debug("회원 정보 {},{} 로 로그인", map.get("uid"), map.get("pw"));
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			System.out.println("UserController 46 : 아이디 " + map.get("uid") + " 비번 " + map.get("pw"));
			User user = userService.loginCheck(map);
			if (user != null) {
				String accessToken = jwtService.createAccessToken("uid", user.getUid());// key, data
				String refreshToken = jwtService.createRefreshToken("uid", user.getUid());// key, data
				userService.saveRefreshToken(user.getUid(), refreshToken);
				logger.debug("로그인 accessToken 정보 : {}", accessToken);
				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("userInfo", user);
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@GetMapping(value = "/view/{uid}")
	public ResponseEntity<?> view(@PathVariable String uid, HttpServletRequest request) {
		logger.debug("회원 정보 {} 로 상세정보 보기", uid);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if(jwtService.checkToken(request.getHeader("access-token"))) {
			try {
				//로그인 사용자 정보
				User user = userService.view(uid);
				resultMap.put("userInfo", user);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}catch(Exception e) {
				logger.error("정보조회 실패: {}", e.getMessage());
				resultMap.put(",essage", e.getMessage());
				status = HttpStatus.UNAUTHORIZED;
			}
		}else {
			logger.error("사용 불가능 토큰!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);



	}

	@PostMapping(value = "/update")
	public ResponseEntity<?> update(@RequestBody Map<String, String> map) {
		logger.debug("회원 아이디 {} 정보 수정하기", map.get("uid"));
		try {
			userService.update(map);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping(value = "/updatepass")
	public ResponseEntity<?> updatepass(@RequestBody Map<String, String> map) {
		logger.debug("회원 비밀번호 {} 정보 수정하기", map.get("pw"));
		try {
			userService.updatePassword(map);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping(value = "/delete/{uid}")
	public ResponseEntity<?> delete(@PathVariable String uid) {
		logger.debug("회원 비밀번호 {} 정보 수정하기", uid);
		try {
			userService.delete(uid);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody User user, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		logger.debug("token : {}, user : {}", token, user);
		if (jwtService.checkToken(token)) {
			if (token.equals(userService.getRefreshToken(user.getUid()))) {
				String accessToken = jwtService.createAccessToken("userid", user.getUid());
				logger.debug("token : {}", accessToken);
				logger.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		} else {
			logger.debug("리프레쉬토큰도 사용불가!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	/* 로그아웃 */
	@GetMapping("/logout/{uid}")
	public ResponseEntity<?> removeToken(@PathVariable String uid) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			userService.deleRefreshToken(uid);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		}catch(Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
