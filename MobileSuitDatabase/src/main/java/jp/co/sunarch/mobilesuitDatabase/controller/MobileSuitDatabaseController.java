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

import jp.co.sunarch.mobilesuitDatabase.model.form.MobileSuitArmedEditForm;
import jp.co.sunarch.mobilesuitDatabase.model.form.MobileSuitArmedRegistForm;
import jp.co.sunarch.mobilesuitDatabase.model.form.MobileSuitArmedSearchForm;
import jp.co.sunarch.mobilesuitDatabase.model.form.MobileSuitEditForm;
import jp.co.sunarch.mobilesuitDatabase.model.form.MobileSuitEquipmentEditForm;
import jp.co.sunarch.mobilesuitDatabase.model.form.MobileSuitEquipmentRegistForm;
import jp.co.sunarch.mobilesuitDatabase.model.form.MobileSuitEquipmentSearchForm;
import jp.co.sunarch.mobilesuitDatabase.model.form.MobileSuitRegistForm;
import jp.co.sunarch.mobilesuitDatabase.model.form.MobileSuitSearchForm;
import jp.co.sunarch.mobilesuitDatabase.model.result.MobileSuitArmedResult;
import jp.co.sunarch.mobilesuitDatabase.model.result.MobileSuitDetailResult;
import jp.co.sunarch.mobilesuitDatabase.model.result.MobileSuitEquipmentResult;
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
	
	@GetMapping("/MSDB/MobileSuitArmedList")
	public String getMobileSuitArmedList(Model model) {
		List<MobileSuitArmedResult> msArmedResultList = mobilesuitService.getMobileSuitArmedList();
		model.addAttribute("msArmedResultList", msArmedResultList);
		return "/MSDB/Lists/MobileSuitArmedList";
	}
	
	@GetMapping("/MSDB/MobileSuitEquipmentList")
	public String getMobileSuitEquipmentList(Model model) {
		List<MobileSuitEquipmentResult> msEquipmentResultList = mobilesuitService.getMobileSuitEquipmentList();
		model.addAttribute("msEquipmentResultList", msEquipmentResultList);
		return "/MSDB/Lists/MobileSuitEquipmentList";
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
		model.addAttribute("url", "/MSDB/MobileSuitList");
		return "/MSDB/Registers/RegisterResult";
	}
	
	@GetMapping("/MSDB/MobileSuitArmedRegister")
	public String MobileSuitArmedRegister(Model model) {
		model.addAttribute("msArmedRegistForm", new MobileSuitArmedRegistForm());
		return "/MSDB/Registers/MobileSuitArmedRegister";
	}
	
	@PostMapping("/MSDB/MobileSuitArmedRegister")
	public String MobileSuitArmedRegister(@ModelAttribute MobileSuitArmedRegistForm msArmedRegistForm,
			Model model) {
		String message = mobilesuitService.insertMobileSuitArmed(msArmedRegistForm);
		model.addAttribute("message", message);
		model.addAttribute("url", "/MSDB/MobileSuitArmedList");
		return "/MSDB/Registers/RegisterResult";
	}
	
	@GetMapping("/MSDB/MobileSuitEquipmentRegister")
	public String MobileSuitEquipmentRegister(Model model) {
		MobileSuitEquipmentRegistForm msEquipmentRegistForm = 
				mobilesuitService.getMSEquipmentInfoList(new MobileSuitEquipmentRegistForm());
		model.addAttribute("msEquipmentRegistForm", msEquipmentRegistForm);
		return "/MSDB/Registers/MobileSuitEquipmentRegister";
		
	}
	
	@PostMapping("/MSDB/MobileSuitEquipmentRegister")
	public String MobileSuitEquipmentRegister(@ModelAttribute MobileSuitEquipmentRegistForm msEquipmentRegistForm,
			Model model) {
		String message = mobilesuitService.insertMobileSuitEquipment(msEquipmentRegistForm);
		model.addAttribute("message", message);
		model.addAttribute("url", "/MSDB/MobileSuitEquipmentList");
		return "/MSDB/Registers/RegisterResult";
	}
	
	@GetMapping("/MSDB/MobileSuitEdit/{msId}")
	public String MobileSuitEdit(@PathVariable String msId, Model model) {
		MobileSuitEditForm msEditForm = mobilesuitService.getMobileSuit(msId);
		model.addAttribute("msEditForm", msEditForm);
		return "/MSDB/Edits/MobileSuitEdit";
	}
	
	@PostMapping("/MSDB/MobileSuitEdit/{msId}")
	public String MobileSuitEdit(@PathVariable String msId, @ModelAttribute MobileSuitEditForm msEditForm,
			Model model) {
		msEditForm.setMsId(msId);
		String message = mobilesuitService.updateMobileSuit(msEditForm);
		model.addAttribute("message", message);
		model.addAttribute("url", "/MSDB/MobileSuitList");
		return "/MSDB/Edits/EditResult";
	}
	
	@GetMapping("/MSDB/MobileSuitArmedEdit/{armedId}")
	public String MobileSuitArmedEdit(@PathVariable String armedId, Model model) {
		MobileSuitArmedEditForm msArmedEditForm = mobilesuitService.getMobileSuitArmed(armedId);
		model.addAttribute("msArmedEditForm", msArmedEditForm);
		return "/MSDB/Edits/MobileSuitArmedEdit";
	}
	
	@PostMapping("/MSDB/MobileSuitArmedEdit/{armedId}")
	public String MobileSuitArmedEdit(@PathVariable String armedId, @ModelAttribute MobileSuitArmedEditForm msArmedEditForm,
			Model model) {
		msArmedEditForm.setArmedId(armedId);
		String message = mobilesuitService.updateMobileSuitArmed(msArmedEditForm);
		model.addAttribute("message", message);
		model.addAttribute("url", "/MSDB/MobileSuitArmedList");
		return "/MSDB/Edits/EditResult";
	}
	
	@GetMapping("/MSDB/MobileSuitEquipmentEdit/{equipmentId}")
	public String MobileSuitEquipmentEdit(@PathVariable String equipmentId, Model model) {
		MobileSuitEquipmentEditForm msEquipmentEditForm = mobilesuitService.getMobileSuitEquipment(equipmentId);
		model.addAttribute("msEquipmentEditForm", msEquipmentEditForm);
		return "/MSDB/Edits/MobileSuitEquipmentEdit";
	}
	
	@PostMapping("/MSDB/MobileSuitEquipmentEdit/{equipmentId}")
	public String MobileSuitEquipmentEdit(@PathVariable String equipmentId, @ModelAttribute MobileSuitEquipmentEditForm msEquipmentEditForm,
			Model model) {
		msEquipmentEditForm.setEquipmentId(equipmentId);
		String message = mobilesuitService.updateMobileSuitEquipment(msEquipmentEditForm);
		model.addAttribute("message", message);
		model.addAttribute("url", "/MSDB/MobileSuitEquipmentList");
		return "/MSDB/Edits/EditResult";
	}
	
	@PostMapping("/MSDB/MobileSuitDelete/{msId}")
	public String MobileSuitDelete(@PathVariable String msId, Model model) {
		String message = mobilesuitService.deleteMobileSuit(msId);
		model.addAttribute("message", message);
		model.addAttribute("url", "/MSDB/MobileSuitList");
		return "/MSDB/Deletes/DeleteResult";
	}
	
	@PostMapping("/MSDB/MobileSuitArmedDelete/{armedId}")
	public String MobileSuitArmedDelete(@PathVariable String armedId, Model model) {
		String message = mobilesuitService.deleteArmed(armedId);
		model.addAttribute("message", message);
		model.addAttribute("url", "/MSDB/MobileSuitArmedList");
		return "/MSDB/Deletes/DeleteResult";
	}
	
	@PostMapping("/MSDB/MobileSuitEquipmentDelete/{equipmentId}")
	public String MobileSuitEquipmentDelete(@PathVariable String equipmentId, Model model) {
		String message = mobilesuitService.deleteEquipment(equipmentId);
		model.addAttribute("message", message);
		model.addAttribute("url", "/MSDB/MobileSuitEquipmentList");
		return "/MSDB/Deletes/DeleteResult";
	}
	
	@GetMapping("/MSDB/MobileSuitSearch")
	public String MobileSuitSearch(Model model) {
		model.addAttribute("msSearchForm", new MobileSuitSearchForm());
		return "/MSDB/searches/MobileSuitSearch";
	}
	
	@PostMapping("/MSDB/MobileSuitSearch")
	public String MobileSuitSearch(@ModelAttribute MobileSuitSearchForm msSearchForm, Model model) {
		List<MobileSuitsResult> MobileSuits = mobilesuitService.searchMobileSuit(msSearchForm);
		model.addAttribute("mobilesuits", MobileSuits);
		return "/MSDB/Lists/MobileSuitList";
	}
	
	@GetMapping("/MSDB/MobileSuitArmedSearch")
	public String MobileSuitArmedSearch(Model model) {
		model.addAttribute("msArmedSearchForm", new MobileSuitArmedSearchForm());
		return "/MSDB/searches/MobileSuitArmedSearch";
	}
	
	@PostMapping("/MSDB/MobileSuitArmedSearch")
	public String MobileSuitArmedSearch(@ModelAttribute MobileSuitArmedSearchForm msArmedSearchForm, Model model) {
		List<MobileSuitArmedResult> msArmedResultList = mobilesuitService.searchMobileSuitArmed(msArmedSearchForm);
		model.addAttribute("msArmedResultList", msArmedResultList);
		return "/MSDB/Lists/MobileSuitArmedList";
	}
	
	@GetMapping("/MSDB/MobileSuitEquipmentSearch")
	public String MobileSuitEquipmentSearch(Model model) {
		model.addAttribute("msEquipmentSearchForm", new MobileSuitEquipmentSearchForm());
		return "/MSDB/searches/MobileSuitEquipmentSearch";
	}
	
	@PostMapping("/MSDB/MobileSuitEquipmentSearch")
	public String MobileSuitEquipmentSearch(@ModelAttribute MobileSuitEquipmentSearchForm msEquipmentSearchForm, Model model) {
		List<MobileSuitEquipmentResult> msEquipmentResultList = mobilesuitService.searchMobileSuitEquipment(msEquipmentSearchForm);
		model.addAttribute("msEquipmentResultList", msEquipmentResultList);
		return "/MSDB/Lists/MobileSuitEquipmentList";
	}

}
