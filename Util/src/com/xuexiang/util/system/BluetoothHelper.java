package com.xuexiang.util.system;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.xuexiang.util.log.LogHelper;
import com.xuexiang.util.system.bluetoothhelper.OnSearchDeviceListener;

/** 
 * 
 * @author xx
 * @Date 2016-12-21 下午10:27:02 
 */
public class BluetoothHelper {

	private Context mContext;
	private static volatile BluetoothHelper sBluetoothHelper;
	private volatile STATUS mCurrStatus = STATUS.FREE;
	private volatile BlueToothReceiver mReceiver = new BlueToothReceiver();
    private enum STATUS {
        DISCOVERING,
        CONNECTED,
        FREE
    }

    private BluetoothAdapter mBluetoothAdapter;
    private List<BluetoothDevice> mBondedList = new ArrayList<BluetoothDevice>();
    private List<BluetoothDevice> mNewList = new ArrayList<BluetoothDevice>();

    private OnBluetoothDeviceListener mOnBluetoothDeviceListener;
    
    public static BluetoothHelper getInstance(Context context) {
        if (sBluetoothHelper == null) {
            synchronized (BluetoothHelper.class) {
                if (sBluetoothHelper == null)
                	sBluetoothHelper = new BluetoothHelper(context);
            }
        }
        return sBluetoothHelper;
    }

    /**
     * private constructor for singleton
     *
     * @param context context
     */
    private BluetoothHelper(Context context) {
        mContext = context.getApplicationContext();
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }
    
    /**
     * 蓝牙广播接收器
     * @author xx
     *
     */
    private class BlueToothReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (mOnBluetoothDeviceListener != null) {
                	 mOnBluetoothDeviceListener.onNewDeviceFound(device);
                }
                if (device.getBondState() == BluetoothDevice.BOND_NONE) {
                    mNewList.add(device);
                } else if (device.getBondState() == BluetoothDevice.BOND_BONDED) {
                    mBondedList.add(device);
                }

            } else if (action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)) {
				BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				if (mOnBluetoothDeviceListener != null) {
               	 	mOnBluetoothDeviceListener.onBondStateChanged(device);
                } 
			} else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                if (mOnBluetoothDeviceListener != null) {
                	mOnBluetoothDeviceListener.onSearchCompleted(mBondedList, mNewList);
                }
            }
        }
    }
    
    /**
	 * 注册蓝牙广播接收器
	 */
	private void registerBT(Context context) {
		IntentFilter intentFilter = new IntentFilter();     
		intentFilter.addAction(BluetoothDevice.ACTION_FOUND);     
		intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);     
		intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		context.registerReceiver(mReceiver, intentFilter);  

	}
    
    /**
	 * 开启蓝牙
	 */
	public boolean openBluetooth() {
        boolean result = false;
        if (mBluetoothAdapter != null) {
            if (mBluetoothAdapter.isEnabled()) {
            	return true;
            } else {
            	mBluetoothAdapter.enable();
            	try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            	if (mBluetoothAdapter.isEnabled()) {
                    result = true;
                }
            }
        } 
        return result;
    }
    
    /**
	 * 检验蓝牙地址的有效性
	 * @param address 蓝牙地址
	 * @return true:有效，false:无效
	 */
	public boolean checkBluetoothAddress(String address) {
		 Pattern p = Pattern.compile("[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}");  
         Matcher m = p.matcher(address);
         return m.matches();
	}
	
	/**
	 * 弹出蓝牙连接的方法
	 * @param btClass
	 * @param btDevice
	 * @return
	 * @throws Exception
	 */
	public boolean createBind(BluetoothDevice btDevice) throws Exception {
		Method createBondMethod = btDevice.getClass().getMethod("createBond");
		Boolean returnValue = (Boolean) createBondMethod.invoke(btDevice);
		return returnValue.booleanValue();
	}
	
	/**
	 * 蓝牙设备监听器
	 * @author xx
	 *
	 */
	public interface OnBluetoothDeviceListener extends OnSearchDeviceListener {
		/**
		 * @param bondState 绑定状态
		 */
		void onBondStateChanged(BluetoothDevice device);
	}
}
