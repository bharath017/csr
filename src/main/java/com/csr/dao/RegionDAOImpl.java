package com.csr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.csr.bean.DistrictBean;
import com.csr.bean.HobliBean;
import com.csr.bean.StateBean;
import com.csr.bean.TalukBean;
import com.csr.bean.VillageBean;

@Repository
public class RegionDAOImpl implements RegionDAO {

	@Autowired
	private JdbcTemplate template;
	
	@Autowired
	private NamedParameterJdbcTemplate ntemplate;
	
	private static final String GET_ALL_STATE_LIST = "select * from state";
	private static final String ADD_STATE="insert into state(state_name,region_name,state_code)"
								+ " values(:state_name,:region_name,:state_code)";
	
	private static final String UPDATE_STATE_DETAILS="update state set state_name=:state_name,region_name=:region_name,state_code=:state_code"
			+ " where state_id=:state_id ";
	
	private static final String DELETE_STATE_DETAILS="delete from state where state_id=?";
	
	private static final String GET_SINGLE_STATE_DETAILS="select * from state where state_id=?";
	
	private static final String GET_STATE_LIST_FOR_OPTION="select state_id,state_name from state";
	
	private static final String GET_ALL_DISTRICT_LIST="select s.state_name,d.district_name,d.district_id,d.district_status"
													+" from district d LEFT JOIN state s "
													+" ON d.state_id=s.state_id"
													+" WHERE d.district_name like ?"
													+" and d.state_id like if(?=0,'%%',?)"
													+" order by d.district_id asc"
													+" limit ?,?";
	
	private static final String COUNT_ALL_DISTRICT_LIST="select count(d.district_id)"
													+" from district d "
													+" WHERE d.district_name like ?"
													+" and d.state_id like if(?=0,'%%',?)";
	
	private static final String ADD_DISTRICT="insert into district(district_name,state_id) values(:district_name,:state_id)";
	
	private static final String UPDATE_DISTRICT="update district set district_name = :district_name,state_id = :state_id where district_id=:district_id";
	
	private static final String DELETE_DISTRICT="delete from district where district_id=?";
	
	private static final String GET_SINGLE_DISTRICT_DETAILS="select * from district where district_id=?";
	
	private static final String GET_STATE_WISE_DISTRICT_LIST="select district_id,district_name from district where state_id=?";
	
	private static final String GET_DISTRICT_WISE_TALUK_FOR_OPTION="select taluk_id,taluk_name from taluk where district_id=?";
	

	private static final String ADD_HOBLI="insert into hobli(hobli_name,taluk_id) values(:hobli_name,:taluk_id)";
	
	private static final String UPDATE_HOBLI="update hobli set hobli_name=:hobli_name,taluk_id=:taluk_id where hobli_id=:hobli_id";
	
	private static final String DELETE_HOBLI="delete from hobli where hobli_id=?";
	
	private static final String GET_HOBLI_LIST="select h.hobli_name,h.hobli_id,s.state_name,d.district_name,t.taluk_name"
											+ " from hobli h LEFT JOIN (taluk t,district d,state s)"
											+ " ON h.taluk_id=t.taluk_id"
											+ " and t.district_id=d.district_id"
											+ " and d.state_id=s.state_id"
											+ " where h.hobli_name like ?"
											+ " and h.taluk_id like if(?=0,'%%',?)"
											+ " and d.district_id like if(?=0,'%%',?)"
											+ " and s.state_id like if(?=0,'%%',?)"
											+ " order by s.state_name asc,d.district_name asc,t.taluk_name asc,h.hobli_name asc"
											+ " limit ?,?";
	
	private static final String COUNT_HOBLI_LIST="select count(h.hobli_id) as count"
											+ " from hobli h LEFT JOIN (taluk t,district d,state s)"
											+ " ON h.taluk_id=t.taluk_id"
											+ " and t.district_id=d.district_id"
											+ " and d.state_id=s.state_id"
											+ " where h.hobli_name like ?"
											+ " and h.taluk_id like if(?=0,'%%',?)"
											+ " and d.district_id like if(?=0,'%%',?)"
											+ " and s.state_id like if(?=0,'%%',?)";
	
	private static final String GET_SINGLE_HOBLI_DATA="select i.hobli_id,i.hobli_name,t.taluk_id,d.district_id,s.state_id"
			+ " from hobli i LEFT JOIN (state s,district d,taluk t)"
			+ " on i.taluk_id=t.taluk_id"
			+ " and t.district_id=d.district_id"
			+ " and d.state_id=s.state_id"
			+ " where i.hobli_id=?";
	
	private static final String GET_TALUK_WISE_HOBLI_FOR_OPTION="select hobli_id,hobli_name from hobli where taluk_id=?";

