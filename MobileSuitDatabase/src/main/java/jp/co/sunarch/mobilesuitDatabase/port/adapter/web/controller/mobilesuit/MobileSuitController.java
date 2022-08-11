package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MobileSuitController {
	
	@GetMapping("/MSDB/MobileSuits")
	public String getMobileSuits(Model model) {
		return "/MSDB/MobileSuits/MobileSuitList";
	}

}
