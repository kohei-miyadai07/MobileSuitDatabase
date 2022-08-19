package jp.co.sunarch.mobilesuitDatabase.port.adapter.web.controller.mobilesuit;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.from.mobilesuit.MobileSuitDetailFrom;
import jp.co.sunarch.mobilesuitDatabase.port.adapter.web.model.result.mobilesuit.MobileSuitDetailResult;
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

	@GetMapping("/MSDB/MobileSuits/{msId}")
	public String getMobileSuitDetail(@PathVariable String msId, Model model) {
		MobileSuitDetailFrom mobileSuitIdFrom = MobileSuitDetailFrom.of(msId);
		MobileSuitDetailResult msDetailResult = mobileSuitQuery.getMobileSuitDetail(mobileSuitIdFrom);
		model.addAttribute("mobilesuitDetail", msDetailResult);
		return "/MSDB/MobileSuits/msId/MobileSuitDetail";
	}

}
