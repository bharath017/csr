package com.csr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.csr.bean.ClusterBean;

@Repository
public class ClusterDAOImpl implements ClusterDAO{

	@Autowired
	private NamedParameterJdbcTemplate ntemplate;
	
	@Autowired
	private JdbcTemplate template;
	
	private static final String ADD_CLUSTER_DETAILS="insert into cluster(cluster_resource_center_name,cluster_resource_center_address,"
			+ "cluster_resource_person_name,cluster_resource_person_phone,district_id,hobli_id,state_id,taluk_id)"
			+ " values(:cluster_resource_center_name,:cluster_resource_center_address,:cluster_resource_person_name,"
			+ " :cluster_resource_person_phone,:district_id,:hobli_id,:state_id,:taluk_id)";
	
	private static final String UPDATE_CLUSTER_DETAILS="update cluster set"
			+ "cluster_resource_center_name=:cluster_resource_center_name,cluster_resource_center_address=:cluster_resource_center_address,"
			+ "cluster_resource_person_name=:cluster_resource_person_name,cluster_resource_person_phone=:cluster_resource_person_phone,"
			+ "district_id=:district_id,hobli_id=:hobli_id,state_id=:state_id,taluk_id=:taluk_id where cluster_id=:cluster_id"; 
	
	private static final String DELETE_CLUSTER_DETAILS="delete from cluster where cluster_id=?";
	
	private static final String GET_SINGLE_CLUSTER_DATA="select * from cluster where cluster_id=?";
	
	
	private static final String GET_ALL_CLUSTER_LIST="select c.cluster_id,c.cluster_resource_center_name,c.cluster_resource_center_address,c.cluster_resource_person_name,"
												+" c.cluster_resource_person_phone,s.state_name,d.district_name,t.taluk_name,h.hobli_name"
												+" from cluster c LEFT JOIN (state s,district d,taluk t,hobli h)"
												+" ON c.state_id=s.state_id"
												+" and c.district_id=d.district_id"
												+" and c.taluk_id=t.taluk_id"
												+" and c.hobli_id=h.hobli_id"
												+" where c.cluster_resource_center_name like ?"
												+" and c.state_id like if(?=0,'%%',?)"
												+" and c.district_id like if(?=0,'%%',?)"
												+" and c.taluk_id like if(?=0,'%%',?)"
												+" and c.hobli_id like if(?=0,'%%',?)"
												+" order by c.cluster_id desc "
												+" limit ?,?";
	
	private static final String COUNT_ALL_CLUSTER_LIST="select count(c.cluster_id)"
													+" from cluster c"
													+" where c.cluster_resource_center_name like ?"
													+" and c.state_id like if(?=0,'%%',?)"
													+" and c.district_id like if(?=0,'%%',?)"
													+" and c.taluk_id like if(?=0,'%%',?)"
													+" and c.hobli_id like if(?=0,'%%',?)";	
	
	
	private static final String GET_CLUSTER_DATA_FOR_OPTION="select cluster_id,cluster_resource_center_name from cluster";
	
	@Override
	public int addClusterDetails(ClusterBean bean) {
		BeanPropertySqlParameterSource source =new BeanPropertySqlParameterSource(bean);
		return ntemplate.update(ADD_CLUSTER_DETAILS, source);
	}

	@Override
	public int updateClusterDetails(ClusterBean bean) {
		BeanPropertySqlParameterSource source =new BeanPropertySqlParameterSource(bean);
		return ntemplate.update(UPDATE_CLUSTER_DETAILS, source);
	}

	@Override
	public int deleteClusterDetails(int cluster_id) {
		return template.update(DELETE_CLUSTER_DETAILS,new Object[] {cluster_id});
	}

	
	@Override
	public Map<String, Object> getSingleClusterData(int cluster_id) {
		return template.queryForMap(GET_SINGLE_CLUSTER_DATA, new Object[cluster_id]);
	}
	
	public Object getAllClusterList(int state_id, int district_id, int taluk_id,int hobli_id,String search, int start, int length) {
		
		List<Map<String,Object>> talukList = template.queryForList(GET_ALL_CLUSTER_LIST, new Object[] {"%"+search+"%",state_id,state_id,district_id,district_id,taluk_id,taluk_id,hobli_id,hobli_id ,start,length});
		return talukList;
		
	}

	public int countClusterList(int state_id, int district_id , int taluk_id,int hobli_id, String search) {
		return template.queryForObject(COUNT_ALL_CLUSTER_LIST, Integer.class, new Object[] {"%"+search+"%",state_id,state_id,district_id,district_id,taluk_id,taluk_id,hobli_id,hobli_id });
	}
	

	@Override
	public Map<Integer, String> getClusterDataForOption() {
		return template.query(GET_CLUSTER_DATA_FOR_OPTION,new ResultSetExtractor<Map<Integer,String>>() {
			@Override
			public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<Integer,String> data=new HashMap<>();
				while(rs.next()) {
					data.put(rs.getInt(1), rs.getString(2));
				}
				return data;
			}
		},new Object[] {});
	}	
	
	
	
	
	

}
