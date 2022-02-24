package com.csr.dao;

import java.util.List;
import java.util.Map;

import com.csr.bean.SchoolBean;
import com.csr.bean.SchoolImageBean;

public interface SchoolDAO {
	public int addSchoolData(SchoolBean bean);
	public int getLastAddedSchoolId();
	public int[] addSchoolImage(SchoolImageBean[] bean);
	public List<Map<String,Object>> getSchoolList(String school_name,int start, int length);
	public int countSchoolList(String school_name);
	public int deleteSchool(int school_id);
	public Map<Integer,String> getSchoolListByVillageForOption(int village_id);
	public Map<Integer,String> getAllSchoolForOption();
	
}
