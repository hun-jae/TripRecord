package com.ssafy.hotplace.controller;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.hotplace.model.Hotplace;
import com.ssafy.hotplace.model.service.HotplaceService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/hotplace")
@CrossOrigin("*")
@Api("핫플레이스 컨트롤러 api V1")
public class HotplaceController {
	private static final Logger logger = LoggerFactory.getLogger(HotplaceController.class);

	private HotplaceService hotplaceService;

	@Autowired
	public HotplaceController(HotplaceService hotplaceService) {
		this.hotplaceService = hotplaceService;
	}

	@PostMapping("/write")
	public ResponseEntity<?> write(@RequestBody Hotplace hotplace) {
		logger.debug("write hotplace : {}", hotplace);
		try {
			hotplaceService.write(hotplace);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/searchlist")
	public ResponseEntity<?> searchList(@RequestParam Map<String, String> map) {
		List<Hotplace> hotplace_list;
		try {
			hotplace_list = hotplaceService.searchList(map);
			return new ResponseEntity<List<Hotplace>>(hotplace_list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/search")
	public ResponseEntity<?> search(@RequestParam String keyword) {
		List<Hotplace> hotplace_list;
		try {
			hotplace_list = hotplaceService.search(keyword);
			return new ResponseEntity<List<Hotplace>>(hotplace_list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/search/{contentId}")
	public ResponseEntity<?> searchByContentId(@PathVariable int contentId) {
		List<Hotplace> hotplace_list;
		try {
			hotplace_list = hotplaceService.searchByContentId(contentId);
			return new ResponseEntity<List<Hotplace>>(hotplace_list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/view")
	public ResponseEntity<?> view(@RequestParam int bid) {
		// logger.debug("update hotplace : {}", hotplace);

		try {
			return new ResponseEntity<Hotplace>(hotplaceService.view(bid), HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody Hotplace hotplace) {
		logger.debug("update hotplace : {}", hotplace);
		try {
			hotplaceService.update(hotplace);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam int bid) {
		// logger.debug("update hotplace : {}", hotplace);

		try {
			hotplaceService.delete(bid);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping("/updateheart")
	public ResponseEntity<?> updateHeart(@RequestBody Hotplace hotplace) {
		// logger.debug("update hotplace : {}", hotplace);
		try {
			hotplaceService.updateHeart(hotplace.getBid());
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
