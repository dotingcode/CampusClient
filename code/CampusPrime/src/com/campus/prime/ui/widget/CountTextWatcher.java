package com.campus.prime.ui.widget;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class CountTextWatcher implements TextWatcher{
	
	private int maxCount = 130;
	private EditText mContentView;
	private TextView mCountView;
	
	private int editStart;
	private int editEnd;
	
	public CountTextWatcher(EditText contentView,TextView countView) {
		// TODO Auto-generated constructor stub
		this.mContentView = contentView;
		this.mCountView = countView;
	}
	
	
	public CountTextWatcher(EditText contentView,TextView countView,int count){
		this(contentView,countView);
		this.maxCount = count;
	}
	
	public void setMaxCount(int max){
		this.maxCount = max;
	}
	
	
	@Override
	public void afterTextChanged(Editable s){
		editStart = mContentView.getSelectionStart();
		editEnd = mContentView.getSelectionEnd();
		
		//��ȥ�������������򣡣������ջ���������
		mContentView.removeTextChangedListener(this);

		//�����ַ���������140ʱ���Ͷ�����ȥ�ˡ����� 
		while(calculateLength(s.toString())>maxCount){
			s.delete(editStart - 1, editEnd);
			editStart--;
			editEnd--;
		}
		mContentView.setSelection(editStart);
		
		//�ټ��ϼ�����
		 mContentView.addTextChangedListener(this);  
		 mCountView.setText(String.valueOf(maxCount - getInputCount()));
	}
	
	

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		
	}
	
	/** 
     * ���������������
     * һ������һ���ַ�
     * ����Ӣ����ĸһ���ַ�
     * һ�����ı��һ���ַ�
     * ����Ӣ�ı��һ���ַ�
     * ��������һ���ַ�
     *  
     */  
	private long calculateLength(CharSequence c) {
		double len = 0;
		for (int i = 0; i < c.length(); i++) {
			int tmp = (int) c.charAt(i);
			if (tmp > 0 && tmp < 127) {
				len += 0.5;
			} else {
				len++;
			}
		}
		return Math.round(len);
	}
	
	 /** 
     * ��ȡ�û���������� 
     *  
     * @return 
     */ 
	private long getInputCount() {
		// TODO Auto-generated method stub
		return calculateLength(mContentView.getText().toString());  
	}

}
