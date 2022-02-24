package com.csr.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.csr.bean.SchoolBean;
import com.csr.bean.SchoolImageBean;

@Repository
public class SchoolDAOImpl implements SchoolDAO {

	private static final String ADD_SCHOOL_DETAILS = "Insert into school (school_name,school_address,hm_name,hm_phone,school_strength, "
			+ " no_of_teacher,village_id,pin_code,sdmc_president_name,sdmc_president_phone) "
			+ " values (:school_name,:school_address,:hm_name,:hm_phone,:school_strength, " 
			+ " :no_of_teacher,:village_id,:pin_code,:sdmc_president_name,:sdmc_president_phone)";
	
	private static final String GET_LAST_ADDED_SCHOOL_ID="select max(school_id) from school";
	
	private static final String ADD_SCHOOL_IMAGE = "insert into school_image(image_path,school_id) values(?,?)";
	
	private static final String GET_SCHOOL_LIST="select s.school_id,s.school_name,s.school_address,s.hm_name,s.hm_phone,s.school_strength,s.no_of_teacher,"
											+" v.village_name,h.hobli_name,t.taluk_name,d.district_name,st.state_name"
											+" from school s LEFT JOIN (state st,district d,hobli h,village v,taluk t)"
											+" ON s.village_id=v.village_id"
											+" and v.hobli_id=h.hobli_id"
											+" and h.taluk_id=t.taluk_id"
											+" and t.district_id=d.district_id"
											+" and d.state_id=st.state_id"
											+" where s.school_name like ?"
											+" order by school_id desc"
											+" limit ?,?";
	
	private static final String COUNT_SCHOOL_LIST="select count(s.school_id) as count"
											+" from school s LEFT JOIN (state st,district d,hobli h,village v,taluk t)"
											+" ON s.village_id=v.village_id"
											+" and v.hobli_id=h.hobli_id"
											+" and h.taluk_id=t.taluk_id"
											+" and t.district_id=d.district_id"
											+" and d.state_id=st.state_id"
											+" where s.school_name like ?";
	private static final String DELETE_SCHOOL_DATA="delete from school where school_id=?";
	
	private static final String GET_SCHOOL_LIST_BY_VILLAGE_ID="select school_id,school_name from school where village_id=?";
	
	private static final String GET_ALL_SCHOOL_FOR_OPTION="select school_id,school_name from school";
	
	@Autowired
	private NamedParameterJdbcTemplate ntemplate;
	
	@Autowired
	private JdbcTemplate template;
	
	
	
	@Override
	public int addSchoolData(SchoolBean bean) {
		BeanPropertySqlParameterSource source =new BeanPropertySqlParameterSource(bean);
		return ntemplate.update(ADD_SCHOOL_DETAILS, source);
	}

	
	@Override
	public int getLastAddedSchoolId() {
		return template.queryForObject(GET_LAST_ADDED_SCHOOL_ID, Integer.class);
	}


	@Override
	public int[] addSchoolImage(SchoolImageBean[] bean) {
		return template.batchUpdate(ADD_SCHOOL_IMAGE, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, bean[i].getImage_path());
				ps.setInt(2, bean[i].getSchool_id());
			}
			public int getBatchSize() {
				return bean.length;
			}

		});
	}

	@Override
	public List<Map<String, Object>> getSchoolList(String school_name, int start, int length) {
		return template.queryForList(GET_SCHOOL_LIST, new Object[] {"%"+school_name+"%",start,length});
	}

	@Override
	public int countSchoolList(String school_name) {
		return template.queryForObject(COUNT_SCHOOL_LIST, Integer.class,new Object[] {"%"+school_name+"%"});
	}


	@Override
	public int deleteSchool(int school_id) {
		return template.update(DELETE_SCHOOL_DATA,new Object[] {school_id});
	}

	@Override
	public Map<Integer, String> getSchoolListByVillageForOption(int village_id) {
		return template.query(GET_SCHOOL_LIST_BY_VILLAGE_ID,new ResultSetExtractor<Map<Integer,String>>(){
			@Override
			public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<Integer,String> data = new HashMap<>();
				while(rs.next()) {
					data.put(rs.getInt(1), rs.getString(2));
				}
				return data;
			}
		},new Object[] {village_id});
	}

	
	
	@Override
	public Map<Integer, String> getAllSchoolForOption() {
		return template.query(GET_ALL_SCHOOL_FOR_OPTION,new ResultSetExtractor<Map<Integer,String>>(){
			@Override
			public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<Integer,String> data=new HashMap<>();
				while(rs.next()) {
					data.put(rs.getInt(1), rs.getString(2));
				}
				return data;
			}
		});
	}
	
	

	
	
}
