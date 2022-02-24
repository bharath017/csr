package com.csr.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClusterBean {
	private int cluster_id;
	private String cluster_resource_center_name;
	private String cluster_resource_center_address;
	private String cluster_resource_person_name;
	private String cluster_resource_person_phone;
	private int district_id;
	private int state_id;
	private int taluk_id;
	private int hobli_id;
	private String cluster_status;
}