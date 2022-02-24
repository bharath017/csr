package com.csr.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DonorBean {
	private int donor_id;
	private String donor_name;
	private String donor_address;
	private String donor_contact_person_name;
	private String donor_contact_person_phone;
}
