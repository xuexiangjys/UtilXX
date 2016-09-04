package com.example.testutil.view.swipelistview.entity;

public class RecentItem implements Comparable<RecentItem> {
	private int headImg;// 头像
	private String name;// 消息来自
	private int newNum;// 新消息数目
	
	public RecentItem() {
	}

	public RecentItem(String name, int headImg,int newNum) {
		super();
		this.headImg = headImg;
		this.name = name;
		this.newNum = newNum;
	}

	public int getHeadImg() {
		return headImg;
	}

	public void setHeadImg(int headImg) {
		this.headImg = headImg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNewNum() {
		return newNum;
	}

	public void setNewNum(int newNum) {
		this.newNum = newNum;
	}

//	public static int[] getHeads() {
//		return heads;
//	}

	@Override
	public int compareTo(RecentItem another) {
		// TODO Auto-generated method stub
		return (int) (another.newNum - this.newNum);
	}

}
