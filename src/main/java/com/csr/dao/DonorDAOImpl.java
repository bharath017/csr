package com.csr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.csr.bean.DonorBean;

@Repository
public class DonorDAOImpl implements DonorDAO{
	
	@Autowired
	private NamedParameterJdbcTemplate ntemplate;
	
	@Autowired
	private JdbcTemplate template;
	
	private static final String ADD_DONER_DETAILS="insert into donor(donor_name,donor_address,donor_contact_person_name,donor_contact_person_phone)"
			+ " values(:donor_name,:donor_address,:donor_contact_person_name,:donor_contact_person_phone)";
	
	private static final String UPDATE_DONER_DETAILS="update donor set donor_name=:donor_name,donor_address=:donor_address,donor_contact_person_name=:donor_contact_person_name,"
			+ "donor_contact_person_phone=:donor_contact_person_phone where donor_id=:donor_id";
	
	private static final String DELETE_DONER_DETAILS="delete form donor where donor_id=?";
	
	private static final String GET_SINGLE_DONER_DETAILS="select * from donor where donor_id=?";

	private static final String GET_ALL_DONOR_LIST = "select * from donor where donor_name like ? order by donor_id desc limit ?,?";

	private static final String COUNT_ALL_DONOR_LIST = "select count(donor_id) from donor where donor_name like ?";
	
	private static final String GET_DONORS_FOR_OPTION="select donor_id,donor_name from donor";
	
	@Override
	public int addDonor(DonorBean bean) {
		BeanPropertySqlParameterSource srouce=new BeanPropertySqlParameterSource(bean);
		return ntemplate.update(ADD_DONER_DETAILS, srouce);
	}

	@Override
	public int updateDonor(DonorBean bean) {
		BeanPropertySqlParameterSource source=new BeanPropertySqlParameterSource(bean);
		return ntemplate.update(UPDATE_DONER_DETAILS, source);
	}

	@Override
	public int deleteDonor(int donor_id) {
		return template.update(DELETE_DONER_DETAILS,new Object[] {donor_id});
	}

	@Override
	public Map<String, Object> getSingleDonorDetails(int donor_id) {
		return template.queryForMap(GET_SINGLE_DONER_DETAILS,new Object[] {donor_id});
	}

	public Object getAllDonorList(String search, int start, int length) {
		List<Map<String,Object>> talukList = template.queryForList(GET_ALL_DONOR_LIST, new Object[] {"%"+search+"%",start,length});
		return talukList;
	}

	public int countDonorList(String search) {
		return template.queryForObject(COUNT_ALL_DONOR_LIST, Integer.class, new Object[] {"%"+search+"%" });
	}

	@Override
	public Map<Integer, String> getDonorsForOption() {
		return template.query(GET_DONORS_FOR_OPTION,new  ResultSetExtractor<Map<Integer,String>>() {
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
