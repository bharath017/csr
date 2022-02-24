package com.csr.dao;

import java.util.Map;

import com.csr.bean.ClusterBean;

public interface ClusterDAO {
	public int addClusterDetails(ClusterBean bean);
	public int updateClusterDetails(ClusterBean bean);
	public int deleteClusterDetails(int cluster_id);
	public Map<String,Object> getSingleClusterData(int cluster_id);
	public Map<Integer,String> getClusterDataForOption();
	
}
