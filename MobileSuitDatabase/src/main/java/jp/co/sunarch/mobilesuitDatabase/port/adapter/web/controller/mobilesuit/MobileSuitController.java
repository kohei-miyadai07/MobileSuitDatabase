package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.mobilesuit.MobileSuitModel;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MobileSuitController {
	
	private final MobileSuitQuery mobileSuitOuery;
	
	@GetMapping("/MSDB/MobileSuits")
	public String getMobileSuits(Model model) {
		List<MobileSuitModel> msResponse = mobileSuitOuery.findAll();
		model.addAttribute("mobilesuits", msResponse);
		return "/MSDB/MobileSuits/MobileSuitList";
	}

}