	private static final String GET_HOBLI_WISE_VILLAGE_LIST_FOR_OPTION="select village_id,village_name from village where hobli_id=?";
	
	private static final String ADD_TALUK = "insert into taluk(taluk_name,district_id)"
			+ " values(:taluk_name,:district_id)";

	private static final String COUNT_ALL_TALUK_LIST = "select count(t.taluk_id) "
			+" from taluk t LEFT JOIN district d "
			+" ON  t.district_id=d.district_id "
			+" WHERE t.taluk_name like ? "
			+" and d.state_id like if(?=0,'%%',?)"
			+" and t.district_id  like if(?=0,'%%',?)";

	private static final String GET_ALL_TALUK_LIST = "select s.state_name,d.district_name,t.taluk_id,t.taluk_name"
			+" from taluk t LEFT JOIN (state s,district d) "
			+" ON d.state_id=s.state_id"
			+" and t.district_id=d.district_id "
			+" WHERE t.taluk_name like ? "
			+" and d.state_id like if(?=0,'%%',?)"
			+" and t.district_id  like if(?=0,'%%',?) "
			+" order by t.taluk_name asc"
			+" limit ?,?";

	private static final String GET_SINGLE_TALUK_DETAILS = "select s.state_id,d.district_id,t.taluk_id,t.taluk_name"
			+" from taluk t LEFT JOIN (state s,district d) "
			+" ON d.state_id=s.state_id"
			+" and t.district_id=d.district_id "
			+" WHERE t.taluk_id = ? ";

	
	
	private static final String UPDATE_TALUK_DETAILS = "update taluk set taluk_name=:taluk_name,district_id=:district_id"
			+ " where taluk_id=:taluk_id ";
	
	private static final String ADD_VILLAGE = "insert into village (village_name, hobli_id) values (:village_name, :hobli_id)";

	private static final String UPDATE_VILLAGE = "update village set village_name=:village_name, hobli_id=:hobli_id where village_id=:village_id";

	private static final String GET_VILLAGE_LIST = "select v.village_name,v.village_id,h.hobli_name,s.state_name,d.district_name,t.taluk_name"
			+ " from village v LEFT JOIN (taluk t,district d,state s,hobli h)"
			+ " ON h.taluk_id=t.taluk_id"
			+ " and t.district_id=d.district_id"
			+ " and d.state_id=s.state_id"
			+ " and v.hobli_id=h.hobli_id "
			+ " where v.village_name like ?"
			+ " and h.hobli_id like if(?=0,'%%',?)"
			+ " and t.taluk_id like if(?=0,'%%',?)"
			+ " and d.district_id like if(?=0,'%%',?)"
			+ " and s.state_id like if(?=0,'%%',?)"
			+ " order by s.state_name asc,d.district_name asc,t.taluk_name asc,h.hobli_name,v.village_name asc"
			+ " limit ?,?";

	private static final String COUNT_VILLAGE_LIST ="select count(v.village_id)"
			+ " from village v LEFT JOIN (taluk t,district d,state s,hobli h)"
			+ " ON h.taluk_id=t.taluk_id"
			+ " and t.district_id=d.district_id"
			+ " and d.state_id=s.state_id"
			+ " and v.hobli_id=h.hobli_id "
			+ " where v.village_name like ?"
			+ " and h.hobli_id like if(?=0,'%%',?)"
			+ " and t.taluk_id like if(?=0,'%%',?)"
			+ " and d.district_id like if(?=0,'%%',?)"
			+ " and s.state_id like if(?=0,'%%',?)";

	private static final String DELETE_VILLAGE = "delete from village where village_id=?";

	private static final String GET_SINGLE_VILLAGE_DATA = "select v.village_id,v.village_name,i.hobli_id,t.taluk_id,d.district_id,s.state_id"
			+ " from village v LEFT JOIN (state s,district d,taluk t,hobli i)"
			+ " on i.taluk_id=t.taluk_id"
			+ " and v.hobli_id=i.hobli_id "
			+ " and t.district_id=d.district_id"
			+ " and d.state_id=s.state_id"
			+ " where v.village_id=?";
	
	

	private static final String DELETE_TALUK_DETAILS = "delete from taluk where taluk_id=?";
	@Override
	public List<Map<String, Object>> getAllStateList() {
		return template.queryForList(GET_ALL_STATE_LIST);
	}

	@Override
	public int addState(StateBean bean) {
		BeanPropertySqlParameterSource source=new BeanPropertySqlParameterSource(bean);
		int count = ntemplate.update(ADD_STATE, source);
		return count;
	}

