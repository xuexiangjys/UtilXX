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

import com.xuexiang.util.app.ActivityUtil;
import com.xuexiang.util.app.ThreadPoolManager;
import com.xuexiang.util.system.bluetoothhelper.OnSearchDeviceListener;

/** 
 * 蓝牙连接辅助类
 * @author xx
 * @Date 2016-12-21 下午10:27:02 
 */
public class BluetoothHelper {
	private Context mContext;
	private static final String DEVICE_HAS_NOT_BLUETOOTH_MODULE = "device has not bluetooth module!";
	private static volatile BluetoothHelper sBluetoothHelper;
	private volatile BlueToothReceiver mReceiver = new BlueToothReceiver();

    private BluetoothAdapter mBluetoothAdapter;
    private List<BluetoothDevice> mBondedList = new ArrayList<BluetoothDevice>();
    private List<BluetoothDevice> mNewList = new ArrayList<BluetoothDevice>();
    private boolean mNeed2unRegister = false;
    private OnBluetoothDeviceListener mListener;
    
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
                if (mListener != null) {
                	 mListener.onNewDeviceFound(device);
                }
                if (device.getBondState() == BluetoothDevice.BOND_NONE) {
                    mNewList.add(device);
                } else if (device.getBondState() == BluetoothDevice.BOND_BONDED) {
                    mBondedList.add(device);
                }

            } else if (action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)) {
				BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				if (mListener != null) {
               	 	mListener.onBondStateChanged(device, intent);
                } 
			} else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                if (mListener != null) {
                	mListener.onSearchCompleted(mBondedList, mNewList);
                }
            }
        }
    }
    
    //================================================蓝牙开启================================================//
    /**
   	 * 注册蓝牙广播接收器
   	 */
   	public void registerBTReceiver(Context context) {
   		if (mReceiver == null) {
   			mReceiver = new BlueToothReceiver();
   		}
   		IntentFilter intentFilter = new IntentFilter();     
   		intentFilter.addAction(BluetoothDevice.ACTION_FOUND);     
   		intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);     
   		intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
   		context.getApplicationContext().registerReceiver(mReceiver, intentFilter); 
   		mNeed2unRegister = true;
   	}
    
	/**
	 * 开启蓝牙
	 * @param isReOpen 是否是重新打开蓝牙
	 */
	public void openBluetooth(final boolean isReOpen) {
		ThreadPoolManager.getInstance().addTask(new Runnable() {
			boolean openResult;
			@Override
			public void run() {
				if (isReOpen) {
					openResult = reOpenBluetooth();
				} else {
					openResult = openBluetooth();
				}
				ActivityUtil.runOnUIThread(new Runnable() {
					@Override
					public void run() {
						if (openResult) {
							if (mListener != null) {
								if (isReOpen) {
									mListener.onBluetoothReOpened();
								} else {
									mListener.onBluetoothOpened();
								}
							}
						}
					}
				});
			}
		});
    }
	
    /**
	 * 开启蓝牙【耗时操作】
	 */
	private boolean openBluetooth() {
        boolean result = false;
        if (mBluetoothAdapter != null) {
            if (mBluetoothAdapter.isEnabled()) {
            	return true;
            } else {
            	result = enableBluetooth();
            }
        } else {
        	if (mListener != null) {
        		mListener.onError(new NullPointerException(DEVICE_HAS_NOT_BLUETOOTH_MODULE));
        	}
        }
        return result;
    }
	
	/**
	 * 重新开启蓝牙【耗时操作】
	 */
	private boolean reOpenBluetooth() {
        if (mBluetoothAdapter.isEnabled()) {
        	mBluetoothAdapter.disable(); //关闭蓝牙  
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
        return enableBluetooth();
    }
	
	/**
	 * 打开蓝牙开关【耗时操作】
	 * @return
	 */
	private boolean enableBluetooth() {
		boolean result = false;
		if (mBluetoothAdapter != null) {
			mBluetoothAdapter.enable(); //重新开启蓝牙  				
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (mBluetoothAdapter.isEnabled()) {
	            result = true;
	        }
		} else {
			if (mListener != null) {
        		mListener.onError(new NullPointerException(DEVICE_HAS_NOT_BLUETOOTH_MODULE));
        	}
		}
		return result;
	}
	//================================================蓝牙扫描================================================//
	
	public void setOnBluetoothDeviceListener(OnBluetoothDeviceListener listener) {
		mListener = listener;
	}
	
	/**
	 * Start to search bluetooth device
	 */
	public void startSearch() {
		if (mBluetoothAdapter.isDiscovering()) {
			mBluetoothAdapter.cancelDiscovery();
		}
		mBluetoothAdapter.startDiscovery();
	}
	
	 /**
     * discovery the devices.
     *
     * @param listener listener for the process
     */
    public void searchDevices(OnBluetoothDeviceListener listener) {
    	mListener = listener;
        if (mBluetoothAdapter == null) {
        	if (mListener != null) {
        		mListener.onError(new NullPointerException(DEVICE_HAS_NOT_BLUETOOTH_MODULE));
        	}
            return;
        }
        registerBTReceiver(mContext);
        
        if (mBondedList == null) {
        	mBondedList = new ArrayList<BluetoothDevice>();
        }
        if (mNewList == null) {
        	mNewList = new ArrayList<BluetoothDevice>();
        }
        mBondedList.clear();
        mNewList.clear();
       
        startSearch();
        if (mListener != null) {
        	mListener.onStartDiscovery();
        }
    }
	
	//================================================蓝牙匹配================================================//
    /**
	 * 检验蓝牙地址的有效性
	 * @param address 蓝牙地址
	 * @return true:有效，false:无效
	 */
	public boolean isBtAddressVaild(String address) {
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
	
	//================================================对象销毁================================================//
	 /**
     * Closes the connection and releases any system resources associated
     * with the stream.
     */
    public void close() {
        if (mBluetoothAdapter != null) {
        	mBluetoothAdapter.cancelDiscovery();
        }
        // unregister
        if (mNeed2unRegister) {
            mContext.unregisterReceiver(mReceiver);
            mNeed2unRegister = false;
        }
        mNewList = null;
        mBondedList = null;
        mListener = null;
        mReceiver = null;
    }
	
	/**
	 * 蓝牙设备监听器
	 * @author xx
	 *
	 */
	public interface OnBluetoothDeviceListener extends OnSearchDeviceListener {
		/**
		 * 蓝牙绑定状态发生改变
		 */
		void onBondStateChanged(BluetoothDevice device, Intent intent);
		
		/**
		 * 蓝牙打开监听
		 */
		void onBluetoothOpened();
		
		/**
		 * 蓝牙重新打开监听
		 */
		void onBluetoothReOpened();
	}
}
