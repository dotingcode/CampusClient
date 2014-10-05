package com.campus.prime.ui.view;



import com.campus.prime.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class DialogHeader extends TextView
{
	float lineWidth;
	int lineA, lineR, lineG, lineB;
	
	Paint paint = new Paint();
	
	
	
	
	public DialogHeader(Context context)
	{
		super(context);
		// TODO Auto-generated constructor stub
	}

	public DialogHeader(Context context,AttributeSet attrs)
	{
		super(context,attrs);
		// TODO Auto - generated constructor stub
	}
	public DialogHeader(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		paint.setARGB(lineA, lineR, lineG, lineB);
		canvas.drawRect(0.0f, getHeight()-lineWidth, getWidth(), getHeight(), paint);
	}
	
	public void setLineColor( int lineA ,int lineR, int lineG , int lineB)
	{
		this.lineA = lineA;
		this.lineR = lineR;
		this.lineG = lineG;
		this.lineB = lineB;
	}
	public void setLineWidth( float lineWidth)
	{
		this.lineWidth=lineWidth;
	}
}
