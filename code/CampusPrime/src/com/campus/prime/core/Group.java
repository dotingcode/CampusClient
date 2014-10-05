package com.campus.prime.core;

import java.io.Serializable;

import android.graphics.drawable.Drawable;

import com.afollestad.cardsui.Card.CardMenuListener;
import com.afollestad.cardsui.CardBase;
import com.afollestad.cardsui.CardHeader.ActionListener;
import com.campus.prime.R;

public class Group implements Serializable ,CardBase<Group>{
	/**
	 * serialize ID
	 */
	private static final long serialVersionUID = 6444217954371113339L;

	private int id;
	
	private String name;
	
	private String description;
	
	private String avatar;
	
	private String cover;
	
	private UserItem founder;
	
	private String created;
	
	private int total;
	
	

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public UserItem getFounder() {
		return founder;
	}

	public void setFounder(UserItem founder) {
		this.founder = founder;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "GroupProfile [id=" + id + ", name=" + name + ", description="
				+ description + ", avatar=" + avatar + ", founder=" + founder
				+ ", created=" + created + ", total=" + total + "]";
	}

	public GroupItem toGroupItem(){
		GroupItem groupItem = new GroupItem();
		groupItem.setId(this.getId());
		groupItem.setName(this.getName());
		groupItem.setDescription(this.getDescription());
		groupItem.setTotal(this.getTotal());
		return groupItem;
	}
	
	
	@Override
	public Object getSilkId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equalTo(Group other) {
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
	public CardMenuListener<Group> getPopupListener() {
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
