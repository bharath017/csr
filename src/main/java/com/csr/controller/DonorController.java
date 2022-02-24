package com.csr.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csr.bean.DonorBean;
import com.csr.service.DonorService;

@Controller
@RequestMapping("/Donor")
public class DonorController {

	@Autowired
	private DonorService service;

	@GetMapping("/addDonor")
	public ModelAndView addNewDonor() {
		ModelAndView model=new ModelAndView("AddDonor");
		model.addObject("donor", new DonorBean());
		return model;
	}
	
	@PostMapping("/addDonor")
	@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
	public String addCluster(@Valid @ModelAttribute("donor") DonorBean bean,
				BindingResult result,Model model,RedirectAttributes attribute) throws Exception {
		if(result.hasErrors()) {
			return "cluster";
		}else {
			int count = service.addDonor(bean);
			attribute.addFlashAttribute("result", "Inserted Successfully");
			//attribute.addAttribute("result", "Inserted Successfully");
			 return "redirect:/Donor/DonorList";
		}
	}
	
	@GetMapping("/DonorList")
	public String showClusterList() {
		return "DonorList";
	}
	
	@GetMapping("/getDonorList")
	@ResponseBody
	@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
	public Map<String,Object> getDonorList(HttpServletRequest request){
		String search = request.getParameter("search");
		int start = Integer.parseInt(request.getParameter("start"));
		int length = Integer.parseInt(request.getParameter("length"));
		int draw = Integer.parseInt(request.getParameter("draw"));
		Map<String,Object> data = service
					.getDonorList(search, start, length, draw);
		return data;
	}
}
