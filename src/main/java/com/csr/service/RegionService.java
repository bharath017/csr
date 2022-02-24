package com.csr.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csr.bean.DistrictBean;
import com.csr.bean.HobliBean;
import com.csr.bean.StateBean;
import com.csr.bean.TalukBean;
import com.csr.bean.VillageBean;
import com.csr.dao.RegionDAOImpl;

@Service
public class RegionService {
	@Autowired
	private RegionDAOImpl dao;
	
	public List<Map<String,Object>> getAllStateList(){
		return dao.getAllStateList();
	}
	
	public int addState(StateBean bean) {
		return dao.addState(bean);
	}
	
	public int deleteState(int state_id) {
		return dao.deleteStateBean(state_id);
	}
	
	public StateBean getSingleStateDetailsForUpdate(int state_id) {
		return dao.getSingleStateDetails(state_id);
	}
	
	public int updateStateDetails(StateBean bean) {
		return dao.updateState(bean);
	}
	
	public Map<Integer,String> getStateDetailsForOption(){
		return dao.getStateListForOption();
	}
	
	public Map<String,Object> getStateListForDataTable(int state_id, String search, int start, int length, int draw){
		Map<String,Object> data = new HashMap<>();
		data.put("data", dao.getAllDistrictList(state_id, search, start, length));
		data.put("draw", draw);
		int recordCount=dao.countAllDistrict(state_id, search);
		data.put("recordsTotal", recordCount);
		data.put("recordsFiltered", recordCount);
		return data;
	}
	
	public int addDistrict(DistrictBean bean) {
		return dao.addDistrict(bean);
	}
	
	public int updateDistrict(DistrictBean bean) {
		return dao.updateDistrict(bean);
	}
	
	public int deleteDistrict(int district_id) {
		return dao.deleteDistrict(district_id);
	}
	
	
	public DistrictBean getSingleDistrictDetails(int district_id) {
		return dao.getSingleDistrictDetails(district_id);
	}

	public Map<Integer,String> getStateWiseDistrictList(int state_id) {
		return dao.getStateWiseDistrictList(state_id);
	}
	
	public Map<Integer,String> getDistrictWiseTalukDataForOption(int district_id){
		return dao.getDistrictWiseTalukListForOption(district_id);
	}
	
	public int addTaluk(TalukBean bean) {
		return dao.addTaluk(bean);
	}

	public Map<String, Object> getTalukListForDataTable(int state_id, int district_id, String search, int start,
			int length, int draw) {
		Map<String,Object> data = new HashMap<>();
		data.put("data", dao.getAllTalukList(state_id,district_id, search, start, length));
		data.put("draw", draw);
		int recordCount=dao.countAllTaluk(state_id,district_id, search);
		data.put("recordsTotal", recordCount);
		data.put("recordsFiltered", recordCount);
		return data;
	}

	public Map<String, Object> getSingleTalukDetailsForUpdate(int taluk_id) {
		return  dao.getSingleTalukDetails(taluk_id);
	}

	public int updateTalukDetails(@Valid TalukBean bean) {
		return dao.updateTaluk(bean);
	}

	public int deleteTaluk(int taluk_id) {
		// TODO Auto-generated method stub
		return dao.deleteTalukBean(taluk_id);
	}
	
	public int addHobli(HobliBean bean) {
		return dao.addHobli(bean);
	}
	
	public int updateHobli(HobliBean bean) {
		return dao.updateHobli(bean);
	}
	
	public int deleteHobli(int hobli_id) {
		return dao.deleteHobli(hobli_id);
	}
	
	public Map<String,Object> getHobliList(int state_id,int district_id,int taluk_id,String search,int start,int length, int draw){
		Map<String,Object> data = new HashMap<>();
		data.put("data", dao.getAllHobliList(state_id, district_id, taluk_id, search, start, length));
		data.put("draw", draw);
		int recordCount=dao.countHobliList(state_id, district_id, taluk_id, search);
		data.put("recordsTotal", recordCount);
		data.put("recordsFiltered", recordCount);
		return data;
	}
	
	
	public Map<String,Object> getSingleHobliData(int hobli_id){
		return dao.getSingleHobliData(hobli_id);
	}
	
	
	public Map<Integer,String> getTalukWiseHobliListForOption(int taluk_id){
		return dao.getTalukWiseHobliListForOption(taluk_id);
	}
	
	public Map<Integer,String> getHobliWiseVillageListForOption(int hobli_id){
		return dao.getHobliWiseVillageListForOption(hobli_id);
	}
	
	public int addVillage(@Valid VillageBean bean) {
		return dao.addVillage(bean);
	}

	public int updateVillage(@Valid VillageBean bean) {
		return dao.updateVillage(bean);
	}

	public Map<String, Object> getVillageList(int state_id, int district_id, int taluk_id, int hobli_id, String search,
			int start, int length, int draw) {
		Map<String,Object> data = new HashMap<>();
		data.put("data", dao.getAllVillageList(state_id, district_id, taluk_id,hobli_id, search, start, length));
		data.put("draw", draw);
		int recordCount=dao.countVillageList(state_id, district_id, taluk_id,hobli_id, search);
		data.put("recordsTotal", recordCount);
		data.put("recordsFiltered", recordCount);
		return data;
	}

	public int deleteVillage(int village_id) {
		return dao.deleteVillage(village_id);
	}

	public Map<String, Object> getSingleVillageData(int village_id) {
		return dao.getSingleVillageData(village_id);
	}
	
}
