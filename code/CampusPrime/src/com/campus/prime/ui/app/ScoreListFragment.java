package com.campus.prime.ui.app;


import java.util.ArrayList;
import java.util.List;

import com.campus.prime.R;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;


public class ScoreListFragment extends Fragment
{
	@Override
	public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState)
	{
		
		View parent = inflater.inflate(R.layout.score_list, null);

		ListView listView = (ListView) parent.findViewById(R.id.fsl_list);
		ScoreActivity mActivity = (ScoreActivity)this.getActivity();
		mActivity.currentData = this.getData(mActivity.data, mActivity.currentGrade	, mActivity.currentSemester);
		ScoreListAdapter adapter = new ScoreListAdapter(this.getActivity(),R.layout.score_item,mActivity.currentData);
		listView.setAdapter(adapter);	
		listView.setOnItemClickListener(new OnItemClickListener(){
			FragmentManager fm = getActivity().getSupportFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				ScoreActivity mActivity = (ScoreActivity) getActivity();
				mActivity.currentPosition=arg2+1;
				ft.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out, R.anim.push_right_in,
						R.anim.push_right_out);
				ft.addToBackStack(null).replace(R.id.ass_fragment, new ScoreDetailFragment()).commit();
			}});
		return parent;		
	}
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public void onPrepareOptionsMenu(Menu menu) 
	{
		// TODO Auto-generated method stub	
		ScoreActivity mActivity =  (ScoreActivity) getActivity();
		ActionBar actionBar = mActivity.getActionBar();	
		actionBar.setDisplayHomeAsUpEnabled(true);
			
		if(mActivity.currentGrade != 5 && mActivity.currentSemester != 3)
		{
			actionBar.setTitle("第"+((mActivity.currentGrade-1)*2+mActivity.currentSemester)+"学期");
		}
		else if(mActivity.currentGrade !=5 && mActivity.currentSemester == 3)
		{
			actionBar.setTitle("第"+mActivity.currentGrade+"学年");
		}
		else if(mActivity.currentGrade == 5 && mActivity.currentSemester == 1)
		{
			actionBar.setTitle("所有第1学期");
		}
		else if (mActivity.currentGrade == 5 && mActivity.currentSemester == 2)
		{
			actionBar.setTitle("所有第2学期");
		}
		else if(mActivity.currentGrade == 5 && mActivity.currentSemester == 3)
		{
			actionBar.setTitle("所有");
		}
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		FragmentManager fm = getActivity().getSupportFragmentManager();
		// TODO Auto-generated method stub
		switch(item.getItemId())
		{	
			case android.R.id.home:
				fm.popBackStack();
		}
		return false;
	}	
	public List<Score> getData(List<Score> data , int grade ,int semester)
	{
		List<Score> scores = new ArrayList<Score>();
		int i=0;
		if(grade == 1 && semester == 1)
		{
			for(i=0;i<data.size();i++)
			{
				if(data.get(i).getGrade() == 1 && data.get(i).getSemester()==1)
					scores.add(data.get(i));
			}
		}
		else if(grade == 1 && semester == 2)
		{
			for(i=0;i<data.size();i++)
			{
				if(data.get(i).getGrade()==1 && data.get(i).getSemester()==2)
					scores.add(data.get(i));
			}
		}
		else if(grade == 1 && semester == 3)
		{
			for(i=0;i<data.size();i++)
			{
				if(data.get(i).getGrade()==1)
					scores.add(data.get(i));
			}
		}
		else if(grade == 2 && semester == 1)
		{
			for(i=0;i<data.size();i++)
			{
				if(data.get(i).getGrade() == 2 && data.get(i).getSemester()==1)
					scores.add(data.get(i));
			}
		}
		else if(grade == 2 && semester == 2)
		{
			for(i=0;i<data.size();i++)
			{
				if(data.get(i).getGrade()==2 && data.get(i).getSemester()==2)
					scores.add(data.get(i));
			}
		}
		else if(grade == 2 && semester == 3)
		{
			for(i=0;i<data.size();i++)
			{
				if(data.get(i).getGrade()==2)
					scores.add(data.get(i));
			}
		}
		else if(grade == 3 && semester == 1)
		{
			for(i=0;i<data.size();i++)
			{
				if(data.get(i).getGrade() == 3 && data.get(i).getSemester()==1)
					scores.add(data.get(i));
			}
		}
		else if(grade == 3 && semester == 2)
		{
			for(i=0;i<data.size();i++)
			{
				if(data.get(i).getGrade()==3 && data.get(i).getSemester()==2)
					scores.add(data.get(i));
			}
		}
		else if(grade == 3 && semester == 3)
		{
			for(i=0;i<data.size();i++)
			{
				if(data.get(i).getGrade()==3)
					scores.add(data.get(i));
			}
		}
		else if(grade == 4 && semester == 1)
		{
			for(i=0;i<data.size();i++)
			{
				if(data.get(i).getGrade() == 4 && data.get(i).getSemester()==1)
					scores.add(data.get(i));
			}
		}
		else if(grade == 4 && semester == 2)
		{
			for(i=0;i<data.size();i++)
			{
				if(data.get(i).getGrade()==4 && data.get(i).getSemester()==2)
					scores.add(data.get(i));
			}
		}
		else if(grade == 4 && semester == 3)
		{
			for(i=0;i<data.size();i++)
			{
				if(data.get(i).getGrade()==4)
					scores.add(data.get(i));
			}
		}
		else if(grade == 5 && semester == 1)
		{
			for(i=0;i<data.size();i++)
			{
				if( data.get(i).getSemester()==1)
					scores.add(data.get(i));
			}
		}
		else if(grade == 5 && semester == 2)
		{
			for(i=0;i<data.size();i++)
			{
				if(data.get(i).getSemester()==2)
					scores.add(data.get(i));
			}
		}
		else if(grade == 5 && semester == 3)
		{
			for(i=0;i<data.size();i++)
			{
				scores = data;
			}
		}
		return scores;
	}

}