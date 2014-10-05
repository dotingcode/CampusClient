package com.campus.prime.core.service;

import static com.campus.prime.constant.AppConstant.TAG;
import com.campus.prime.core.client.CampusClient;
import com.campus.prime.core.client.CampusRequest;
import com.campus.prime.utils.CommonLog;
import com.campus.prime.utils.LogFactory;

public class CampusService {
	
	protected static CommonLog log = LogFactory.createLog(TAG);
	
	private CampusClient client;
	
	
	public CampusService() {
		// TODO Auto-generated constructor stub
		this(new CampusClient());
	}
	public CampusService(CampusClient client) {
		// TODO Auto-generated constructor stub
		this.client = client;
	}

	
	public CampusClient getClient(){
		//this.client = client.setCredential(token);
		return this.client;
	}
	
	
	public CampusRequest createRequest(){
		return new CampusRequest();
	}
	
	
}
