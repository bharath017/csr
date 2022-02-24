package com.csr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.data.xml.DatasetTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.csr.bean.TicketBean;
import com.csr.bean.TicketCategoryBean;
import com.csr.bean.TicketDonorBean;
import com.csr.bean.TicketSubCategoryBean;

@Repository
public class TicketDAOImpl implements TicketDAO {
	
	@Autowired
	private JdbcTemplate template;

	@Autowired
	private NamedParameterJdbcTemplate ntemplate;
	
	
	private static final String ADD_CATEGORY="insert into ticket_category(category_name) values(:category_name)";
	private static final String UPDATE_CATEGORY="update ticket_category set category_name=:category_name where category_id=:category_id";
	private static final String DELETE_CATEGORY="delete from ticket_category where category_id=?";
	private static final String GET_TICKET_CATEGORY_LIST="select category_id,category_name from ticket_category where category_name like ? order by category_name asc";
	private static final String GET_TICKET_CATEGORY_FOR_OPTIONS="select category_id,category_name from ticket_category";
	
	private static final String ADD_TICKET_SUB_CATEGORY="insert into ticket_sub_category(sub_category_name,category_id) values(:sub_category_name,:category_id)";
	private static final String UPDATE_TICKTE_SUB_CATEGORY="update ticket_sub_category set sub_category_name=:sub_category_name,category_id=:category_id"
			+ " where sub_category_id=:sub_category_id";
	private static final String DELETE_TICKET_SUB_CATEGORY="delete from ticket_sub_category where sub_category_id=?";
	private static final String GET_ALL_TICKET_SUB_CATEGORY_LIST="select s.sub_category_id,s.sub_category_name,s.category_id,c.category_name"
														+ " from ticket_sub_category s join (ticket_category c)"
														+ " ON s.category_id=c.category_id "
														+ " where s.sub_category_name like ?"
														+ " and s.category_id like if(?=0,'%%',?)"
														+ " order by c.category_name asc";
	
	private static final String GET_TICKET_SUB_CATEGORY_FOR_OPTION="select sub_category_id,sub_category_name from ticket_sub_category where category_id=?";
	
	private static final String ADD_TICKET="insert into ticket(school_id,category_id,sub_category_id,description,images) values(:school_id,:category_id,:sub_category_id,:description,:images)";
	
	private static final String GET_ALL_TICKET_LIST="select t.ticket_id,s.school_name,c.category_name,sc.sub_category_name,"
										  +" t.description,DATE_FORMAT(t.ticket_date,'%d/%m/%Y') as ticket_date,t.ticket_status"
										  +" from ticket t LEFT JOIN (school s,ticket_category c,ticket_sub_category sc)"
										  +" ON t.school_id=s.school_id"
										  +" and t.category_id=c.category_id"
										  +" and t.sub_category_id=sc.sub_category_id"
										  +" where t.school_id like if(?=0,'%%',?)"
										  +" and t.category_id like if(?=0,'%%',?)"
										  +" and t.sub_category_id like if(?=0,'%%',?)"
										  +" and t.ticket_status like if(?='','%%',?)"
										  +" order by t.ticket_id desc "
										  +" limit ?,?";
	
	private static final String COUNT_TICKET_LIST="select COUNT(t.ticket_id)"
											  +" from ticket t LEFT JOIN (school s,ticket_category c,ticket_sub_category sc)"
											  +" ON t.school_id=s.school_id"
											  +" and t.category_id=c.category_id"
											  +" and t.sub_category_id=sc.sub_category_id"
											  +" where t.school_id like if(?=0,'%%',?)"
											  +" and t.category_id like if(?=0,'%%',?)"
											  +" and t.sub_category_id like if(?=0,'%%',?)"
											  +" and t.ticket_status like if(?='','%%',?)";
	
	private static final String GET_SINGLE_TICKET_DETAILS="select t.*,s.school_name,c.category_name,sc.sub_category_name,"
													+" v.village_name,h.hobli_name,tk.taluk_name,d.district_name,st.state_name"
													+" from ticket t JOIN (school s,ticket_category c,ticket_sub_category sc,village v,hobli h,taluk tk,district d,state st)"
													+" ON t.school_id=s.school_id"
													+" and t.category_id=c.category_id"
													+" and t.sub_category_id=sc.sub_category_id"
													+" and s.village_id = v.village_id"
													+" and v.hobli_id=h.hobli_id"
													+" and h.taluk_id=tk.taluk_id"
													+" and tk.district_id=d.district_id"
													+" and d.state_id=st.state_id"
													+" where t.ticket_id=?";
	
