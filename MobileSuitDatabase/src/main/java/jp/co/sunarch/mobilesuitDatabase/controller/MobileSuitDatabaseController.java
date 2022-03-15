package jp.co.sunarch.mobilesuitDatabase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sunarch.mobilesuitDatabase.model.form.MobileSuitRegistForm;
import jp.co.sunarch.mobilesuitDatabase.model.result.MobileSuitDetailResult;
import jp.co.sunarch.mobilesuitDatabase.model.result.MobileSuitsResult;
import jp.co.sunarch.mobilesuitDatabase.service.MobileSuitService;

@Controller
public class MobileSuitDatabaseController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private MobileSuitService mobilesuitService;
	
	@GetMapping("/")
	public String getMessage() {
		String result = jdbcTemplate.queryForObject("select 1",String.class);
		System.out.println(result);
		return "/MSDB/ms";
	}
	
	@GetMapping("/MSDB/MobileSuitList")
	public String getMoblieSuits(Model model) {
		List<MobileSuitsResult> MobileSuits = mobilesuitService.getMobileSuits();
		model.addAttribute("mobilesuits", MobileSuits);
		return "/MSDB/Lists/MobileSuitList";
	}
	
	@GetMapping("/MSDB/MobileSuitDetail/{msName}")
	public String getMobileSuitDetail(@PathVariable("msName")String msName, Model model) {
		MobileSuitDetailResult mobilesuitDetail = mobilesuitService.getMobileSuitDetail(msName);
		model.addAttribute("mobilesuitDetail", mobilesuitDetail);
		return "/MSDB/Details/MobileSuitDetail";
	}
	
	@GetMapping("/MSDB/MobileSuitRegister")
	public String MobileSuitRegister(Model model) {
		model.addAttribute("msRegistForm", new MobileSuitRegistForm());
		return "/MSDB/Registers/MobileSuitRegister";
	}
	
	@PostMapping("/MSDB/MobileSuitRegister")
	public String MobileSuitRegister(@ModelAttribute MobileSuitRegistForm msRegistForm, 
			Model model) {
		String message = mobilesuitService.insertMobileSuit(msRegistForm);
		model.addAttribute("message", message);
		return "/MSDB/Registers/RegisterResult";
	}

}
