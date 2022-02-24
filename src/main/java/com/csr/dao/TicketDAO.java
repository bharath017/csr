package com.csr.dao;

import java.util.List;
import java.util.Map;

import com.csr.bean.TicketBean;
import com.csr.bean.TicketCategoryBean;
import com.csr.bean.TicketDonorBean;
import com.csr.bean.TicketSubCategoryBean;

public interface TicketDAO {

	public int addTicketCategory(TicketCategoryBean bean);
	public int updateTicketCategory(TicketCategoryBean bean);
	public int deleteTicketCategory(int category_id);
	public List<Map<String,Object>> getTicketCategoryList(String search);
	public Map<Integer,String> getTicketCategoryForOption();
	
	public int addTicketSubCategory(TicketSubCategoryBean bean);
	public int updateticketSubCategory(TicketSubCategoryBean bean);
	public int deleteTicketSubCategory(int sub_category_id);
	public List<Map<String,Object>> getAllTicketSubCategoryList(int category_id,String search);
	public Map<Integer,String> getTicketSubCategoryForOption(int category_id);
	
	public int addTicket(TicketBean bean);
	public int updateTicket(TicketBean bean);
	public int cancelTicket(int ticket_id);
	
	public List<Map<String,Object>> getTicketList(int school_id,int category_id,int sub_category_id,String ticket_status,int start, int length);
	public int countTicketList(int school_id,int category_id,int sub_category_id,String ticket_status);
	public Map<String,Object> getSingleTicketDetails(int ticket_id);
	
	public int changeTicketStatus(int ticket_id,String ticket_status);
	public Object getTicketImages(int ticket_id);
	public int uploadAndGetEstimatdDocument(int ticket_id,Object estimated_documents);
	public String getEstimatedDocuments(int ticket_id);
	public int addEstimatedAmount(int ticket_id,double estimated_amount);
	public int approveEstimatedAmount(int ticket_id);
	public int addTicketDonor(TicketDonorBean bean);
	public List<Map<String,Object>> getDonorListForTicket(int ticket_id);
	public int deleteTicketDonor(int ticket_donor_id);
	public int uploadCompletionImage(int ticket_id,Object completion_documents);
	public String getTicketCompletionDocuments(int ticket_id);
	public int updateTicketCompletion(int ticket_id,String completion_description);
	
	
	
	
	
	
	
}
