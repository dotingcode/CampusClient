package com.campus.prime.ui.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarSimulateData 
{
	private List<CalendarData> list = null;
	public CalendarSimulateData()
	{
		list = this.init();
		this.simulate(list);
	}
	public List<CalendarData> init()
	{
		List<CalendarData> list = new ArrayList<CalendarData>();
		for(int i=0; i<21; i++)
		{
			CalendarData data = new CalendarData();
			data.setId(i);
			list.add(data);
		}
		return list;
	}
	public void simulate(List<CalendarData> list)
	{
		CalendarData week5 = list.get(5);
		week5.setMessage6("�����ڼ��ڵ�һ��");
		week5.setMessage7("�����ڼ��ڵڶ���");
		week5.setMessage1("�����ڼ��ڵ�����");
		
		CalendarData week7 = list.get(7);
		week7.setMessage5("�˶����һ��");
		week7.setMessage6("�˶���ڶ���");
		
		CalendarData week9 = list.get(9);
		week9.setMessage5("�Ͷ��ڼ��ڵ�һ��");
		week9.setMessage6("�Ͷ��ڼ��ڵڶ���");
		week9.setMessage7("�Ͷ��ڼ��ڵ�����");
		week9.setMessage1("��5��2�տγ�");
		
		CalendarData week13 = list.get(13);
		week13.setMessage7("����ڵ�һ��");
		week13.setMessage1("����ڵڶ���");
		CalendarData week14 = list.get(14);
		week14.setMessage1("����ڵ�����");
		
		
		CalendarData week17 = list.get(17);
		week17.setMessage2("��������");
		week17.setMessage3("��������");
		week17.setMessage4("��������");
		week17.setMessage5("��������");
		week17.setMessage6("��������");
		week17.setMessage7("��������");
		week17.setMessage1("��������");
		
		CalendarData week18 = list.get(18);
		week18.setMessage2("��������");
		week18.setMessage3("��������");
		week18.setMessage4("��������");
		week18.setMessage5("��������");
		week18.setMessage6("��������");
		week18.setMessage7("��������");
		week18.setMessage1("��������");
		
		CalendarData week19 = list.get(19);
		week19.setMessage2("��������");
		week19.setMessage3("��������");
		week19.setMessage4("��������");
		week19.setMessage5("��������");
		week19.setMessage6("��������");
		week19.setMessage7("��������");
		week19.setMessage1("��������");
		
		CalendarData week20 = list.get(20);
		week20.setMessage2("��������");
		week20.setMessage3("��������");
		week20.setMessage4("��������");
		week20.setMessage5("��������");
		week20.setMessage6("��������");
		week20.setMessage7("��������");
		week20.setMessage1("��������");
	}
	
	public int getWeek(String year, String month, String day)
	{
		int week=-1;
		Calendar start = Calendar.getInstance();
		start.set(Calendar.YEAR, 2014);
		start.set(Calendar.MONTH, 1);
		start.set(Calendar.DAY_OF_MONTH, 24);
		
		Calendar now = Calendar.getInstance();
		now.set(Calendar.YEAR, Integer.parseInt(year.trim()));
		now.set(Calendar.MONTH, Integer.parseInt(month.trim())-1);
		now.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day.trim()));
		
		if(start.get(Calendar.YEAR)==now.get(Calendar.YEAR))
		{
			if(this.getDayOfWeek(year, month, day)==1)
				week = now.get(Calendar.WEEK_OF_YEAR)-start.get(Calendar.WEEK_OF_YEAR)-1;
			else
				week = now.get(Calendar.WEEK_OF_YEAR)-start.get(Calendar.WEEK_OF_YEAR);
		}
		else
		{
			week = now.get(Calendar.WEEK_OF_YEAR)-start.get(Calendar.WEEK_OF_YEAR);
		}
		if(week>=0 && week <=20)
			return week;
		else
			return -1;
	}
	
	public int getDayOfWeek(String year, String month, String day)
	{
		Calendar now = Calendar.getInstance();
		now.set(Calendar.YEAR, Integer.parseInt(year.trim()));
		now.set(Calendar.MONTH, Integer.parseInt(month.trim())-1);
		now.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day.trim()));
		return now.get(Calendar.DAY_OF_WEEK);
	}
	
	public String getMessage( String year, String month, String day)
	{
		String mess = null;		
		int week = getWeek(year, month, day);
		if(week==-1)
		{
			return "��������";
		}
		else
		{
			mess = ((CalendarData)list.get(week)).getMessage(getDayOfWeek(year, month, day));			
		}
		if(mess==null || mess.equals(""))
		{
			return "��������";
		}
		else
			return mess;
	}
	

}
