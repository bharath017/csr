package com.csr.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csr.bean.DonorBean;
import com.csr.dao.DonorDAOImpl;

@Service
public class DonorService {

	@Autowired
	private DonorDAOImpl dao;
	
	public int addDonor(DonorBean bean) {
		return dao.addDonor(bean);
	}
	
	public int updateDonor(DonorBean bean) {
		return dao.updateDonor(bean);
	}
	
	public int deleteDonor(int donor_id) {
		return dao.deleteDonor(donor_id);
	}

	public Map<String, Object> getDonorList(String search, int start, int length, int draw) {
		Map<String,Object> data = new HashMap<>();
		data.put("data", dao.getAllDonorList(search, start, length));
		data.put("draw", draw);
		int recordCount=dao.countDonorList(search);
		data.put("recordsTotal", recordCount);
		data.put("recordsFiltered", recordCount);
		return data;
	}
	
	public Map<Integer,String> getDonorListForOption(){
		return dao.getDonorsForOption();
	}
	
}
