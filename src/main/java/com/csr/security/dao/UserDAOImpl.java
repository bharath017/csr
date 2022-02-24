package com.csr.security.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.csr.security.bean.UserBean;



@Component
public class UserDAOImpl implements UserDAO {
	
	private static final String GET_USER_DETAILS="select * from user where login_id=?";
	
	private static final String GET_ROLE_DETAILS="select role_name from user_role ur,user u where ur.user_id=u.user_id and u.login_id=?";
	
	//declare all query here
	private static final String INSERT_USER="insert into user(name,user_name,user_password,user_email,branch_id,user_phone,user_type) values(:name,:user_name,:user_password,:user_mail,:branch_id,:user_phone,:user_type)";
	
	private static final String UPDATE_USER="update user set name=:name,user_password=:user_password,user_email=:user_mail,branch_id=:branch_id,company_id=:company_id where user_id=:user_id";
	
	private static final String DEACTIVATE_USER="update user_status= where user_id=:user_id";
	
	private static final String CHECK_USER_AVAILABILITY="select count(*) as count from user where user_name=?";
	
	private static final String GET_ALL_USER="select u.*,c.client_name,(select branch_name from branch where branch_id=u.branch_id) as branch_name"
			+ " from user u,client c where u.company_id=c.client_id and (user_name like ? or user_phone like ?)"
			+ " and company_id like if(0=?,'%%',?) and branch_id like if(0=?,'%%',?) order by user_id desc limit ?,10";
	
	 
	private static final String GET_LENGTH_OF_USER_LIST="select count(*) as length"
			+ " from user u,client c where u.company_id=c.client_id and (user_name like ? or user_phone like ?)"
			+ " and company_id like if(0=?,'%%',?) and branch_id like if(0=?,'%%',?)";
	
	private static final String GET_USER_DETAILS_FOR_UPDATE="select * from user where user_id=?";
	
	private static final String GET_USER_ROLES="select user_role from user_role where user_id=?";
	
	private static final String GET_FULL_DETAILS_BY_USERID="select u.*,c.client_name,(select branch_name from branch where branch_id=u.branch_id) as branch_name "
			+ " from user u,client c where u.company_id=c.client_id and u.user_id=?";
	
	private static final String INSERT_USER_ROLE="insert into user_role(user_role,user_id) values(?,?)";
	
	private static final String CHECK_AVAILABLE_OF_USER_ROLE="select count(*) as count from user_role where user_id=? and user_role=?";
	
	private static final String DELETE_USER_ROLES="delete from user_role where user_id=? and user_role not in (?)";
	
	
	
	
	@Autowired
	public JdbcTemplate template;
	
	@Autowired
	private  NamedParameterJdbcTemplate ntemplate;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	