	private static final String CHANGE_TICKET_STATUS="update ticket set ticket_status=? where ticket_id=?";
	
	private static final String GET_TICKET_IMAGES="select images from ticket where ticket_id=?";
	
	private static final String UPDATE_ESTIMATED_DOCUMENTS="update ticket set estimated_documents=? where ticket_id=?";
	
	private static final String GET_ESTIMATED_DOCUMENTS="select estimated_documents from ticket where ticket_id=?";
	
	private static final String UPDATE_ESTIMATED_AMOUNT="update ticket set estimated_amount=? where ticket_id=?";
	
	private static final String APPROVE_ESTIMATED_AMOUNTS="update ticket set estimated_status='approved' where ticket_id=?";
	
	private static final String ADD_TICKET_DONOR="insert into ticket_donors(donor_id,amount,ticket_id)"
			+ " values(:donor_id,:amount,:ticket_id)";
	
	private static final String GET_DONOR_LIST_FOR_TICKET="select t.ticket_donor_id,d.donor_name,t.donor_id,t.amount,t.ticket_id"
													+" from ticket_donors t JOIN (donor d)"
													+" ON t.donor_id=d.donor_id"
													+" where t.ticket_id=?";
	
	private static final String DELETE_TICKET_DONOR="delete from ticket_donors where ticket_donor_id=?";
	
	private static final String UPDATE_COMPLETION_DOCUMENT="update ticket set completion_documents=? where ticket_id=?";
	
	private static final String GET_COMPLETION_DOCUMENTS="select completion_documents from ticket where ticket_id=?";
	
	private static final String UPDATE_COMPLETION_DESCRIPTION="update ticket set ticket_status='completed',completion_description=? where ticket_id=?";
	
	@Override
	public int addTicketCategory(TicketCategoryBean bean) {
		return ntemplate.update(ADD_CATEGORY, 
				new BeanPropertySqlParameterSource(bean));
	}

	@Override
	public int updateTicketCategory(TicketCategoryBean bean) {
		return ntemplate.update(UPDATE_CATEGORY,
				new BeanPropertySqlParameterSource(bean));
	}

	@Override
	public int deleteTicketCategory(int category_id) {
		return template.update(DELETE_CATEGORY,
				new Object[] {category_id});
	}

	@Override
	public List<Map<String, Object>> getTicketCategoryList(String search) {
		return template.queryForList(GET_TICKET_CATEGORY_LIST,new Object[] {"%"+search+"%"});
	}

	@Override
	public int addTicketSubCategory(TicketSubCategoryBean bean) {
		return ntemplate.update(ADD_TICKET_SUB_CATEGORY,
				new BeanPropertySqlParameterSource(bean));
	}

	@Override
	public int updateticketSubCategory(TicketSubCategoryBean bean) {
		return  ntemplate.update(UPDATE_TICKTE_SUB_CATEGORY,
				new BeanPropertySqlParameterSource(bean));
	}

	@Override
	public int deleteTicketSubCategory(int sub_category_id) {
		return template.update(DELETE_TICKET_SUB_CATEGORY
				,new Object[] {sub_category_id});
	}

	@Override
	public List<Map<String, Object>> getAllTicketSubCategoryList(int category_id, String search) {
		return template.queryForList(GET_ALL_TICKET_SUB_CATEGORY_LIST,
				new Object[] {"%"+search+"%",category_id,category_id});
	}

