package com.campus.prime.ui.app.course;

import java.util.ArrayList;
import java.util.List;

import com.campus.prime.core.Course;

public class Constant {
	
	public static List<List<Course>> sheet = new ArrayList<List<Course>>();
	
	public static List<Course> day1 = new ArrayList<Course>();
	public static List<Course> day2 = new ArrayList<Course>();
	public static List<Course> day3 = new ArrayList<Course>();
	public static List<Course> day4 = new ArrayList<Course>();
	public static List<Course> day5 = new ArrayList<Course>();
	public static List<Course> day6 = new ArrayList<Course>();
	public static List<Course> day7 = new ArrayList<Course>();
	
	
	
	static{
		sheet.add(day1);
		sheet.add(day2);
		sheet.add(day3);
		sheet.add(day4);
		sheet.add(day5);
		sheet.add(day6);
		sheet.add(day7);
		
		
		
		Course course01 = new Course();
		course01.setId(1);
	    course01.setCourseName("C++�����̳�");
	    course01.setCourseTeacher("����ʦ");
	    course01.setStartWeek("1");
	    course01.setEndWeek("17");
	    course01.setCourseClassroom("a101");
	    course01.setSingleDoubleWeek(2);
	    course01.setDayOfWeek(1);
	    course01.setStartClassNum(1);
	    course01.setEndClassNum(2);
	    course01.setCoursePlace("������");
		
	    Course course02 = new Course();
	    course02.setId(2);
	    course02.setCourseName("�ߵ���ѧ");
	    course02.setCourseTeacher("����ʦ");
	    course02.setStartWeek("1");
	    course02.setEndWeek("17");
	    course02.setCourseClassroom("b302");
	    course02.setSingleDoubleWeek(2);
	    course02.setDayOfWeek(1);
	    course02.setStartClassNum(3);
	    course02.setEndClassNum(5);
	    course02.setCoursePlace("������");
	    
	    Course course03 = new Course();
	    course03.setId(3);
	    course03.setCourseName("���ݽṹ");
	    course03.setCourseTeacher("����ʦ");
	    course03.setStartWeek("1");
	    course03.setEndWeek("17");
	    course03.setCourseClassroom("c505");
	    course03.setSingleDoubleWeek(2);
	    course03.setDayOfWeek(1);
	    course03.setStartClassNum(6);
	    course03.setEndClassNum(7);
	    course03.setCoursePlace("������");
	    
	    Course course04 = new Course();
	    course04.setId(3);
	    course04.setCourseName("���������ϵͳ");
	    course04.setCourseTeacher("¬��ʦ");
	    course04.setStartWeek("1");
	    course04.setEndWeek("17");
	    course04.setCourseClassroom("a215");
	    course04.setSingleDoubleWeek(2);
	    course04.setDayOfWeek(1);
	    course04.setStartClassNum(8);
	    course04.setEndClassNum(10);
	    course04.setCoursePlace("������");
	    
	   
	    Course course05 = new Course();
	    course05.setId(5);
	    course05.setCourseName("�й���ͳ����");
	    course05.setCourseTeacher("����ʦ");
	    course05.setStartWeek("1");
	    course05.setEndWeek("17");
	    course05.setCourseClassroom("a403");
	    course05.setSingleDoubleWeek(2);
	    course05.setDayOfWeek(1);
	    course05.setStartClassNum(11);
	    course05.setEndClassNum(12);
	    course05.setCoursePlace("������");
	    
	    day1.add(course01);
	    day1.add(course02);
	    day1.add(course03);
	    day1.add(course04);
	    day1.add(course05);
	    
	    
	    
		Course course11 = new Course();
		course11.setId(6);
		course11.setCourseName("��ѧӢ��");
		course11.setCourseTeacher("����ʦ");
		course11.setStartWeek("1");
		course11.setEndWeek("17");
		course11.setCourseClassroom("b202");
		course11.setSingleDoubleWeek(2);
		course11.setDayOfWeek(2);
		course11.setStartClassNum(1);
		course11.setCoursePlace("������");
		course11.setEndClassNum(2);
	    
		
	    Course course12 = new Course();
	    course12.setId(7);
		course12.setCourseName("C++ϵͳ����ʵ��");
		course12.setCourseTeacher("����ʦ");
		course12.setStartWeek("1");
		course12.setEndWeek("17");
		course12.setCourseClassroom("��һ����");
		course12.setSingleDoubleWeek(2);
		course12.setDayOfWeek(2);
		course12.setStartClassNum(3);
		course12.setEndClassNum(4);
		course12.setCoursePlace("��ʵ��¥");
		
		 Course course13 = new Course();
		    course13.setId(7);
			course13.setCourseName("��ѧ����");
			course13.setCourseTeacher("����ʦ");
			course13.setStartWeek("1");
			course13.setEndWeek("17");
			course13.setCourseClassroom("c104");
			course13.setSingleDoubleWeek(2);
			course13.setDayOfWeek(2);
			course13.setStartClassNum(5);
			course13.setEndClassNum(6);
			course13.setCoursePlace("������");
		
	    Course course14 = new Course();
	    course14.setId(8);
		course14.setCourseName("���ݿ�ԭ��");
		course14.setCourseTeacher("����ʦ");
		course14.setStartWeek("1");
		course14.setEndWeek("17");
		course14.setCourseClassroom("a202");
		course14.setSingleDoubleWeek(2);
		course14.setDayOfWeek(2);
		course14.setStartClassNum(8);
		course14.setEndClassNum(10);
		course14.setCoursePlace("������");
		
		
		Course course15 = new Course();
	    course15.setId(8);
		course15.setCourseName("��ѧ����");
		course15.setCourseTeacher("����ʦ");
		course15.setStartWeek("1");
		course15.setEndWeek("17");
		course15.setCourseClassroom("b301");
		course15.setSingleDoubleWeek(2);
		course15.setDayOfWeek(2);
		course15.setStartClassNum(8);
		course15.setEndClassNum(10);
		course15.setCoursePlace("������");
	   
		day2.add(course11);
		day2.add(course12);
		day2.add(course13);
		day2.add(course14);
		day2.add(course15);
		
		
		Course course21 = new Course();
		course21.setId(9);
		course21.setCourseName("�����ͼ��ѧ");
		course21.setCourseTeacher("����ʦ");
		course21.setStartWeek("1");
		course21.setEndWeek("17");
		course21.setCourseClassroom("a403");
		course21.setSingleDoubleWeek(2);
		course21.setDayOfWeek(3);
		course21.setStartClassNum(1);
		course21.setEndClassNum(2);
		course21.setCoursePlace("������");
		
		Course course22 = new Course();
		course22.setId(10);
		course22.setCourseName("�罻����");
		course22.setCourseTeacher("����ʦ");
		course22.setStartWeek("1");
		course22.setEndWeek("17");
		course22.setCourseClassroom("a132");
		course22.setSingleDoubleWeek(2);
		course22.setDayOfWeek(3);
		course22.setStartClassNum(3);
		course22.setEndClassNum(4);
		course22.setCoursePlace("������");
		
	    Course course23 = new Course();
	    course23.setId(11);
		course23.setCourseName("C++");
		course23.setCourseTeacher("����ʦ");
		course23.setStartWeek("1");
		course23.setEndWeek("17");
		course23.setCourseClassroom("c222");
		course23.setSingleDoubleWeek(2);
		course23.setDayOfWeek(3);
		course23.setStartClassNum(6);
		course23.setEndClassNum(7);
		course23.setCoursePlace("������");
		
	    Course course24 = new Course();
	    course24.setId(12);
		course24.setCourseName("�й�����ѧ");
		course24.setCourseTeacher("����ʦ");
		course24.setStartWeek("1");
		course24.setEndWeek("17");
		course24.setCourseClassroom("b333");
		course24.setSingleDoubleWeek(2);
		course24.setDayOfWeek(3);
		course24.setStartClassNum(8);
		course24.setEndClassNum(10);
		course24.setCoursePlace("������");
		
	    Course course25 = new Course();
	    course25.setId(13);
		course25.setCourseName("�н���ʷ��Ҫ");
		course25.setCourseTeacher("����ʦ");
		course25.setStartWeek("1");
		course25.setEndWeek("17");
		course25.setCourseClassroom("b222");
		course25.setSingleDoubleWeek(2);
		course25.setDayOfWeek(3);
		course25.setStartClassNum(11);
		course25.setEndClassNum(12);
		course25.setCoursePlace("������");
	   
		day3.add(course21);
		day3.add(course22);
		day3.add(course23);
		day3.add(course24);
		day3.add(course25);
	    
		
		 Course course31 = new Course();
		    course31.setId(14);
		    course31.setCourseName("���������");
		    course31.setCourseTeacher("κ��ʦ");
		    course31.setStartWeek("1");
			course31.setEndWeek("17");
			course31.setCourseClassroom("b222");
			course31.setSingleDoubleWeek(2);
		    course31.setDayOfWeek(4);
		    course31.setStartClassNum(1);
		    course31.setEndClassNum(2);
		    course31.setCoursePlace("������");
		
		
		
		
	    Course course32 = new Course();
	    course32.setId(14);
	    course32.setCourseName("�ֲ�ʽ����");
	    course32.setCourseTeacher("����ʦ");
	    course32.setStartWeek("1");
		course32.setEndWeek("17");
		course32.setCourseClassroom("c444");
		course32.setSingleDoubleWeek(2);
	    course32.setDayOfWeek(4);
	    course32.setStartClassNum(3);
	    course32.setEndClassNum(4);
	    course32.setCoursePlace("������");
	    
	    Course course33 = new Course();
	    course33.setId(15);
	    course33.setCourseName("ƽ�����");
	    course33.setCourseTeacher("����ʦ");
	    course33.setStartWeek("1");
		course33.setEndWeek("17");
		course33.setCourseClassroom("a432");
		course33.setSingleDoubleWeek(2);
	    course33.setDayOfWeek(4);
	    course33.setStartClassNum(8);
	    course33.setEndClassNum(10);
	    course33.setCoursePlace("������");
	    
	    Course course34 = new Course();
	    course34.setId(15);
	    course34.setCourseName("��ѧӢ��");
	    course34.setCourseTeacher("����ʦ");
	    course34.setStartWeek("1");
		course34.setEndWeek("17");
		course34.setCourseClassroom("c222");
		course34.setSingleDoubleWeek(2);
	    course34.setDayOfWeek(4);
	    course34.setStartClassNum(8);
	    course34.setEndClassNum(10);
	    course34.setCoursePlace("������");
	    
	    
	    Course course35 = new Course();
	    course35.setId(15);
	    course35.setCourseName("�������");
	    course35.setCourseTeacher("����ʦ");
	    course35.setStartWeek("1");
		course35.setEndWeek("17");
		course35.setCourseClassroom("c112");
		course35.setSingleDoubleWeek(2);
	    course35.setDayOfWeek(4);
	    course35.setStartClassNum(11);
	    course35.setEndClassNum(12);
	    course35.setCoursePlace("������");
		
		
	    
	    
	    day4.add(course31);
	    day4.add(course32);
	    day4.add(course33);
	    day4.add(course34);
		day4.add(course35);
	    
		
		Course course41 = new Course();
		course41.setId(16);
		course41.setCourseName("���ݽṹ");
	    course41.setCourseTeacher("����ʦ");
	    course41.setStartWeek("1");
		course41.setEndWeek("17");
		course41.setCourseClassroom("c222");
		course41.setSingleDoubleWeek(2);
	    course41.setDayOfWeek(5);
	    course41.setStartClassNum(1);
	    course41.setEndClassNum(2);
	    course41.setCoursePlace("������");
	    
	    
		Course course42 = new Course();
		course42.setId(17);
		course42.setCourseName("������������ͳ��");
	    course42.setCourseTeacher("κ��ʦ");
	    course42.setStartWeek("1");
		course42.setEndWeek("17");
		course42.setCourseClassroom("a121");
		course42.setSingleDoubleWeek(2);
	    course42.setDayOfWeek(5);
	    course42.setStartClassNum(3);
	    course42.setEndClassNum(5);
	    course42.setCoursePlace("������");
	    
	    Course course43 = new Course();
	    course43.setId(18);
	    course43.setCourseName("ë��˼��");
	    course43.setCourseTeacher("����ʦ");
	    course43.setStartWeek("1");
		course43.setEndWeek("17");
		course43.setCourseClassroom("c213");
		course43.setSingleDoubleWeek(2);
	    course43.setDayOfWeek(5);
	    course43.setStartClassNum(6);
	    course43.setEndClassNum(7);
	    course43.setCoursePlace("������");
	    
	    
	    Course course44 = new Course();
	    course44.setId(18);
	    course44.setCourseName("��ѧ������������");
	    course44.setCourseTeacher("����ʦ");
	    course44.setStartWeek("1");
		course44.setEndWeek("17");
		course44.setCourseClassroom("a213");
		course44.setSingleDoubleWeek(2);
	    course44.setDayOfWeek(5);
	    course44.setStartClassNum(8);
	    course44.setEndClassNum(10);
	    course44.setCoursePlace("������");
	    
	    Course course45 = new Course();
	    course45.setId(18);
	    course45.setCourseName("��ҵ");
	    course45.setCourseTeacher("κ");
	    course45.setStartWeek("1");
		course45.setEndWeek("17");
		course45.setCoursePlace("������");
		course45.setSingleDoubleWeek(2);
	    course45.setDayOfWeek(5);
	    course45.setStartClassNum(11);
	    course45.setEndClassNum(12);
	    course45.setCourseClassroom("a333");
	   
		day5.add(course41);
		day5.add(course42);
		day5.add(course43);
		day5.add(course44);
		day5.add(course45);
		
		Course course51 = new Course();
		course51.setStartClassNum(0);
	    course51.setEndClassNum(2);
	    
		Course course52 = new Course();
		course52.setStartClassNum(0);
	    course52.setEndClassNum(5);
	    
	    Course course53 = new Course();
	    course53.setStartClassNum(0);
	    course53.setEndClassNum(7);
	    
	    Course course54 = new Course();
	    course54.setStartClassNum(0);
	    course54.setEndClassNum(10);
	    
	    Course course55 = new Course();
	    course55.setStartClassNum(0);
	    course55.setEndClassNum(12);
	   
		day6.add(course51);
		day6.add(course52);
		day6.add(course53);
		day6.add(course54);
		day6.add(course55);
		
		
		Course course61 = new Course();
		course61.setStartClassNum(0);
		course61.setEndClassNum(2);
		
		Course course62 = new Course();
		course62.setStartClassNum(0);
		course62.setEndClassNum(5);
		
		Course course63 = new Course();
		course63.setStartClassNum(0);
		course63.setEndClassNum(7);
		
		Course course64 = new Course();
		course64.setStartClassNum(0);
		course64.setEndClassNum(10);
		
		Course course65 = new Course();
		course65.setStartClassNum(0);
		course65.setEndClassNum(12);
		
		
		day7.add(course61);
		day7.add(course62);
		day7.add(course63);
		day7.add(course64);
		day7.add(course65);
		
		
		
		
	}
	
	
}
