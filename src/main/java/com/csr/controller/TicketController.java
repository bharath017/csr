package com.csr.controller;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csr.bean.TicketBean;
import com.csr.bean.TicketCategoryBean;
import com.csr.bean.TicketDonorBean;
import com.csr.bean.TicketSubCategoryBean;
import com.csr.service.DonorService;
import com.csr.service.SchoolService;
import com.csr.service.TicketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Controller
@RequestMapping("/Ticket")
public class TicketController {

	@Autowired
	private TicketService service;
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private DonorService donorService;

	
	

	@ModelAttribute("donors")
	public Map<Integer,String> getDonorForOption(){
		return donorService.getDonorListForOption();
	}
	
	
	@GetMapping("/getAllSchoolForOption")
	@ResponseBody
	public Map<Integer,String> getAllSchoolForOption(){
		return schoolService.getAllSchoolListForOption();
	}
	
	@GetMapping("/getTicketCategoryForOption")
	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	public Map<Integer,String> getTicketCategoryForOptionView(){
		return service.getTicketCategoryForOption();
	}
	
	
	@GetMapping("/ticketCategory")
	@PreAuthorize("isAuthenticated()")
	public String goToTicketCategory(Model model) {
		model.addAttribute("category", new TicketCategoryBean());
		model.addAttribute("subcategory", new TicketSubCategoryBean());
		return "TicketCategory";
	}
	
	
	@PostMapping("/addTicketCategory")
	@Secured({"ROLE_ADMIN","ROLE_SUPERADMIN"})
	@ResponseBody
	public int addTicketCategory(@ModelAttribute("category") TicketCategoryBean bean) {
		return  service.addTicketCategory(bean);
	}
	
	
	@GetMapping("/getAllCategoryList")
	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	public List<Map<String,Object>> getAllTicketCategoryList(@RequestParam("search") String search){
		return service.getTicketCategoryList(search);
	}
	
	
	@PostMapping("/addTicketSubCategory")
	@ResponseBody
	@PreAuthorize("isAuthenticated()")
	public int addTicketSubCategory(@ModelAttribute("subcategory") TicketSubCategoryBean bean) {
		return service.addTicketSubCategory(bean);
	}
	
	
	
	@GetMapping("/getAllSubCategoryList")
	@ResponseBody
	@PreAuthorize("isAuthenticated()")
	public List<Map<String,Object>> getTicketSubCategoryList(@RequestParam("search") String search,
											@RequestParam("category_id") int category_id){
		List<Map<String,Object>> data = service.getTicketSubCategoryList(search, category_id);
		return data;
	}
	
	
	@GetMapping("/getSchoolForOption/{village_id}")
	@ResponseBody
	public Map<Integer,String> getSchoolListByVillageIDForOption(@PathVariable("village_id") int village_id){
		return schoolService.getSchoolListForOption(village_id);
	}
	
	
	@GetMapping("/AddTicket")
	@PreAuthorize("isAuthenticated()")
	public String generateTicket(Model model) {
		model.addAttribute("ticket", new TicketBean());
		return "AddTicket";
	}
	
	@GetMapping("/getTicketSubCategoryForOption/{category_id}")
	@ResponseBody
	public Map<Integer,String> getTicketSubCategoryForOption(@PathVariable("category_id") int category_id){
		return service.getTicketSubCategoryForOption(category_id);
	}
	
	
	@RequestMapping(value = "/upload",method = RequestMethod.POST)
	@ResponseBody
	public List<String> uploadImages(@Value("${school.imagePath}") String imagePath,
					@RequestParam("files") MultipartFile[] files){
		
		List<String> fileNames  = new LinkedList<>();
		Arrays.asList(files).stream().forEach(file -> {
			byte[] bytes = new byte[0];
			try {
				bytes = file.getBytes();
				String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
				String newName = "TicketImage-"+System.currentTimeMillis()+extension;
				Path path=Paths.get(imagePath+"\\"+newName);
				Files.write(path, bytes);
				fileNames.add(newName);
			}catch(Exception e) {
				e.printStackTrace();
			}
		});
		return fileNames;
	}
	
	
	
	@PostMapping("/saveTicket")
	public String saveTicket(@Valid @ModelAttribute("ticket") TicketBean bean,
			BindingResult result,Model model,RedirectAttributes attribute) {
		bean.setImages((bean.getImages().equals(""))?null:bean.getImages());
		if(result.hasErrors()) {
			model.addAttribute("errors", "Some Error Exist");
			return "AddTicket";
		}else {
			int count  = service.addTicket(bean);
			attribute.addFlashAttribute("result", "ticket");
			attribute.addFlashAttribute("resultCount", count);
			return "redirect:/Ticket/ticketList";
		}
	}
	
	
	@GetMapping("/ticketList")
	public String showTicketList(){
		return "TicketList";
	}
	
	
	@GetMapping("/getAllTicketList")
	@ResponseBody
	public Map<String,Object>  getAllTicketList(HttpServletRequest request){
		int school_id = Integer
				.parseInt(request.getParameter("school_id"));
		int category_id = Integer
				.parseInt(request.getParameter("category_id"));
		int sub_category_id = Integer
				.parseInt(request.getParameter("sub_category_id"));
		
		String ticket_status = request.getParameter("ticket_status");
		int start = Integer.parseInt(request.getParameter("start"));
		int length = Integer.parseInt(request.getParameter("length"));
		int draw = Integer.parseInt(request.getParameter("draw"));
		Map<String,Object> data = service.getAllTicketList(school_id, 
				category_id, sub_category_id, ticket_status, start, length, draw);
		return data;
	}
	
	
	
