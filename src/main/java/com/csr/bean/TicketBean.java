package com.csr.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TicketBean {
	private int ticket_id;
	private int school_id;
	private int category_id;
	private int sub_category_id;
	private String description;
	private double estimated_amount;
	private String ticket_date;
	private String estimated_approval;
	private Object images;
}
