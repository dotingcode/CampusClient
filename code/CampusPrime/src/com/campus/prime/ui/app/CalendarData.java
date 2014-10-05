package com.campus.prime.ui.app;

public class CalendarData {
	private int id;
	private String message0;
	private String message1;
	private String message2;
	private String message3;
	private String message4;
	private String message5;
	private String message6;
	private String message7;
	public int getId() {
		return id;
	}
	public String getMessage(int id)
	{
		String mess=null;
		switch(id)
		{
			case 0: mess=message0; break;
			case 1: mess=message1; break;
			case 2: mess=message2; break;
			case 3: mess=message3; break;
			case 4: mess=message4; break;
			case 5: mess=message5; break;
			case 6: mess=message6; break;
			case 7: mess=message7; break;
		}
		return mess;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setMessage0(String message0) {
		this.message0 = message0;
	}
	public void setMessage1(String message1) {
		this.message1 = message1;
	}
	public void setMessage2(String message2) {
		this.message2 = message2;
	}
	public void setMessage3(String message3) {
		this.message3 = message3;
	}
	public void setMessage4(String message4) {
		this.message4 = message4;
	}
	public void setMessage5(String message5) {
		this.message5 = message5;
	}
	public void setMessage6(String message6) {
		this.message6 = message6;
	}
	public void setMessage7(String message7) {
		this.message7 = message7;
	}
	
	
	
}
