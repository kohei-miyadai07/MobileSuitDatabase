package jp.co.sunarch.mobilesuitDatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MobileSuitDatabaseController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/")
	public String getMessage() {
		String result = jdbcTemplate.queryForObject("select 1",String.class);
		System.out.println(result);
		return "/MobileSuitDatabase/ms";
	}

}