	@Override
	public Map<Integer, String> getTicketCategoryForOption() {
		return template.query(GET_TICKET_CATEGORY_FOR_OPTIONS,new ResultSetExtractor<Map<Integer,String>>(){
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

	@Override
	public int addTicket(TicketBean bean) {
		BeanPropertySqlParameterSource source=new BeanPropertySqlParameterSource(bean);
		return ntemplate.update(ADD_TICKET, source);
	}

	@Override
	public int updateTicket(TicketBean bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cancelTicket(int ticket_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<Integer, String> getTicketSubCategoryForOption(int category_id) {
		return template.query(GET_TICKET_SUB_CATEGORY_FOR_OPTION,new ResultSetExtractor<Map<Integer,String>>(){
			@Override
			public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<Integer,String> data=new HashMap<>();
				while(rs.next()) {
					data.put(rs.getInt(1), rs.getString(2));
				}
				return data;
			}
		},new Object[] {category_id});
	}

	@Override
	public List<Map<String, Object>> getTicketList(int school_id, int category_id, int sub_category_id,String ticket_status, int start,
			int length) {
		
		return template.queryForList(GET_ALL_TICKET_LIST,
				new Object[] {school_id,school_id,category_id,category_id,
						sub_category_id,sub_category_id,ticket_status,ticket_status,start,length});
	}

	@Override
	public int countTicketList(int school_id, int category_id, int sub_category_id, String ticket_status) {
		return template.queryForObject(COUNT_TICKET_LIST, Integer.class, new Object[] {school_id,school_id,category_id,category_id,
						sub_category_id,sub_category_id,ticket_status,ticket_status});
	}

	
	@Override
	public Map<String, Object> getSingleTicketDetails(int ticket_id) {
		return template.queryForObject(GET_SINGLE_TICKET_DETAILS, new RowMapper<Map<String,Object>>(){
			@Override
			public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
				Map<String,Object> data = new HashMap<>();
				data.put("ticket_id", rs.getInt("ticket_id"));
				data.put("school_id", rs.getInt("school_id"));
				data.put("category_id", rs.getInt("category_id"));
				data.put("sub_category_id", rs.getInt("sub_category_id"));
				data.put("school_name", rs.getString("school_name"));
				data.put("category_name", rs.getString("category_name"));
				data.put("sub_category_name", rs.getString("sub_category_name"));
				data.put("description", rs.getString("description"));
				data.put("ticket_date", rs.getString("ticket_date"));
				data.put("images", rs.getObject("images"));
				data.put("village_name", rs.getString("village_name"));
				data.put("estimated_status", rs.getString("estimated_status"));
				data.put("estimated_amount", rs.getDouble("estimated_amount"));
				data.put("ticket_status", rs.getString("ticket_status"));
				data.put("completion_description", rs.getString("completion_description"));
				return data;
			}
		},new Object[] {ticket_id});
	}

	@Override
	public int changeTicketStatus(int ticket_id, String ticket_status) {
		return template.update(CHANGE_TICKET_STATUS,
				new Object[] {ticket_status,ticket_id});
	}

	@Override
	public Object getTicketImages(int ticket_id) {
		return template.queryForObject(GET_TICKET_IMAGES, 
					Object.class,new Object[] {ticket_id});
	}

	@Override
	public int uploadAndGetEstimatdDocument(int ticket_id,Object estimated_documents) {
		return template.update(UPDATE_ESTIMATED_DOCUMENTS,new Object[] {estimated_documents,ticket_id});
	}

	@Override
	public String getEstimatedDocuments(int ticket_id) {
		return template.queryForObject(GET_ESTIMATED_DOCUMENTS, String.class,ticket_id);
	}

	@Override
	public int addEstimatedAmount(int ticket_id, double estimated_amount) {
		return template.update(UPDATE_ESTIMATED_AMOUNT,new Object[] {estimated_amount,ticket_id});
	}

	@Override
	public int approveEstimatedAmount(int ticket_id) {
		return template.update(APPROVE_ESTIMATED_AMOUNTS,new Object[] {ticket_id});
	}

	@Override
	public int addTicketDonor(TicketDonorBean bean) {
		return ntemplate.update(ADD_TICKET_DONOR, 
				new BeanPropertySqlParameterSource(bean));
	}

	@Override
	public List<Map<String, Object>> getDonorListForTicket(int ticket_id) {
		return template.queryForList(GET_DONOR_LIST_FOR_TICKET,new Object[] {ticket_id});
	}

	@Override
	public int deleteTicketDonor(int ticket_donor_id) {
		return template.update(DELETE_TICKET_DONOR,new Object[] {ticket_donor_id});
	}

	@Override
	public int uploadCompletionImage(int ticket_id, Object completion_documents) {
		return template.update(UPDATE_COMPLETION_DOCUMENT,new Object[] {completion_documents,ticket_id});
	}

	@Override
	public String getTicketCompletionDocuments(int ticket_id) {
		return template.queryForObject(GET_COMPLETION_DOCUMENTS, 
					String.class,new Object[] {ticket_id});
	}

	@Override
	public int updateTicketCompletion(int ticket_id, String completion_description) {
		return template.update(UPDATE_COMPLETION_DESCRIPTION,
				new Object[] {completion_description,ticket_id});
	}

	 
}
