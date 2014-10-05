package com.campus.prime.ui.app;


import java.util.ArrayList;
import java.util.List;

import com.campus.prime.R;
import com.campus.prime.ui.BaseActivity;

import android.os.Bundle;
import android.view.Menu;
public class ScoreActivity extends BaseActivity {

	int currentGrade;
	int currentSemester;
	int currentSorceId;
	int currentPosition;
	public List <Score> data;
	public List <Score> currentData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score_search);	
		this.simulateData();
		getSupportFragmentManager().beginTransaction().replace(R.id.ass_fragment, new ScoreSelectFragment()).commit();	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		showProgress(false);
		return true;
		
	}
	
	/**
	 * Simulate data for scores
	 * @author zhangzhen
	 */
	public void simulateData()
	{
		data = new ArrayList();
		
		// 1.1
		Score score1 = new Score();
		score1.setCourseName("˼����������뷨�ɻ���");
		score1.setCourseTeacther("����ʦ");
		score1.setCourseType("����");
		score1.setCredit(2);
		score1.setFirstScore(78);
		score1.setGpa(2f);
		score1.setSemester(1);
		score1.setGrade(1);
		data.add(score1);
		
		Score score2 = new Score();
		score2.setCourseName("�й�����ʷ��Ҫ");
		score2.setCourseTeacther("����ʦ");
		score2.setCourseType("����");
		score2.setCredit(2);
		score2.setFirstScore(50);
		score2.setGpa(3f);
		score2.setSemester(1);
		score2.setGrade(1);
		data.add(score2);
		
		Score score3 = new Score();
		score3.setCourseName("��ѧӢ��1");
		score3.setCourseTeacther("����ʦ");
		score3.setCourseType("����");
		score3.setCredit(3);
		score3.setFirstScore(82);
		score3.setGpa(2.8f);
		score3.setSemester(1);
		score3.setGrade(1);
		data.add(score3);
		
		Score score4 = new Score();
		score4.setCourseName("�ߵ���ѧ1");
		score4.setCourseTeacther("����ʦ");
		score4.setCourseType("����");
		score4.setCredit(2);
		score4.setFirstScore(66f);
		score4.setGpa(2.4f);
		score4.setSemester(1);
		score4.setGrade(1);
		data.add(score4);
		
		Score score5 = new Score();
		score5.setCourseName("����1");
		score5.setCourseTeacther("����ʦ");
		score5.setCourseType("����");
		score5.setCredit(2);
		score5.setFirstScore(78);
		score5.setGpa(2.2f);
		score5.setSemester(1);
		score5.setGrade(1);
		data.add(score5);
		
		Score score6 = new Score();
		score6.setCourseName("�������������");
		score6.setCourseTeacther("����ʦ");
		score6.setCourseType("����");
		score6.setCredit(2);
		score6.setFirstScore(80);
		score6.setGpa(2);
		score6.setSemester(1);
		score6.setGrade(1);
		data.add(score6);
		
		//1.2
		Score score7 = new Score();
		score7.setCourseName("����������");
		score7.setCourseTeacther("����ʦ");
		score7.setCourseType("����");
		score7.setCredit(1);
		score7.setFirstScore(59);
		score7.setSecondScore(80);
		score7.setGpa(2f);
		score7.setSemester(2);
		score7.setGrade(1);
		data.add(score7);
		
		Score score8 = new Score();
		score8.setCourseName("��ѧӢ��2");
		score8.setCourseTeacther("����ʦ");
		score8.setCourseType("����");
		score8.setCredit(3);
		score8.setFirstScore(80);
		score8.setGpa(3f);
		score8.setSemester(2);
		score8.setGrade(1);
		data.add(score8);
		
		Score score9 = new Score();
		score9.setCourseName("�ߵ���ѧ2");
		score9.setCourseTeacther("����ʦ");
		score9.setCourseType("����");
		score9.setCredit(2);
		score9.setFirstScore(80);
		score9.setGpa(3f);
		score9.setSemester(2);
		score9.setGrade(1);
		data.add(score9);
		
		Score score10 = new Score();
		score10.setCourseName("����2");
		score10.setCourseTeacther("����ʦ");
		score10.setCourseType("����");
		score10.setCredit(2);
		score10.setFirstScore(72);
		score10.setGpa(1.10f);
		score10.setSemester(2);
		score10.setGrade(1);
		data.add(score10);
		
		Score score11 = new Score();
		score11.setCourseName("��ͨ����");
		score11.setCourseTeacther("����ʦ");
		score11.setCourseType("����");
		score11.setCredit(2);
		score11.setFirstScore(76);
		score11.setGpa(2f);
		score11.setSemester(2);
		score11.setGrade(1);
		data.add(score11);
		
		Score score12 = new Score();
		score12.setCourseName("��·����");
		score12.setCourseTeacther("����ʦ");
		score12.setCourseType("����");
		score12.setCredit(2);
		score12.setFirstScore(72);
		score12.setGpa(2f);
		score12.setSemester(2);
		score12.setGrade(1);
		data.add(score12);
		
		Score score13 = new Score();
		score13.setCourseName("C++�������");
		score13.setCourseTeacther("����ʦ");
		score13.setCourseType("����");
		score13.setCredit(2);
		score13.setFirstScore(76);
		score13.setGpa(2f);
		score13.setSemester(2);
		score13.setGrade(1);
		data.add(score13);
		
		//2.1
		Score score14 = new Score();
		score14.setCourseName("ë��˼�����й���ɫ����");
		score14.setCourseTeacther("����ʦ");
		score14.setCourseType("����");
		score14.setCredit(2);
		score14.setFirstScore(81);
		score14.setGpa(2f);
		score14.setSemester(1);
		score14.setGrade(2);
		data.add(score14);
		
		Score score15 = new Score();
		score15.setCourseName("��ѧӢ��3");
		score15.setCourseTeacther("����ʦ");
		score15.setCourseType("����");
		score15.setCredit(3);
		score15.setFirstScore(67);
		score15.setGpa(2f);
		score15.setSemester(1);
		score15.setGrade(2);
		data.add(score15);
		
		Score score16 = new Score();
		score16.setCourseName("���Դ���");
		score16.setCourseTeacther("����ʦ");
		score16.setCourseType("����");
		score16.setCredit(2);
		score16.setFirstScore(67);
		score16.setGpa(2f);
		score16.setSemester(1);
		score16.setGrade(2);
		data.add(score16);
		
		Score score17 = new Score();
		score17.setCourseName("����3");
		score17.setCourseTeacther("����ʦ");
		score17.setCourseType("����");
		score17.setCredit(2);
		score17.setFirstScore(73);
		score17.setGpa(2f);
		score17.setSemester(1);
		score17.setGrade(2);
		data.add(score17);
		
		Score score18 = new Score();
		score18.setCourseName("ģ����Ӽ���");
		score18.setCourseTeacther("����ʦ");
		score18.setCourseType("����");
		score18.setCredit(2);
		score18.setFirstScore(67);
		score18.setGpa(2f);
		score18.setSemester(1);
		score18.setGrade(2);
		data.add(score18);
		
		Score score19 = new Score();
		score19.setCourseName("���ݽṹ");
		score19.setCourseTeacther("����ʦ");
		score19.setCourseType("����");
		score19.setCredit(2);
		score19.setFirstScore(67);
		score19.setGpa(2f);
		score19.setSemester(1);
		score19.setGrade(2);
		data.add(score19);
		
		Score score20 = new Score();
		score20.setCourseName("���ֵ��Ӽ���");
		score20.setCourseTeacther("����ʦ");
		score20.setCourseType("����");
		score20.setCredit(2);
		score20.setFirstScore(67);
		score20.setGpa(2f);
		score20.setSemester(1);
		score20.setGrade(2);
		data.add(score20);
		
		//2.2
		Score score21 = new Score();
		score21.setCourseName("��ѧӢ��4");
		score21.setCourseTeacther("����ʦ");
		score21.setCourseType("����");
		score21.setCredit(2);
		score21.setFirstScore(67);
		score21.setGpa(2f);
		score21.setSemester(2);
		score21.setGrade(2);
		data.add(score21);
		
		Score score22 = new Score();
		score22.setCourseName("������������ͳ��");
		score22.setCourseTeacther("����ʦ");
		score22.setCourseType("����");
		score22.setCredit(2);
		score22.setFirstScore(67);
		score22.setGpa(2f);
		score22.setSemester(2);
		score22.setGrade(2);
		data.add(score22);
		
		Score score23 = new Score();
		score23.setCourseName("����4	");
		score23.setCourseTeacther("����ʦ");
		score23.setCourseType("����");
		score23.setCredit(2);
		score23.setFirstScore(67);
		score23.setGpa(2f);
		score23.setSemester(2);
		score23.setGrade(2);
		data.add(score23);
		
		Score score24 = new Score();
		score24.setCourseName("���ݿ�ԭ��");
		score24.setCourseTeacther("����ʦ");
		score24.setCourseType("����");
		score24.setCredit(2);
		score24.setFirstScore(67);
		score24.setGpa(2f);
		score24.setSemester(2);
		score24.setGrade(2);
		data.add(score24);
		
		Score score25 = new Score();
		score25.setCourseName("��������ԭ��");
		score25.setCourseTeacther("����ʦ");
		score25.setCourseType("����");
		score25.setCredit(2);
		score25.setFirstScore(67);
		score25.setGpa(2f);
		score25.setSemester(2);
		score25.setGrade(2);
		data.add(score25);
		
		Score score26 = new Score();
		score26.setCourseName("����ϵͳ");
		score26.setCourseTeacther("¬��ʦ");
		score26.setCourseType("����");
		score26.setCredit(2);
		score26.setFirstScore(67);
		score26.setGpa(2f);
		score26.setSemester(2);
		score26.setGrade(2);
		data.add(score26);
		
		Score score27 = new Score();
		score27.setCourseName("��ɢ��ѧ");
		score27.setCourseTeacther("����ʦ");
		score27.setCourseType("����");
		score27.setCredit(2);
		score27.setFirstScore(67);
		score27.setGpa(2f);
		score27.setSemester(2);
		score27.setGrade(2);
		data.add(score27);
		
		//3.1
		Score score28 = new Score();
		score28.setCourseName("΢��ԭ��");
		score28.setCourseTeacther("����ʦ");
		score28.setCourseType("����");
		score28.setCredit(2);
		score28.setFirstScore(67);
		score28.setGpa(2f);
		score28.setSemester(1);
		score28.setGrade(3);
		data.add(score28);
		
		Score score29 = new Score();
		score29.setCourseName("������̼�UML��ģ");
		score29.setCourseTeacther("����ʦ");
		score29.setCourseType("����");
		score29.setCredit(2);
		score29.setFirstScore(67);
		score29.setGpa(2f);
		score29.setSemester(1);
		score29.setGrade(3);
		data.add(score29);
			
		Score score30 = new Score();
		score30.setCourseName("Java�������ʵѵ");
		score30.setCourseTeacther("����ʦ");
		score30.setCourseType("����");
		score30.setCredit(2);
		score30.setFirstScore(67);
		score30.setGpa(2f);
		score30.setSemester(1);
		score30.setGrade(3);
		data.add(score30);
		
		//3.2
		Score score31 = new Score();
		score31.setCourseName("΢���ӿڼ���");
		score31.setCourseTeacther("����ʦ");
		score31.setCourseType("����");
		score31.setCredit(2);
		score31.setFirstScore(67);
		score31.setGpa(2f);
		score31.setSemester(2);
		score31.setGrade(3);
		data.add(score31);
		
		Score score32 = new Score();
		score32.setCourseName("����ԭ��");
		score32.setCourseTeacther("����ʦ");
		score32.setCourseType("����");
		score32.setCredit(2);
		score32.setFirstScore(67);
		score32.setGpa(2f);
		score32.setSemester(2);
		score32.setGrade(3);
		data.add(score32);
		
		Score score33 = new Score();
		score33.setCourseName("��Ƭ��ԭ��");
		score33.setCourseTeacther("����ʦ");
		score33.setCourseType("����");
		score33.setCredit(2);
		score33.setFirstScore(67);
		score33.setGpa(2f);
		score33.setSemester(2);
		score33.setGrade(3);
		data.add(score33);
		
		Score score34 = new Score();
		score34.setCourseName("�����ϵͳ�ṹ");
		score34.setCourseTeacther("����ʦ");
		score34.setCourseType("����");
		score34.setCredit(2);
		score34.setFirstScore(67);
		score34.setGpa(2f);
		score34.setSemester(2);
		score34.setGrade(3);
		data.add(score34);
		
		
		
	}

}