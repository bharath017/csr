package com.csr.dao;

import java.util.Map;

import com.csr.bean.DonorBean;

public interface DonorDAO {

	public int addDonor(DonorBean bean);
	public int updateDonor(DonorBean bean);
	public int deleteDonor(int doner_id);
	public Map<String,Object> getSingleDonorDetails(int donor_id);
	public Map<Integer,String> getDonorsForOption();
}
