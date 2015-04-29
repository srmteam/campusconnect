package com.kripanshuvivek.campusconn;

import android.graphics.drawable.Drawable;

public class ClubRowItem {

	 private String member_name;
	 private Drawable profile_pic;
	 private String status;

	 public ClubRowItem(String member_name, Drawable profile_pic_id, String status) {
	  this.member_name = member_name;
	  this.profile_pic = profile_pic_id;
	  this.status = status;
	 }

	 public String getMember_name() {
	  return member_name;
	 }

	 public void setMember_name(String member_name) {
	  this.member_name = member_name;
	 }

	 public Drawable getProfile_pic() {
	  return profile_pic;
	 }

	 public void setProfile_pic_id(Drawable profile_pic) {
	  this.profile_pic = profile_pic;
	 }

	 public String getStatus() {
	  return status;
	 }

	 public void setStatus(String status) {
	  this.status = status;
	 }
}
