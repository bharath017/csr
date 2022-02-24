package com.csr.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SchoolImageBean {
	private int id;
	private String image_path;
	private int school_id;
}