	@Override
	public UserBean getSingleUserDetailsUsingUsername(String username){
		UserBean userbean=(UserBean)template.queryForObject(GET_USER_DETAILS,new RowMapper<UserBean>() {
			@Override
			public UserBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserBean bean=new UserBean();
				bean.setUser_name(rs.getString("login_id"));
				bean.setUser_password(rs.getString("password"));
				boolean enabled=rs.getString("user_status").equals("active")?true:false;
				bean.setEnabled(enabled);
				bean.setName(rs.getString("user_name"));
				bean.setUser_phone(rs.getString("user_phone"));
				bean.setUser_id(rs.getInt("user_id"));
				return bean;
			}
		},new Object[] {new String(username)});
		return userbean;
	}

	@Override
	public ArrayList<String> getRoleDetailsByUserName(String username){
		@SuppressWarnings("deprecation")
		ArrayList<String> roles=template.query(GET_ROLE_DETAILS,new Object[] {new String(username)},new ResultSetExtractor<ArrayList<String>>() {
			@Override
			public ArrayList<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				ArrayList<String> roles=new ArrayList<>();
				while(rs.next()) {
					roles.add(rs.getString("role_name"));
				}
				return roles;
			}
		});
		return roles;
	}



	@Override
	public UserBean getUserDetailsByUserName(String username){
		UserBean bean=this.getSingleUserDetailsUsingUsername(username);
		ArrayList<String> roles=this.getRoleDetailsByUserName(username);
		bean.setRoles(roles.toArray(new String[roles.size()]));
		return bean;
	}
	
	@Override
	public int insertUser(UserBean bean) {
		BeanPropertySqlParameterSource source=new BeanPropertySqlParameterSource(bean);
		int count=ntemplate.update(INSERT_USER, source);
		return count;
	}

	@Override
	public int updateUser(UserBean bean) {
		BeanPropertySqlParameterSource source=new BeanPropertySqlParameterSource(bean);
		int count=ntemplate.update(UPDATE_USER, source);
		return count;
	}

	@Override
	public int deactivateUser(Long user_id) {
		MapSqlParameterSource source=new MapSqlParameterSource();
		source.addValue("user_id", user_id);
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public boolean checkAvailableOfUserName(String user_name) {
		@SuppressWarnings("deprecation")
		int available=template.queryForObject(CHECK_USER_AVAILABILITY, new Object[] {user_name},new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				int count=rs.getInt("count");
				return count;
			}
			
		});
		if(available>0)
			return true;
		else
			return false;
	}



	@Override
	public List<UserBean> getAllUserList(String name_phone,int client_id,int branch_id,int index,int length) {
		
		@SuppressWarnings("deprecation")
		List<UserBean> userlist=template.query(GET_ALL_USER,new Object[] {"%"+name_phone+"%","%"+name_phone+"%",client_id,client_id,branch_id,branch_id,index},new ResultSetExtractor<List<UserBean>>() {
			@Override
			public List<UserBean> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<UserBean> userlist=new ArrayList<>();
				while(rs.next()) {
					UserBean bean=new UserBean();
					bean.setName(rs.getString("name"));
					bean.setUser_id(rs.getInt("user_id"));
					bean.setUser_name(rs.getString("user_name"));
					bean.setUser_mail(rs.getString("user_email"));
					bean.setUser_password(encoder().encode(rs.getString("user_password")));
					bean.setUser_phone(rs.getString("user_phone"));
					userlist.add(bean);
				}
				return userlist;
			}
			
		});
		
		return userlist;
	}



	@Override
	public int getLengthOfUserList(String name_phone, int client_id, int branch_id) {
		int length=template.queryForObject(GET_LENGTH_OF_USER_LIST,new Object[] {"%"+name_phone+"%","%"+name_phone+"%",client_id,client_id,branch_id,branch_id},Integer.class);
		return length;
	}
	
	
	public static BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}



	@Override
	public UserBean getUserDetailsForEdit(Long user_id) {
		@SuppressWarnings("deprecation")
		UserBean bean=template.queryForObject(GET_USER_DETAILS_FOR_UPDATE,new Object[] {user_id},new RowMapper<UserBean>() {
			@Override
			public UserBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserBean bean=new UserBean();
				bean.setUser_id(rs.getInt("user_id"));
				bean.setName(rs.getString("name"));
				bean.setUser_mail(rs.getString("user_email"));
				bean.setUser_name(rs.getString("user_name"));
				bean.setUser_phone(rs.getString("user_phone"));
				return bean;
			}
			
		});
		return bean;
	}



	@Override
	public List<String> getAllUserRoleByUserId(Long user_id) {
		@SuppressWarnings("deprecation")
		List<String> rolelist=template.query(GET_USER_ROLES,new Object[] {user_id},new ResultSetExtractor<List<String>>() {
			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<String> list=new ArrayList<>();
				while(rs.next()) {
					list.add(rs.getString(1));
				}
				return list;
			}
		});
		return rolelist;
	}



	@Override
	public UserBean getFullDetailsByUserId(Long user_id) {
		@SuppressWarnings("deprecation")
		UserBean bean=template.queryForObject(GET_FULL_DETAILS_BY_USERID,new Object[] {user_id},new RowMapper<UserBean>() {
			@Override
			public UserBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserBean bean=new UserBean();
				bean.setUser_id(rs.getInt("user_id"));
				bean.setName(rs.getString("name"));
				bean.setUser_mail(rs.getString("user_email"));
				bean.setUser_name(rs.getString("user_name"));
				bean.setUser_phone(rs.getString("user_phone"));
				return bean;
			}
			
		});
		return bean;
	}



	@Override
	public int insertUserRoles(String[] roles, Long user_id) {
		int count=0;
		for(String role:roles) {
			boolean available=this.getAvailabilityOfUserRole(user_id, role);
			if(available==false) {
				int insert=template.update(INSERT_USER_ROLE,new Object[] {role,user_id});
				if(insert>0)
					count++;
			}
		}
		return count;
	}



	@Override
	public boolean getAvailabilityOfUserRole(Long user_id, String user_role) {
		@SuppressWarnings("deprecation")
		int count=template.queryForObject(CHECK_AVAILABLE_OF_USER_ROLE,new Object[] {user_id,user_role},Integer.class);
		
		if(count>0)
			return true;
		else
			return false;
	}



	@Override
	public int deleteUserRoles(String roles,Long user_id) {
		int count=template.update("delete from user_role where user_id=? and user_role not in ("+roles+")",new Object[] {user_id});
		return count;
	}



	
	
}

