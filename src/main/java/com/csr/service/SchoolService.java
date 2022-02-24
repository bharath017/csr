package com.csr.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csr.bean.SchoolBean;
import com.csr.bean.SchoolImageBean;
import com.csr.dao.SchoolDAOImpl;

@Service
public class SchoolService {
	
	@Autowired
	private SchoolDAOImpl dao;
	
	
	public int addSchool(SchoolBean bean) {
		return dao.addSchoolData(bean);
	}
	
	
	public int getLastAddedSchoolDetails() {
		return dao.getLastAddedSchoolId();
	}
	
	public Map<String,Object> getSchoolList(String school_name,int start,int length, int draw){
		Map<String,Object> data = new HashMap<>();
		data.put("data", dao.getSchoolList(school_name, start, length));
		data.put("draw", draw);
		int recordCount=dao.countSchoolList(school_name);
		data.put("recordsTotal", recordCount);
		data.put("recordsFiltered", recordCount);
		return data;
	}
	
	
	
	public int addSchoolImage(SchoolImageBean[] bean) {
		return dao.addSchoolImage(bean).length;
	}
	
	public int deleteSchool(int school_id) {
		return dao.deleteSchool(school_id);
	}
	
	public Map<Integer,String> getSchoolListForOption(int village_id){
		return dao.getSchoolListByVillageForOption(village_id);
	}
	
	public Map<Integer,String> getAllSchoolListForOption(){
		return dao.getAllSchoolForOption();
	}
	
	
}
