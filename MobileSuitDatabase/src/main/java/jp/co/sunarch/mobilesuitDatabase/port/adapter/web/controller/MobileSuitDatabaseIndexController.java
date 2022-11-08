package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MobileSuitDatabaseIndexController {

	@GetMapping("/")
	public String index() {
		return "/ms";
	}
}
