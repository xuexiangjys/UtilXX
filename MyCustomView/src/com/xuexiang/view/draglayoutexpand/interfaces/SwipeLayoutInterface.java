package com.xuexiang.view.draglayoutexpand.interfaces;

import com.xuexiang.view.draglayoutexpand.view.SwipeLayout;

public interface SwipeLayoutInterface {

	SwipeLayout.Status getCurrentStatus();
	
	void close();
	
	void open();
}
