package com.campus.prime.ui.view;



import com.campus.prime.R;

import android.R.color;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;

public class LabelEditText extends ThemeEditText
{
	private static Boolean isDark = false;
	private static final int [] light_mode = {R.attr.state_light};
	private static final int [] dark_mode = {R.attr.state_dark};
	String label;
	int paddingLeft;
	Paint linePaint;
	Paint textPaint;
	int labelColor;
	int lineColor;
	float lineWidth;
	int labelWidth;
	
	public LabelEditText(Context context)
	{
		super(context);
		//TODO Auto-generated constructor stub
	}
	public LabelEditText(Context context,AttributeSet attrs)
	{
		
		//TODO Auto-generated constructor stub
		super(context,attrs);
		TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.LabelLine);
		labelColor = a.getColor(R.styleable.LabelLine_LabelColor,Color.BLUE);
		lineColor = a.getColor(R.styleable.LabelLine_LineColor, Color.BLUE);
		lineWidth = a.getFloat(R.styleable.LabelLine_LineWidth, 2.0f);
		if(a.getText(R.styleable.LabelLine_LabelName) == null)
			label = null;
		else
			label = a.getText(R.styleable.LabelLine_LabelName).toString();
		
		paddingLeft = this.getPaddingLeft();
		this.initPaint();
	}
	public LabelEditText(Context context, AttributeSet attrs, int defStyle) 
	{
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	

	public int[] onCreateDrawableState(int extraSpace)
	{
		int[] drawableState = super.onCreateDrawableState(extraSpace + 2);
	    if(isDark == true)
	    	mergeDrawableStates(drawableState,dark_mode);
	    else
	    	mergeDrawableStates(drawableState,light_mode);	    
	    return drawableState;
	}
	
	protected void onDraw(Canvas canvas)
	{	
		super.onDraw(canvas);	
		labelWidth = (int) (this.getTextSize() * label.length() + paddingLeft);

		this.setPadding(labelWidth, this.getPaddingTop(), this.getPaddingRight(), this.getPaddingBottom());
		canvas.drawText(label, paddingLeft, this.getLineHeight() + this.getPaddingTop(), textPaint);

	}
	public void setLabel(String label) 
	{
		// TODO Auto-generated method stub
		this.label=label;
	}
	
	public void initPaint()
	{
		textPaint = new Paint();
		textPaint.setColor(labelColor);
		textPaint.setTextSize(this.getTextSize());	
		linePaint = new Paint();
		linePaint.setColor(lineColor);
		linePaint.setStrokeWidth(lineWidth);
	}
	
}