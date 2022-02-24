package com.csr.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csr.bean.ClusterBean;
import com.csr.dao.ClusterDAOImpl;

@Service
public class ClusterService {
	
	@Autowired
	private ClusterDAOImpl dao;
	
	public int addCluster(ClusterBean bean) {
		return dao.addClusterDetails(bean);
	}
	
	public int updateCluster(ClusterBean bean) {
		return dao.updateClusterDetails(bean);
	}
	
	public int deleteCluster(int cluster_id) {
		return dao.deleteClusterDetails(cluster_id);
	}
	
	public Map<String,Object> getSingleClusterDetails(int cluster_id){
		return dao.getSingleClusterData(cluster_id);
	}
	
	public Map<String,Object> getClusterList(int state_id,int district_id,int taluk_id,int hobli_id,String search,int start,int length, int draw){
		Map<String,Object> data = new HashMap<>();
		data.put("data", dao.getAllClusterList(state_id, district_id, taluk_id,hobli_id, search, start, length));
		data.put("draw", draw);
		int recordCount=dao.countClusterList(state_id, district_id, taluk_id,hobli_id, search);
		data.put("recordsTotal", recordCount);
		data.put("recordsFiltered", recordCount);
		return data;
	}
	
	public Map<Integer,String> getClusterListForOption(){
		return dao.getClusterDataForOption();
	}
	
}
