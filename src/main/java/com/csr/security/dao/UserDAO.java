package com.csr.security.dao;

import java.util.ArrayList;
import java.util.List;

import com.csr.security.bean.UserBean;



public interface UserDAO {

	public UserBean getSingleUserDetailsUsingUsername(String username);
	
	public ArrayList<String> getRoleDetailsByUserName(String username);
	
	public UserBean getUserDetailsByUserName(String username);
	
	public int insertUser(UserBean bean);
	
	public int updateUser(UserBean bean);
	
	public int deactivateUser(Long user_id);
	
	public boolean checkAvailableOfUserName(String user_name);
	
	public List<UserBean> getAllUserList(String name_phone,int client_id,int branch_id,int index,int length);
	
	public int getLengthOfUserList(String name_phone,int client_id,int branch_id);
	
	public UserBean getUserDetailsForEdit(Long user_id);
	
	public List<String> getAllUserRoleByUserId(Long user_id);
	
	public UserBean getFullDetailsByUserId(Long user_id);
	
	public int insertUserRoles(String[] roles,Long user_id);
	
	public boolean getAvailabilityOfUserRole(Long user_id,String user_role);
	
	public int deleteUserRoles(String roles,Long user_id);
	
	
}
