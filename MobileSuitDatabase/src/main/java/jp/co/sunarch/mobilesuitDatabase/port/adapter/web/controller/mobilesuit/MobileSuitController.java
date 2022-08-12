package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.result.mobilesuit.MobileSuitResult;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MobileSuitController {
	
	private final MobileSuitQuery mobileSuitQuery;
	
	@GetMapping("/MSDB/MobileSuits")
	public String getMobileSuits(Model model) {
		List<MobileSuitResult> msResultList = mobileSuitQuery.getMobileSuitList();
		model.addAttribute("mobilesuits", msResultList);
		return "/MSDB/MobileSuits/MobileSuitList";
	}

}
