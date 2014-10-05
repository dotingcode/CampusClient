package com.campus.prime.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.campus.prime.R;
import com.campus.prime.utils.SharedPreferenceUtil;

public class MenuGroupAdapter extends BaseExpandableListAdapter
{
	private Context context = null;

	private String[] groups = {"首页","我的社团","通知","应用","设置"};
	private int [] groupImages = {R.drawable.logo,R.drawable.browse,R.drawable.message,R.drawable.drawer_app,R.drawable.setting};
	private String[][] children = {{},{},{},{"校历","课程表","成绩查询"},{}};
	private int[][] childImages = {{},{},{},{R.drawable.drawer_calendar,R.drawable.drawer_course,R.drawable.drawer_score},{}};
	View groupView;	
	TextView groupName;
	ImageView groupIcon;
	TextView groupText;
	TextView childText;
	ImageView childIcon;
	public MenuGroupAdapter (Context context)
	{
		this.context = context;
	}
	
	
	@Override
	public Object getChild(int arg0, int arg1) {
		// TODO Auto-generated method stub
		if( children[arg0] == null )
			return null;
		else
			return children[arg0][arg1];
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		 if (convertView == null) {  
             LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
             convertView = inflater.inflate(R.layout.drawer_menu_item, null);  
         }  
         childIcon = (ImageView) convertView.findViewById(R.id.ami_icon);
         childIcon.setBackgroundResource(childImages[groupPosition][childPosition]);
         childText = (TextView) convertView.findViewById(R.id.ai_application_name);  
         childText.setText(getChild(groupPosition,childPosition).toString());  
         return convertView; 
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		if(children[groupPosition] == null)
			return 0;
		else
			return children[groupPosition].length;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return groups[groupPosition];
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return groups.length;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView == null)
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
            convertView = inflater.inflate(R.layout.drawer_menu_group, null);  
		}
		groupIcon = (ImageView) convertView.findViewById(R.id.ag_icon);
		groupIcon.setBackgroundResource(groupImages[groupPosition]);
		groupName = (TextView) convertView.findViewById(R.id.ag_group_name);
		groupName.setText(getGroup(groupPosition).toString());
		groupText= (TextView) convertView.findViewById(R.id.ag_group_text);
		if(groupPosition == 2){		
			SharedPreferenceUtil util = SharedPreferenceUtil.getInstance();
			if(util.getNotificationCount() > 0){
				groupText.setVisibility(View.VISIBLE);
				groupText.setText(util.getNotificationCount() + "");
			}else{
				groupText.setVisibility(View.INVISIBLE);
			}
		}
		else{
			groupText.setVisibility(TextView.INVISIBLE);
		}
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

}
