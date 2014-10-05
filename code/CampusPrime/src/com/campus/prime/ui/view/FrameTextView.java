package com.campus.prime.ui.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.widget.TextView;
import com.campus.prime.R;

public class FrameTextView extends TextView
{
	
	
	
	private static Boolean isDark = false;
	private static final int [] light_mode = {R.attr.state_light};
	private static final int [] dark_mode = {R.attr.state_dark};
	int paddingTop;
	int paddingBottom;
	int paddingRight;
	int paddingLeft;
	Paint linePaint;
	int lineColor;
	float lineWidth;
	
	public FrameTextView(Context context)
	{
		super(context);
		//TODO Auto-generated constructor stub
	}
	public FrameTextView(Context context,AttributeSet attrs)
	{
		
		//TODO Auto-generated constructor stub
		super(context,attrs);
		TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.LabelLine);
		lineColor = a.getColor(R.styleable.LabelLine_LineColor, Color.BLUE);
		lineWidth = a.getFloat(R.styleable.LabelLine_LineWidth, 2.0f);
		paddingLeft = this.getPaddingLeft();
		this.initPaint();
	}
	public	FrameTextView(Context context, AttributeSet attrs, int defStyle) 
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
		canvas.drawLine(0,0,this.getWidth(), 0, linePaint);
		canvas.drawLine(0,0,0,this.getHeight(),linePaint);
		canvas.drawLine(this.getWidth(), 0, this.getWidth(), this.getHeight(), linePaint);
		canvas.drawLine(0, this.getHeight(), this.getWidth(), this.getHeight(), linePaint);
	}
	
	public void initPaint()
	{
		linePaint = new Paint();
		linePaint.setColor(lineColor);
		linePaint.setStrokeWidth(lineWidth);
	}
	
	

}
