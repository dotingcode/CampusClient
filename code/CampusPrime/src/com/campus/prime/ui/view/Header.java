package com.campus.prime.ui.view;



import com.campus.prime.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class Header extends TextView
{
	int labelColor;
	int lineColor;
	float lineWidth;
	
	
	Paint paint = new Paint();
	
	
	
	
	public Header(Context context)
	{
		super(context);
		// TODO Auto-generated constructor stub
	}

	public Header(Context context,AttributeSet attrs)
	{
		super(context,attrs);
		// TODO Auto - generated constructor stub
		TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.LabelLine);
		labelColor = a.getColor(R.styleable.LabelLine_LabelColor,Color.BLUE);
		lineColor = a.getColor(R.styleable.LabelLine_LineColor, Color.BLUE);
		lineWidth = a.getFloat(R.styleable.LabelLine_LineWidth, 1.0f);
		
		
	}
	public Header(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		
		super.setTextColor(labelColor);
		paint.setColor(lineColor);
		
		canvas.drawRect(0.0f, getHeight()-lineWidth, getWidth(), getHeight(), paint);
	}

}
