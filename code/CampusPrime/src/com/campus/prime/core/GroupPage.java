package com.campus.prime.core;

import java.util.List;

public class GroupPage extends PageBase{
	private List<Group> results;
	
	public void setResults(List<Group> results){
		this.results = results;
	}

	@Override
	public List<Group> getResults() {
		// TODO Auto-generated method stub
		return results;
	}
	@Override
	public String toString() {
		String r = "";
		if(results != null)
			for(Object result:results){
				r += ((GroupItem) result).toString();
			}
		return r; 
	}

}
