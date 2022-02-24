package com.csr.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csr.bean.DistrictBean;
import com.csr.bean.HobliBean;
import com.csr.bean.StateBean;
import com.csr.bean.TalukBean;
import com.csr.bean.VillageBean;
import com.csr.service.RegionService;

@Controller
@RequestMapping("/Region")
public class RegionController {
	
	@Autowired
	private RegionService service;
	
	
	@ModelAttribute("stateOption")
	public Map<Integer,String> getStateList(){
		return service.getStateDetailsForOption();
	}
	
	
	@GetMapping("/State")
	@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
	public String viewState(Model model){
		model.addAttribute("state", new StateBean());
		return "state";
	}
	
	@GetMapping("/stateList")
	@ResponseBody
	public List<Map<String,Object>> getAllStateList(){
		return service.getAllStateList();
	}
	
	
	
	@PostMapping("/addState")
	@ResponseBody
	@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
	public int addState(@Valid @ModelAttribute("state") StateBean bean,
				BindingResult result,Model model) {
		if(result.hasErrors()) {
			return -1;
		}else {
			int count = service.addState(bean);
			return count;
		}
	}
	
	
	@PutMapping("/updateState")
	@ResponseBody
	@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
	public int updateState(@Valid @ModelAttribute StateBean bean,
			BindingResult result,Model model) {
		if(result.hasErrors()) {
			return -1;
		}else {
			int count =  service.updateStateDetails(bean);
			return count;
		}
	}
	
	
	@GetMapping("/getSingleState/{state_id}")
	@ResponseBody
	@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
	public StateBean getSingleStateDataForUpdate(@PathVariable("state_id") int state_id) {
		return service.getSingleStateDetailsForUpdate(state_id);
	}
	
	@DeleteMapping("/deleteState/{state_id}")
	@ResponseBody
	@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
	@ExceptionHandler(value = SQLException.class)
	public int deleteState(@PathVariable("state_id") int state_id) {
		int count = service.deleteState(state_id);
		return count;
	}
	
	
	
	//for district goes here
	
	@GetMapping("/District")
	@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
	public String goToDistrict(Model model) {
		model.addAttribute("district", new DistrictBean());
		return "district";
	}
	
	
	@GetMapping("/districtList")
	@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
	@ResponseBody
	public Map<String,Object> getDistrictList(@RequestParam("search") String search,
									@RequestParam("state_id") int state_id, @RequestParam("start") int start, @RequestParam("length") int length,
									@RequestParam("draw") int draw){
		return service.getStateListForDataTable(state_id, search, start, length, draw);
	}
	
	
	@PostMapping("/addDistrict")
	@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
	@ResponseBody
	public int addDistrict(@Valid @ModelAttribute("district") DistrictBean bean,
							BindingResult result,Model model){
		if(result.hasErrors()) {
			return -1;
		}else {
			return service.addDistrict(bean);
		}
	}
	
	
	@PutMapping("/updateDistrict")
	@ResponseBody
	@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
	public int updateDistrict(@Valid @ModelAttribute("district") DistrictBean bean,
							BindingResult result,Model model) {
		if(result.hasErrors()) {
			return -1;
		}else {
			return service.updateDistrict(bean);
		}
	}
	
	@DeleteMapping("/deleteDistrict/{district_id}")
	@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
	@ResponseBody
	public int deleteDistrict(@PathVariable("district_id") int district_id) {
		return service.deleteDistrict(district_id);
	}
	
	@GetMapping("/getSingleDistrict/{district_id}")
	@ResponseBody
	public DistrictBean getSingleDistrict(@PathVariable("district_id") int district_id) {
		DistrictBean bean = service.getSingleDistrictDetails(district_id);
		return bean;
	}
	
	
	@GetMapping("/stateWiseDistrictList/{state_id}")
	@ResponseBody
	public Map<Integer,String> getStateWiseDistrictList(@PathVariable("state_id") int state_id){
		return service.getStateWiseDistrictList(state_id);
	}
	
	
	//taluk goes here
	
		@GetMapping("/Taluk")
		@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
		public String goToTaluk(Model model) {
			model.addAttribute("Taluk", new TalukBean());
			return "Taluk";
		}
		
		@PostMapping("/addTaluk")
		@ResponseBody
		@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
		public int addTaluk(@Valid @ModelAttribute("Taluk") TalukBean bean,
					BindingResult result,Model model) {
			if(result.hasErrors()) {
				return -1;
			}else {
				int count = service.addTaluk(bean);
				return count;
			}
		}
		
		@GetMapping("/getSingleTaluk/{taluk_id}")
		@ResponseBody
		@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
		public Map<String,Object> getSingleTalukDataForUpdate(@PathVariable("taluk_id") int taluk_id) {
			return service.getSingleTalukDetailsForUpdate(taluk_id);
		}
		
		@GetMapping("/talukList")
		@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
		@ResponseBody
		@ExceptionHandler(value =Exception.class)
		public Map<String,Object> getTalukList(@RequestParam("search") String search,
										@RequestParam("state_id") int state_id,@RequestParam("district_id") int district_id, @RequestParam("start") int start, @RequestParam("length") int length,
										@RequestParam("draw") int draw){
			return service.getTalukListForDataTable(state_id,district_id, search, start, length, draw);
		}
		
		

