package com.csr.bean;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StateBean {
	private int state_id;
	@NotBlank
	private String state_name;
	@NotBlank
	private String region_name;
	private int state_code;
}
