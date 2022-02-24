package com.csr.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csr.bean.ClusterBean;
import com.csr.service.ClusterService;

@Controller
@RequestMapping("/Cluster")
public class ClusterController {
	
	@Autowired
	private ClusterService service;
	
	
	@GetMapping("/addCluster")
	public ModelAndView addNewCluster() {
		ModelAndView model=new ModelAndView("AddCluster");
		model.addObject("cluster", new ClusterBean());
		return model;
	}
	
	@GetMapping("/ClusterList")
	@Secured({"ROLE_ADMIN","ROLE_SADMIN"})
	public String showClusterList() {
		return "ClusterList";
	}
	
	@PostMapping("/addCluster")
	@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
	public String addCluster(@Valid @ModelAttribute("cluster") ClusterBean bean,
				BindingResult result,Model model,RedirectAttributes attribute) throws Exception {
		if(result.hasErrors()) {
			return "cluster";
		}else {
			int count = service.addCluster(bean);
			attribute.addFlashAttribute("result", "Inserted Successfully");
			return "redirect:/Cluster/ClusterList";
		}
	}
	
	@DeleteMapping("/deleteCluster/{cluster_id}")
	@ResponseBody
	@Secured({"ROLE_ADMIN","ROLE_SADMIN"})
	public int deleteCluster(@PathVariable("cluster_id") int cluster_id) {
		try {
			return service.deleteCluster(cluster_id);
		}catch(Exception e) {
			return -1;
		}
	}
	
	@GetMapping("/getClusterList")
	@ResponseBody
	@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
	public Map<String,Object> getHobliList(HttpServletRequest request){
		int state_id = Integer.parseInt(request.getParameter("state_id"));
		int district_id=Integer.parseInt(request.getParameter("district_id"));
		int taluk_id = Integer.parseInt(request.getParameter("taluk_id"));
		int hobli_id = Integer.parseInt(request.getParameter("hobli_id"));
		String search = request.getParameter("search");
		int start = Integer.parseInt(request.getParameter("start"));
		int length = Integer.parseInt(request.getParameter("length"));
		int draw = Integer.parseInt(request.getParameter("draw"));
		Map<String,Object> data = service
					.getClusterList(state_id, district_id, taluk_id, hobli_id , search, start, length, draw);
		return data;
	}
	

	
	
	
}
