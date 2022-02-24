package com.csr.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csr.bean.TicketBean;
import com.csr.bean.TicketCategoryBean;
import com.csr.bean.TicketDonorBean;
import com.csr.bean.TicketSubCategoryBean;
import com.csr.dao.TicketDAOImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Service
public class TicketService {
	
	@Autowired
	private TicketDAOImpl dao;
	
	public int addTicketCategory(TicketCategoryBean bean) {
		return dao.addTicketCategory(bean);
	}
	
	public int updateTicketCategory(TicketCategoryBean bean) {
		return dao.updateTicketCategory(bean);
	}
	
	public int deleteTicketCategory(int category_id) {
		return dao.deleteTicketCategory(category_id);
	}
	
	
	public List<Map<String,Object>> getTicketCategoryList(String search){
		return dao.getTicketCategoryList(search);
	}
	
	public int addTicketSubCategory(TicketSubCategoryBean bean) {
		return dao.addTicketSubCategory(bean);
	}
	
	public int updateTicketSubCategory(TicketSubCategoryBean bean) {
		return dao.updateticketSubCategory(bean);
	}
	
	public int deleteTicketSubCategory(int sub_category_id) {
		return dao.deleteTicketSubCategory(sub_category_id);
	}
	
	public List<Map<String,Object>> getTicketSubCategoryList(String search, int category_id){
		return dao.getAllTicketSubCategoryList(category_id, search);
	}
	
	public Map<Integer,String> getTicketCategoryForOption(){
		return dao.getTicketCategoryForOption();
	}
	
	public Map<Integer,String> getTicketSubCategoryForOption(int category_id){
		return dao.getTicketSubCategoryForOption(category_id);
	}
	
	public int addTicket(TicketBean bean) {
		return dao.addTicket(bean);
	}
	
	public Map<String,Object> getAllTicketList(int school_id,int category_id,int sub_category_id,String ticket_status,int start, int length,int draw){
		Map<String,Object> data = new HashMap<>();
		data.put("data",dao.getTicketList(school_id, category_id, sub_category_id, ticket_status, start, length));
		data.put("draw", draw);
		int recordCount=dao.countTicketList(school_id, category_id, sub_category_id, ticket_status);
		data.put("recordsTotal", recordCount);
		data.put("recordsFiltered", recordCount);
		return data;
	}
	
	
	public Map<String,Object> getSingleTicketDetails(int ticket_id){
		return dao.getSingleTicketDetails(ticket_id);
	}
	
	public int changeTicketStatus(int  ticket_id,String ticket_status) {
		return dao.changeTicketStatus(ticket_id, ticket_status);
	}
	
	public Object getTicketImages(int ticket_id) {
		return dao.getTicketImages(ticket_id);
	}
	
	public int updateEstimatedDocuments(int ticket_id,List<String> documents) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String estimatedDocuments = dao.getEstimatedDocuments(ticket_id);
		if(estimatedDocuments!=null) {
			List<String> list = mapper.readValue(estimatedDocuments, List.class);
			list.addAll(new ArrayList<>(documents));
			String jsonString = mapper.writeValueAsString(list);
			return dao.uploadAndGetEstimatdDocument(ticket_id, jsonString);
		}else {
			String jsonString = mapper.writeValueAsString(documents);
			return dao.uploadAndGetEstimatdDocument(ticket_id, jsonString);
		}
	}
	
	public String getAllEstimatedDocumentsList(int ticket_id) {
		return dao.getEstimatedDocuments(ticket_id);
	}
	
	public String removeEstimatedDocs(int ticket_id,List<String> data) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String jsonString = mapper.writeValueAsString(data);
		dao.uploadAndGetEstimatdDocument(ticket_id, jsonString);
		return dao.getEstimatedDocuments(ticket_id);
	}
	
	public int updateEstimatedAmount(int ticket_id,double amount,String estimated_status) {
		if(estimated_status.equals("pending")) {
			return dao.addEstimatedAmount(ticket_id, amount);
		}
		return -1;
	}
	
	public int approveEstimatedAmounts(int ticket_id) {
		return dao.approveEstimatedAmount(ticket_id);
	}
	
	public int addTicketDonor(TicketDonorBean bean) {
		return dao.addTicketDonor(bean);
	}
	
	public List<Map<String,Object>> getDonorListForTicket(int ticket_id){
		return dao.getDonorListForTicket(ticket_id);
	}
	
	public int deleteTicketDonor(int ticket_donor_id) {
		return dao.deleteTicketDonor(ticket_donor_id);
	}
	
	public int updateCompletionDocuments(int ticket_id,List<String> documents) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String jsonString = mapper.writeValueAsString(documents);
		return dao.uploadCompletionImage(ticket_id, jsonString);
	}
	
	public String getTicketCompletionDocument(int ticket_id) {
		return dao.getTicketCompletionDocuments(ticket_id);
	}
	
	public Map<String,Object> updateTicketCompletion(int ticket_id,String completion_description){
		Map<String,Object> data = dao.getSingleTicketDetails(ticket_id);
		if(!data.get("ticket_status").equals("completed")) {
			dao.updateTicketCompletion(ticket_id, completion_description);
		}
		return data;
	}
	
}