		@PutMapping("/updateTaluk")
		@ResponseBody
		@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
		public int updateTaluk(@Valid @ModelAttribute TalukBean bean,
				BindingResult result,Model model) {
			if(result.hasErrors()) {
				return -1;
			}else {
				int count =  service.updateTalukDetails(bean);
				return count;
			}
		}
		
		@DeleteMapping("/deleteTaluk/{taluk_id}")
		@ResponseBody
		@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
		@ExceptionHandler(value = SQLException.class)
		public int deleteTaluk(@PathVariable("taluk_id") int taluk_id) {
			int count = service.deleteTaluk(taluk_id);
			return count;
		}
	
	
	@GetMapping("/getDistrictWiseTalukForOption/{district_id}")
	@ResponseBody
	public Map<Integer,String> getDistrictWiseTalukForOption(@PathVariable("district_id") int district_id){
		return service.getDistrictWiseTalukDataForOption(district_id);
	}
	
	
	
	
	
	//for hobli
	@GetMapping("/Hobli")
	@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
	public String goToHobli(Model model) {
		model.addAttribute("hobli",new HobliBean());
		return "Hobli";
	}
	
	@PostMapping("/addHobli")
	@ResponseBody
	@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
	public int addHobli(@Valid @ModelAttribute("hobli") HobliBean bean,
			BindingResult result,Model model) {
		if(result.hasErrors()) {
			return -1;
		}else {
			return service.addHobli(bean);
		}
	}
	
	public int updateHobli(@Valid @ModelAttribute("hobli") HobliBean bean,
			BindingResult result) {
		if(result.hasErrors()) {
			return -1;
		}else {
			return service.updateHobli(bean);
		}
	}
	
	@GetMapping("/getHobliList")
	@ResponseBody
	@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
	public Map<String,Object> getHobliList(HttpServletRequest request){
		int state_id = Integer.parseInt(request.getParameter("state_id"));
		int district_id=Integer.parseInt(request.getParameter("district_id"));
		int taluk_id = Integer.parseInt(request.getParameter("taluk_id"));
		String search = request.getParameter("search");
		int start = Integer.parseInt(request.getParameter("start"));
		int length = Integer.parseInt(request.getParameter("length"));
		int draw = Integer.parseInt(request.getParameter("draw"));
		Map<String,Object> data = service
					.getHobliList(state_id, district_id, taluk_id, search, start, length, draw);
		return data;
	}
	
	@DeleteMapping("/deleteHobli/{hobli_id}")
	@ResponseBody
	@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
	public int deleteHobli(@PathVariable("hobli_id") int hobli_id) {
		return service.deleteHobli(hobli_id);
	}
	
	
	@GetMapping("/getSingleHobliData/{hobli_id}")
	@ResponseBody
	@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
	public Map<String,Object> getSingleHobliData(@PathVariable("hobli_id") int hobli_id){
		return service.getSingleHobliData(hobli_id);
	}
	
	
	@GetMapping("/getTalukWiseHobliListOption/{taluk_id}")
	@ResponseBody
	public Map<Integer,String> getTalukWiseHobliListForOption(@PathVariable("taluk_id") int taluk_id){
		return service.getTalukWiseHobliListForOption(taluk_id);
	}
	
	
	@GetMapping("/getHobliWiseVillageListOption/{hobli_id}")
	@ResponseBody
	public Map<Integer,String> getHobliWiseVillageOption(@PathVariable("hobli_id") int hobli_id){
		return service.getHobliWiseVillageListForOption(hobli_id);
	}
	
	
	//For Village
	
		@GetMapping("/Village")
		@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
		public String goToVillage(Model model) {
			model.addAttribute("Village",new VillageBean());
			return "Village";
		}
		
		@PostMapping("/addVillage")
		@ResponseBody
		@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
		public int addVillage(@Valid @ModelAttribute("Village") VillageBean bean,
				BindingResult result,Model model) {
			if(result.hasErrors()) {
				return -1;
			}else {
				return service.addVillage(bean);
			}
		}
		
		@PutMapping("/updateVillage")
		@ResponseBody
		@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
		public int updateVillage(@Valid @ModelAttribute("Village") VillageBean bean,
				BindingResult result) {
			if(result.hasErrors()) {
				return -1;
			}else {
				return service.updateVillage(bean);
			}
		}
		
		@GetMapping("/getVillageList")
		@ResponseBody
		@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
		public Map<String,Object> getVillageList(HttpServletRequest request){
			int state_id = Integer.parseInt(request.getParameter("state_id"));
			int district_id=Integer.parseInt(request.getParameter("district_id"));
			int taluk_id = Integer.parseInt(request.getParameter("taluk_id"));
			int hobli_id = Integer.parseInt(request.getParameter("hobli_id"));
			String search = request.getParameter("search");
			int start = Integer.parseInt(request.getParameter("start"));
			int length = Integer.parseInt(request.getParameter("length"));
			int draw = Integer.parseInt(request.getParameter("draw"));
			Map<String,Object> data = service
						.getVillageList(state_id, district_id, taluk_id, hobli_id, search, start, length, draw);
			return data;
		}
		
		@DeleteMapping("/deleteVillage/{village_id}")
		@ResponseBody
		@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
		public int deleteVillage(@PathVariable("village_id") int village_id) {
			return service.deleteVillage(village_id);
		}
		
		
		@GetMapping("/getSingleVillageData/{village_id}")
		@ResponseBody
		@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
		public Map<String,Object> getSingleVillageData(@PathVariable("village_id") int village_id){
			return service.getSingleVillageData(village_id);
		}
	
	
	
}
