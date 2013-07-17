package cn.luck.bean;

import java.util.Date;

public class DoubleBallBean {

	private int id;
	private int red1;
	private int red2;
	private int red3;
	private int red4;
	private int red5;
	private int red6;
	private int blue;
	private Date publishTime;
	private Date updateTime;
	
	public DoubleBallBean() {
		super();
	}
	
	public DoubleBallBean(int id, int red1, int red2, int red3, int red4,
			int red5, int red6, int blue, Date publishTime) {
		super();
		this.id = id;
		this.red1 = red1;
		this.red2 = red2;
		this.red3 = red3;
		this.red4 = red4;
		this.red5 = red5;
		this.red6 = red6;
		this.blue = blue;
		this.publishTime = publishTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRed1() {
		return red1;
	}
	public void setRed1(int red1) {
		this.red1 = red1;
	}
	public int getRed2() {
		return red2;
	}
	public void setRed2(int red2) {
		this.red2 = red2;
	}
	public int getRed3() {
		return red3;
	}
	public void setRed3(int red3) {
		this.red3 = red3;
	}
	public int getRed4() {
		return red4;
	}
	public void setRed4(int red4) {
		this.red4 = red4;
	}
	public int getRed5() {
		return red5;
	}
	public void setRed5(int red5) {
		this.red5 = red5;
	}
	public int getRed6() {
		return red6;
	}
	public void setRed6(int red6) {
		this.red6 = red6;
	}
	public int getBlue() {
		return blue;
	}
	public void setBlue(int blue) {
		this.blue = blue;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}