	@GetMapping("/viewTicket/{ticket_id}")
	@PreAuthorize("isAuthenticated()")
	public String viewTicket(@PathVariable("ticket_id") int ticket_id,Model model) {
		Map<String,Object> singleTicketData = service.getSingleTicketDetails(ticket_id);
		model.addAttribute("ticketdonor", new TicketDonorBean());
		model.addAttribute("ticket", singleTicketData);
		return "ViewTicket";
	}
	
	@PutMapping("/changeTicketStatus/{ticket_id}/{ticket_status}")
	@ResponseBody
	public int changeTicketStatus(@PathVariable("ticket_id") int ticket_id,
			@PathVariable("ticket_status") String status) {
		return service.changeTicketStatus(ticket_id, status);
	}
	
	@GetMapping("/getTicketImages/{ticket_id}")
	@ResponseBody
	public Object getTicketImageDetail(@PathVariable("ticket_id") int ticket_id) {
		return service.getTicketImages(ticket_id);
	}
	
	
	@PostMapping("/uploadEstimatedDocument/{ticket_id}")
	@ResponseBody
	public String uploadEstimatedDocument(@PathVariable("ticket_id") int ticket_id,@Value("${school.imagePath}") String imagePath,
			@RequestParam("files") MultipartFile[] files) throws JsonProcessingException{
		List<String> fileNames  = new LinkedList<>();
		Arrays.asList(files).stream().forEach(file -> {
			byte[] bytes = new byte[0];
			try {
				bytes = file.getBytes();
				String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
				String newName = "EstimatedDocument-"+System.currentTimeMillis()+extension;
				Path path=Paths.get(imagePath+"\\"+newName);
				Files.write(path, bytes);
				fileNames.add(newName);
			}catch(Exception e) {
				e.printStackTrace();
			}
		});
		service.updateEstimatedDocuments(ticket_id, fileNames);
		String estimatedTickets = service.getAllEstimatedDocumentsList(ticket_id);
		return estimatedTickets;
	}
	
	@GetMapping("/getAllEstimatedDocs/{ticket_id}")
	@ResponseBody
	public String getAllEstimatedDocs(@PathVariable("ticket_id") int ticket_id) {
		return service.getAllEstimatedDocumentsList(ticket_id);
	}
	
	
	@RequestMapping(value="/removeEstimatedDocs/{ticket_id}",
			method = RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String removeEstimatedDocs(@PathVariable("ticket_id") int ticket_id,
			@RequestBody	List<String> data) throws JsonProcessingException {
		return service.removeEstimatedDocs(ticket_id, data);
	}
	
	
	@PutMapping("/updateEstimatedAmount/{ticket_id}/{amount}")
	@ResponseBody
	public int updateEstimatedAmount(@PathVariable("ticket_id") int ticket_id,
			@PathVariable("amount") double amount,HttpServletRequest request) {
		String estimated_status = request.getParameter("estimated_status");
		return service.updateEstimatedAmount(ticket_id, amount, estimated_status);
	}
	
	@PutMapping("/approveEstimatedAmount/{ticket_id}")
	@ResponseBody
	public int approveEstimatedAmount(@PathVariable("ticket_id") int ticket_id) {
		return service.approveEstimatedAmounts(ticket_id);
	}
	
	@PostMapping("/addTicketDonor")
	@ResponseBody
	public int addTicketDonor(@Valid @ModelAttribute("ticketdonor") TicketDonorBean bean,
			BindingResult result) {
		if(result.hasErrors()) {
			return -1;
		}else {
			return service.addTicketDonor(bean);
		}
	}
	
	@GetMapping("/getTicketDonorList/{ticket_id}")
	@ResponseBody
	public List<Map<String,Object>> getTicketDonorList(@PathVariable("ticket_id") int ticket_id){
		return service.getDonorListForTicket(ticket_id);
	}
	
	
	@DeleteMapping("/removeTicketDonor/{ticket_donor_id}")
	@ResponseBody
	public int deleteTicketDonor(@PathVariable("ticket_donor_id") int ticket_donor_id) {
		return service.deleteTicketDonor(ticket_donor_id);
	}
	
	
	@PostMapping("/uploadCompletionImage/{ticket_id}")
	@ResponseBody
	public int uploadCompletionDocuments(@PathVariable("ticket_id") int ticket_id,@Value("${school.imagePath}") String imagePath,
			@RequestParam("files") MultipartFile[] files) throws JsonProcessingException{
		List<String> fileNames  = new LinkedList<>();
		Arrays.asList(files).stream().forEach(file -> {
			byte[] bytes = new byte[0];
			try {
				bytes = file.getBytes();
				String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
				String newName = "CompletionTicket-"+System.currentTimeMillis()+extension;
				Path path=Paths.get(imagePath+"\\"+newName);
				Files.write(path, bytes);
				fileNames.add(newName);
			}catch(Exception e) {
				e.printStackTrace();
			}
		});
		int comleteImage = service.updateCompletionDocuments(ticket_id, fileNames);
		return comleteImage;
	}
	
	
	@GetMapping("/getCompletonDocuments/{ticket_id}")
	@ResponseBody
	public String getCompletionDocuments(@PathVariable("ticket_id") int ticket_id) {
		return service.getTicketCompletionDocument(ticket_id);
	}
	
	
	
	@PutMapping("/updateCompletionDetails/{ticket_id}/{completion_description}")
	@ResponseBody
	public Map<String,Object> updateCompletionDescriptoin(@PathVariable("ticket_id") int ticket_id,
			@PathVariable("completion_description") String completion_description){
		return service.updateTicketCompletion(ticket_id, completion_description);
	}
	
	
	
	
}
