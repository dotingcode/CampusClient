package com.campus.prime.ui;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.afollestad.cardsui.CardAdapter;
import com.afollestad.cardsui.CardBase;
import com.campus.prime.R;
import com.campus.prime.app.Auth;
import com.campus.prime.core.Message;
import com.campus.prime.core.MessagePage;
import com.campus.prime.core.service.MessageService;
import com.campus.prime.ui.message.MessageDetailActivity;
import com.campus.prime.utils.IntentUtil;

public class MessageCardPageFragment extends PagedCardFragment<Message>{
	
	/**
	 * service for loading data
	 */
	protected static MessageService service = new MessageService();
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setCurrentPage(new MessagePage());
	}
	@SuppressWarnings("unchecked" )
	@Override
	protected List<Message> load() {
		// TODO Auto-generated method stub
		List<Message> result = null;
		currentPage = service.getPublic();
		log.i("load currentPage " + currentPage.getNext());
		if(currentPage != null)
			result = (List<Message>)currentPage.getResults();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<Message> next() {
		if(hasNext()){
			currentPage = service.getNext(currentPage.getNext());
			if(currentPage != null)
				return (List<Message>)currentPage.getResults();
			else
				return null;
		}
		return null;
	}
	@Override
	protected CardAdapter<Message> createCardAdapter(List<Message> items) {
		// TODO Auto-generated method stub
		MessageCardAdapter adapter = new MessageCardAdapter(getActivity());
		adapter.setPopupMenu(R.id.login, null);
		return adapter;
	}

	
	
	
	@Override
	public void onItemClick(int index, CardBase card, View view) {
		// TODO Auto-generated method stub
		super.onItemClick(index, card, view);
		if(Auth.isAuthed()){
			if(items == null)
				log.i("items is null");
			if(items != null)
				IntentUtil.startActivity(this.getActivity(), MessageDetailActivity.class, "message",items.get(index));
		}else{
			Toast.makeText(this.getActivity(), "ÇëÏÈµÇÂ¼", Toast.LENGTH_SHORT).show();
		}
	}
	


}
