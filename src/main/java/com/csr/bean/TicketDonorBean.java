package com.csr.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TicketDonorBean {
	private int ticket_donor_id;
	private int donor_id;
	private double amount;
	private int ticket_id;
}
