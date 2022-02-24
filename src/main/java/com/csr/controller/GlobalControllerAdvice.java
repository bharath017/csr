package com.csr.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.csr.service.RegionService;

@ControllerAdvice(annotations = Controller.class)
public class GlobalControllerAdvice {
	
	@Autowired
	private RegionService regionService;
	
	@ModelAttribute("stateOption")
	public Map<Integer,String> getStateList(){
		return regionService.getStateDetailsForOption();
	}
}
