package com.xuexiang.util.system.bluetoothhelper;

/**
 * Created by wuhaojie on 2016/9/10 20:17.
 */
public interface OnReceiveMessageListener extends IErrorListener, IConnectionLostListener {


    void onNewLine(String s);
}
