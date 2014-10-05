package com.campus.prime.core;

import java.util.List;

public class CommentPage extends PageBase{
	private List<Comment> results;
	
	public void setResults(List<Comment> results){
		this.results = results;
	}

	@Override
	public List<Comment> getResults() {
		// TODO Auto-generated method stub
		return results;
	}
	@Override
	public String toString() {
		String r = "";
		for(Object result:results){
			r += ((Comment) result).toString();
			
		}
		return r; 
	}

}
