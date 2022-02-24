package com.csr.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csr.bean.SchoolBean;
import com.csr.bean.SchoolImageBean;
import com.csr.service.ClusterService;
import com.csr.service.SchoolService;

@Controller
@RequestMapping("/School")
public class SchoolController {
	@Autowired
	private SchoolService service;
	
	@Autowired
	private ClusterService clusterService;
	
	
	
	@ModelAttribute("school")
	public SchoolBean getSchoolModel() {
		return new SchoolBean();
	}
	
	@ModelAttribute("clusterListForOption")
	public Map<Integer,String> getClusterModel(){
		return clusterService.getClusterListForOption();
	}
	
	@GetMapping("/addSchool")
	@Secured({"ROLE_ADMIN","ROLE_SADMIN"})
	public String addSchool() {
		return "AddSchool";
	}
	
	
	@PostMapping("/saveSchool")
	@Secured({"ROLE_ADMIN","ROLE_SADMIN"})
	public String addSchool(@Valid @ModelAttribute("school") SchoolBean bean,
				BindingResult result,Model model,
				RedirectAttributes attribute,
				HttpServletRequest request) throws Exception {
		synchronized (request) {
			if(result.hasErrors()) {
				model.addAttribute("error", "Some error exist from background");
				return "AddSchool";
			}else {
				System.out.println(bean);
				int count=service.addSchool(bean);
				SchoolImageBean[] imageList = new SchoolImageBean[bean.getSchool_image().length];
				if(count>0) {
					int school_id = service.getLastAddedSchoolDetails();
					int index = 0;
					for(SchoolImageBean imageBean : bean.getSchool_image()) {
						imageBean.setSchool_id(school_id);
						imageList[index] = imageBean;
						index++;
					}
					//add school image here
					service.addSchoolImage(imageList);
				}
				attribute.addFlashAttribute("resultCount", count);
				attribute.addFlashAttribute("result", "School added successfully");
				return "redirect:/School/SchoolList";
			}
		}
	}
	
	
	@GetMapping("/SchoolList")
	public String showSchoolList() {
		return "SchoolList";
	}
	
	
	@GetMapping("/getSchoolList")
	@ResponseBody
	@Secured({"ROLE_ADMIN","ROLE_SADMIN"})
	public Map<String,Object> getHobliList(HttpServletRequest request){
		String school_name = request.getParameter("school_name");
		int start = Integer.parseInt(request.getParameter("start"));
		int length = Integer.parseInt(request.getParameter("length"));
		int draw = Integer.parseInt(request.getParameter("draw"));
		Map<String,Object> data = service.getSchoolList(school_name, start, length, draw);
		return data;
	}
	
	
	@RequestMapping(value = "/upload",method = RequestMethod.POST)
	@ResponseBody
	public String uploadSchoolImage(@Value("${school.imagePath}") String imagePath,
			@RequestParam("file-0") MultipartFile file) {
		try {
			 byte[] bytes = file.getBytes();
	         Path path = Paths.get(imagePath+"\\" + file.getOriginalFilename());
	         Files.write(path, bytes);
	         return path.getFileName().toString();
		}catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@DeleteMapping("/deleteSchool/{school_id}")
	@Secured({"ROLE_ADMIN","ROLE_SADMIN","ROLE_SUPERADMIN"})
	@ResponseBody
	public int deleteSchoolDetails(@PathVariable("school_id") int school_id) {
		try {
			int count = service.deleteSchool(school_id);
			return count;
		}catch(Exception e) {
			return -1;
		}
	}
	
	
	
	
	
}