	@Override
	public int updateState(StateBean bean) {
		BeanPropertySqlParameterSource source=new BeanPropertySqlParameterSource(bean);
		int count = ntemplate.update(UPDATE_STATE_DETAILS, source);
		return count;
	}

	@Override
	public int deleteStateBean(int state_id) {
		int count = template.update(DELETE_STATE_DETAILS, new Object[] {state_id});
		return count;
	}

	
	@Override
	public StateBean getSingleStateDetails(int state_id) {
		StateBean bean = template.queryForObject(GET_SINGLE_STATE_DETAILS, new RowMapper<StateBean>() {
			@Override
			public StateBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				StateBean bean=new StateBean();
				bean.setState_id(rs.getInt("state_id"));
				bean.setState_name(rs.getString("state_name"));
				bean.setState_code(rs.getInt("state_code"));
				bean.setRegion_name(rs.getString("region_name"));
				return bean;
			}
		},new Object[] {state_id});
		return bean;
	}

	@Override
	public Map<Integer, String> getStateListForOption(){
		return template.query(GET_STATE_LIST_FOR_OPTION, new ResultSetExtractor<Map<Integer,String>>(){
			@Override
			public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<Integer,String> optionData = new HashMap<>();
				while(rs.next()) {
					optionData.put(rs.getInt(1), rs.getString(2));
				}
				return optionData;
			}
		});
	}

	@Override
	public List<Map<String, Object>> getAllDistrictList(int state_id, String search, int start, int length) {
		List<Map<String,Object>> districtList = template.queryForList(GET_ALL_DISTRICT_LIST, new Object[] {"%"+search+"%",state_id,state_id,start,length});
		return districtList;
	}

	@Override
	public int countAllDistrict(int state_id, String search) {
		return template.queryForObject(COUNT_ALL_DISTRICT_LIST, Integer.class, new Object[] {"%"+search+"%",state_id,state_id});
	}


	@Override
	public int deleteDistrict(int district_id) {
		return template.update(DELETE_DISTRICT,new Object[] {district_id});
	}

	@Override
	public int addDistrict(DistrictBean bean) {
		BeanPropertySqlParameterSource source=new BeanPropertySqlParameterSource(bean);
		return ntemplate.update(ADD_DISTRICT, source);
	}

	@Override
	public int updateDistrict(DistrictBean bean) {
		BeanPropertySqlParameterSource source=new BeanPropertySqlParameterSource(bean);
		return ntemplate.update(UPDATE_DISTRICT, source);
	}

	
	@Override
	public DistrictBean getSingleDistrictDetails(int district_id) {
		return template.queryForObject(GET_SINGLE_DISTRICT_DETAILS,new RowMapper<DistrictBean>() {
			@Override
			public DistrictBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				DistrictBean bean=new DistrictBean();
				bean.setDistrict_id(rs.getInt("district_id"));
				bean.setDistrict_name(rs.getString("district_name"));
				bean.setState_id(rs.getInt("state_id"));
				bean.setDistrict_status(rs.getString("district_status"));
				return bean;
			}
		}, new Object[] {district_id});
	}

	
	
	
	@Override
	public List<Map<String, Object>> getAllHobliList(int state_id, int district_id, int taluk_id, String search,
			int start, int length) {
		return template.queryForList(GET_HOBLI_LIST, new Object[] {
				"%"+search+"%",
				taluk_id,taluk_id,
				district_id,district_id,
				state_id,state_id,
				start,
				length
		});
	}

	@Override
	public int countHobliList(int state_id, int district_id, int taluk_id, String search) {
		return template.queryForObject(COUNT_HOBLI_LIST, Integer.class, new Object[] {
				"%"+search+"%",
				taluk_id,taluk_id,
				district_id,district_id,
				state_id,state_id	
		});
	}

	
	@Override
	public Map<Integer, String> getStateWiseDistrictList(int state_id) {
		return template.query(GET_STATE_WISE_DISTRICT_LIST, new ResultSetExtractor<Map<Integer,String>>(){
			@Override
			public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<Integer,String> data = new HashMap<>();
				while(rs.next()) {
					data.put(rs.getInt(1), rs.getString(2));
				}
				return data;
			}
			
		}, new Object[] {state_id});
	}
	
	
	@Override
	public int addTaluk(TalukBean bean) {
		BeanPropertySqlParameterSource source=new BeanPropertySqlParameterSource(bean);
		int count = ntemplate.update(ADD_TALUK, source);
		return count;
	}

	@Override
	public int updateTaluk(TalukBean bean) {
		BeanPropertySqlParameterSource source=new BeanPropertySqlParameterSource(bean);
		int count = ntemplate.update(UPDATE_TALUK_DETAILS, source);
		return count;
	}
	
	@Override
	public Object getAllTalukList(int state_id, int district_id,String search, int start, int length) {
		
		List<Map<String,Object>> talukList = template.queryForList(GET_ALL_TALUK_LIST, new Object[] {"%"+search+"%",state_id,state_id,district_id,district_id,start,length});
		return talukList;
		
	}

	@Override
	public int countAllTaluk(int state_id, int district_id, String search) {
		return template.queryForObject(COUNT_ALL_TALUK_LIST, Integer.class, new Object[] {"%"+search+"%",state_id,state_id,district_id,district_id});
	}

	public Map<String, Object> getSingleTalukDetails(int taluk_id) {
		// TODO Auto-generated method stub
		return template.queryForMap(GET_SINGLE_TALUK_DETAILS, new Object[] {taluk_id});
	}


	public int deleteTalukBean(int taluk_id) {
		int count = template.update(DELETE_TALUK_DETAILS, new Object[] {taluk_id});
		return count;
	}
	
	

	@Override
	public Map<Integer, String> getDistrictWiseTalukListForOption(int district_id) {
		return template.query(GET_DISTRICT_WISE_TALUK_FOR_OPTION,new ResultSetExtractor<Map<Integer,String>>(){
			@Override
			public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				 Map<Integer,String> data = new HashMap<>();
				 while(rs.next()) {
					 data.put(rs.getInt(1), rs.getString(2));
				 }
				return data;
			}
		},new Object[] {district_id});
	}

	@Override
	public int addHobli(HobliBean bean) {
		BeanPropertySqlParameterSource source=new BeanPropertySqlParameterSource(bean);
		return ntemplate.update(ADD_HOBLI, source);
	}

	@Override
	public int updateHobli(HobliBean bean) {
		BeanPropertySqlParameterSource source=new BeanPropertySqlParameterSource(bean);
		return ntemplate.update(UPDATE_HOBLI, source);
	}

	
	@Override
	public int deleteHobli(int hobli_id) {
		return template.update(DELETE_HOBLI, new Object[] {hobli_id});
	}

	@Override
	public Map<String, Object> getSingleHobliData(int hobli_id) {
		return template.queryForMap(GET_SINGLE_HOBLI_DATA, new Object[] {hobli_id});
	}

	@Override
	public Map<Integer, String> getTalukWiseHobliListForOption(int taluk_id) {
		return template.query(GET_TALUK_WISE_HOBLI_FOR_OPTION,new ResultSetExtractor<Map<Integer,String>>(){
			@Override
			public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<Integer,String> data=new HashMap<>();
				while(rs.next()) 
					data.put(rs.getInt("hobli_id"), rs.getString("hobli_name"));
				return data;
			}
			
		},new Object[] {taluk_id});
	}

	
	@Override
	public Map<Integer, String> getHobliWiseVillageListForOption(int hobli_id) {
		return template.query(GET_HOBLI_WISE_VILLAGE_LIST_FOR_OPTION,new ResultSetExtractor<Map<Integer,String>>(){
			@Override
			public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<Integer,String> data=new HashMap<>();
				while(rs.next()) {
					data.put(rs.getInt("village_id"), rs.getString("village_name"));
				}
				return data;
			}
			
		},new Object[] {hobli_id});
	}
	
	public int addVillage(@Valid VillageBean bean) {
		BeanPropertySqlParameterSource source=new BeanPropertySqlParameterSource(bean);
		return ntemplate.update(ADD_VILLAGE, source);
	}

	public int updateVillage(@Valid VillageBean bean) {
		BeanPropertySqlParameterSource source=new BeanPropertySqlParameterSource(bean);
		return ntemplate.update(UPDATE_VILLAGE, source);
	}

	public Object getAllVillageList(int state_id, int district_id, int taluk_id, int hobli_id, String search, int start,
			int length) {
		return template.queryForList(GET_VILLAGE_LIST, new Object[] {
				"%"+search+"%",
				hobli_id,hobli_id,
				taluk_id,taluk_id,
				district_id,district_id,
				state_id,state_id,
				start,
				length
		});
	}

	public int countVillageList(int state_id, int district_id, int taluk_id, int hobli_id, String search) {
		return template.queryForObject(COUNT_VILLAGE_LIST, Integer.class, new Object[] {
				"%"+search+"%",
				hobli_id,hobli_id,
				taluk_id,taluk_id,
				district_id,district_id,
				state_id,state_id	
		});
	}

	public int deleteVillage(int village_id) {
		return template.update(DELETE_VILLAGE, new Object[] {village_id});
	}

	public Map<String, Object> getSingleVillageData(int village_id) {
		return template.queryForMap(GET_SINGLE_VILLAGE_DATA, new Object[] {village_id});
	}
	
}
