package com.travel.hotel.product.entity;

import java.io.Serializable;

@Deprecated
public class DateInfoDTO implements Serializable{
	/**
	 * 当前日期
	 */
	public String dateStr;
	/**
	 * 行坐标
	 */
	public  int row;
	/**
	 * 列坐标
	 */
	public  int col;
	/**
	 * 当前星期(1,2,3,4,5,6,7)
	 */
	public int week;
	
	public String weekStr;
	
	public boolean currentDate=false;
	
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	
	public String getWeekStr() {
		this.weekStr="";
		switch(this.week){
		case 1: this.weekStr = "周一";break;
		case 2: this.weekStr = "周二";break;
		case 3: this.weekStr = "周三";break;
		case 4: this.weekStr = "周四";break;
		case 5: this.weekStr = "周五";break;
		case 6: this.weekStr = "周六";break;
		case 7: this.weekStr = "周日";break;
		}
		return this.weekStr;
	}
	public boolean isCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(boolean currentDate) {
		this.currentDate = currentDate;
	}
	
}
