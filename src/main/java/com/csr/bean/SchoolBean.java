package com.csr.bean;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SchoolBean {
	private int school_id;
	@NotBlank(message = "Enter school name")
	private String school_name;
	@NotBlank(message = "Enter school address")
	private String school_address;
	@NotBlank(message = "Enter HM Name")
	private String hm_name;
	@NotBlank(message = "Enter HM Phone")
	private String hm_phone;
	@NotBlank(message = "Enter Strength")
	private int school_strength;
	private int no_of_teacher;
	private int village_id;
	private int pin_code;
	private String sdmc_president_name;
	private String sdmc_president_phone;
	private int cluster_id;
	private SchoolImageBean[] school_image;
}
