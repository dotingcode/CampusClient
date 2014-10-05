package com.campus.prime.ui.widget;

import com.campus.prime.utils.ViewUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.EditText;

public class MentionEditText extends EditText{
	
	private String mMentionText = "";
	
	public void setMentionText(String text){
		this.mMentionText = text;
		invalidate();
	}
	
	public String getMentionText(){
		return this.mMentionText;
	}
	
	public MentionEditText(Context context){
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MentionEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MentionEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Paint paint = new Paint();
		paint.setTextSize(20);
		paint.setColor(0x969696); 
		canvas.drawText(mMentionText,14, getHeight() / 2 + 5, paint);
		int w = ViewUtils.getTextWidth(paint, mMentionText);
		/**
		Rect rect = new Rect();
		paint.getTextBounds(mMentionText, 0, mMentionText.length(), rect);
		int w = rect.width();
		**/
		setPadding(w + 5, 0, 0, 0);
		super.onDraw(canvas);
	}
	
	
	
}
