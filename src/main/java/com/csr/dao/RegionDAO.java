package com.csr.dao;

import java.util.List;
import java.util.Map;

import com.csr.bean.DistrictBean;
import com.csr.bean.HobliBean;
import com.csr.bean.StateBean;
import com.csr.bean.TalukBean;

public interface RegionDAO {
	public List<Map<String,Object>> getAllStateList();
	public int addState(StateBean bean);
	public int updateState(StateBean bean);
	public int deleteStateBean(int state_id);
	public StateBean getSingleStateDetails(int state_id);
	public Map<Integer,String> getStateListForOption();
	
	public List<Map<String,Object>> getAllDistrictList(int state_id,String search, int start, int length);
	public int countAllDistrict(int state_id,String search);
	public int deleteDistrict(int district_id);
	public int addDistrict(DistrictBean bean);
	public int updateDistrict(DistrictBean bean);
	public DistrictBean getSingleDistrictDetails(int district_id);
	
	
	
	//for taluk
	public int addTaluk(TalukBean bean);
	public int updateTaluk(TalukBean bean);
	public Object getAllTalukList(int state_id, int district_id,String search, int start, int length);
	public int countAllTaluk(int state_id, int district_id, String search);
	
	//for hobli
	
	public List<Map<String,Object>> getAllHobliList(int state_id,int district_id,int taluk_id,String search,int start, int length);
	public int countHobliList(int state_id,int district_id,int taluk_id,String search);
	public int addHobli(HobliBean bean);
	public int updateHobli(HobliBean bean);
	public int deleteHobli(int hobli_id);
	public Map<String,Object> getSingleHobliData(int hobli_id);
	
	//for model
	public Map<Integer,String> getStateWiseDistrictList(int state_id);
	public Map<Integer,String> getDistrictWiseTalukListForOption(int district_id);
	public Map<Integer,String> getTalukWiseHobliListForOption(int taluk_id);
	public Map<Integer,String> getHobliWiseVillageListForOption(int hobli_id);
	
	
}
