package com.campus.prime.core;

import java.io.Serializable;

import android.graphics.drawable.Drawable;

import com.afollestad.cardsui.Card.CardMenuListener;
import com.afollestad.cardsui.CardBase;
import com.afollestad.cardsui.CardHeader.ActionListener;
import com.campus.prime.R;
import com.google.gson.annotations.SerializedName;

public class UserItem  implements Serializable,CardBase<UserItem>{
	/**
	 * serialize ID
	 */
	private static final long serialVersionUID = -1143182077705439821L;

	private int id;
	
	@SerializedName("nick_name")
	private String nickName;
	
	private String avatar;
	
	@SerializedName("userType")
	private char userType;
	
	@SerializedName("push_id")
	private String pushId;
	
	

	public String getPushId() {
		return pushId;
	}

	public void setPushId(String pushId) {
		this.pushId = pushId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public char getUserType() {
		return userType;
	}

	public void setUserType(char userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "UserProfileItem [id=" + id + ", nickName=" + nickName
				+ ", avatar=" + avatar + ", userType=" + userType + "]";
	}

	@Override
	public Object getSilkId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equalTo(UserItem other) {
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
	public CardMenuListener<UserItem> getPopupListener() {
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
		return R.layout.user_item;
	}

	@Override
	public Object getTag() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
