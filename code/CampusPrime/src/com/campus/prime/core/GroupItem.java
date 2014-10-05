package com.campus.prime.core;

import java.io.Serializable;

import android.graphics.drawable.Drawable;

import com.afollestad.cardsui.Card.CardMenuListener;
import com.afollestad.cardsui.CardBase;
import com.afollestad.cardsui.CardHeader.ActionListener;
import com.campus.prime.R;

public class GroupItem implements Serializable,CardBase<GroupItem>{
	/**
	 * serialize ID
	 */
	private static final long serialVersionUID = -4409040253466017999L;

	private int id;
	
	private String name;
	
	private String avatar;
	
	private String description;
	
	private int total;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "GroupItem [id=" + id + ", name=" + name + ", avatar=" + avatar
				+ ", description=" + description + ", total=" + total + "]";
	}

	@Override
	public Object getSilkId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equalTo(GroupItem other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isHeader() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isClickable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getPopupMenu() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ActionListener getActionCallback() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getActionTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CardMenuListener<GroupItem> getPopupListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Drawable getThumbnail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLayout() {
		// TODO Auto-generated method stub
		return R.layout.group_card_item;
	}

	@Override
	public Object getTag() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
}
