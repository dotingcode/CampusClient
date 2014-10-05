package com.campus.prime.ui.view;



import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.AttributeSet;
import android.widget.TextView;
import com.campus.prime.R;

public class LabelTextView extends TextView
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
	
	public LabelTextView(Context context)
	{
		super(context);
		//TODO Auto-generated constructor stub
	}
	public LabelTextView(Context context,AttributeSet attrs)
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
		labelWidth = (int) (6 * (getTextSize()) + paddingLeft);
		this.initPaint();
	}
	public LabelTextView(Context context, AttributeSet attrs, int defStyle) 
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
		this.setPadding(labelWidth, this.getPaddingTop(), this.getPaddingRight(), this.getPaddingBottom());
		if(label == null)
			canvas.drawText("NoLabel",paddingLeft, this.getTextSize()+this.getPaddingTop(), textPaint);
		else if(label.length() == 2)
			{
				char charLabel[] = label.toCharArray();
				canvas.drawText(charLabel[0]+"        "+charLabel[1]+':', paddingLeft, this.getTextSize()+this.getPaddingTop() , textPaint);
			}
		else if(label.length() == 4)
			canvas.drawText(label+':', paddingLeft, this.getTextSize()+this.getPaddingTop() , textPaint);
		else
			canvas.drawText("error", paddingLeft, this.getTextSize()+this.getPaddingTop(), textPaint);
		canvas.drawLine(paddingLeft, this.getHeight(), this.getWidth(), this.getHeight(),linePaint);	
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