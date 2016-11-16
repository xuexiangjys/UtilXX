package com.xuexiang.util.data.sharedPreferences;

import android.content.Context;


/**
 * 用户管理
 * @author xx
 *
 */
public class UserSharePreferenceUtil extends BaseSharedPreferencesUtil {
	private static UserSharePreferenceUtil sInstance;	
	private static String USER_INFO = "user_Info";
	
	private String SHARED_KEY_USER_NAME = "username";
	private String SHARED_KEY_PASSWORD = "password";
	private String SHARED_KEY_PHONE = "phone";
	private String SHARED_KEY_HEADPHOTO = "headphoto";
	private String SHARED_KEY_NICKNAME = "nickname";
	private String SHARED_KEY_SEX = "sex";
	private String SHARED_KEY_SIGNATURE = "signature";
	private String SHARED_KEY_REALNAME = "realname";
	private String SHARED_KEY_IDENTITYCARD = "identitycard";
	private String SHARED_KEY_ISCHECK = "ischeck";	
	/**********************默认地址**************************************/	
	private String SHARED_KEY_CONSIGNEE_DEFAULT = "consignee";
	private String SHARED_KEY_CONTACT_DEFAULT = "contact";
	private String SHARED_KEY_ADDRESS_DEFAULT = "address";
	private String SHARED_KEY_POSTCODE_DEFAULT = "postcode";	
	/**********************自动登录**************************************/	
	private String SHARED_KEY_REMEMBER = "remember";
	private String SHARED_KEY_AUTO_LOGIN = "autologin";


	public UserSharePreferenceUtil(Context context) {
		super(context, USER_INFO);
	}
	
	public static UserSharePreferenceUtil getInstance(Context c) {
		if (sInstance == null) {
			sInstance = new UserSharePreferenceUtil(c.getApplicationContext());
		}
		return sInstance;
	}
	
	
//	public void setUserInfo(UserInfo userinfo) {  
//        editor.putString(SHARED_KEY_USER_NAME, userinfo.getLoginname());  
//        editor.putString(SHARED_KEY_PASSWORD, userinfo.getPassword());  
//        editor.putString(SHARED_KEY_PHONE, userinfo.getPhone()); 
//        editor.putString(SHARED_KEY_HEADPHOTO, userinfo.getHeadphoto());  
//        editor.putString(SHARED_KEY_NICKNAME, userinfo.getNickname());
//        editor.putString(SHARED_KEY_SEX, userinfo.getSex());  
//        editor.putString(SHARED_KEY_SIGNATURE, userinfo.getSignature());  
//        editor.putString(SHARED_KEY_REALNAME, userinfo.getRealname()); 
//        editor.putString(SHARED_KEY_IDENTITYCARD, userinfo.getIdentitycard());  
//        editor.putString(SHARED_KEY_ISCHECK, userinfo.getIscheck());  
//        editor.commit();  
//    }  
	
//	public UserInfo getUserInfo() {  
//		UserInfo userinfo = new UserInfo();
//		userinfo.setLoginname(getLoginname());
//		userinfo.setPassword(getPassword());
//		userinfo.setPhone(getPhone());
//		userinfo.setHeadphoto(getHeadphoto());
//		userinfo.setNickname(getNickname());
//		userinfo.setSex(getSex());
//		userinfo.setSignature(getSignature());
//		userinfo.setRealname(getRealname());
//		userinfo.setIdentitycard(getIdentitycard());
//		userinfo.setIscheck(getIscheck());
//        return userinfo;
//    }  
//	
//	public void setAddressInfo(AddressInfo addressinfo) {  
//        editor.putString(SHARED_KEY_CONSIGNEE_DEFAULT, addressinfo.getConsignee());  
//        editor.putString(SHARED_KEY_CONTACT_DEFAULT, addressinfo.getContact()); 
//        editor.putString(SHARED_KEY_ADDRESS_DEFAULT, addressinfo.getAddress());  
//        editor.putString(SHARED_KEY_POSTCODE_DEFAULT, addressinfo.getPostcode());  
//        editor.commit();  
//    }  
//	
	
	public void setLoginname(String loginname) {  
        putString(SHARED_KEY_USER_NAME, loginname);          
    }  
	
	public void setPassword(String password) {  
        putString(SHARED_KEY_PASSWORD, password);          
    }  
	
	public void setPhone(String phone) {
	    putString(SHARED_KEY_PHONE, phone);           
	}
	public void setHeadphoto(String headphoto) {
        putString(SHARED_KEY_HEADPHOTO, headphoto);           
    }  
	
	public void setNickname(String nickname) {
        putString(SHARED_KEY_NICKNAME, nickname);          
    }  
	
	public void setSex(String sex) {
        putString(SHARED_KEY_SEX, sex);           
    }  
	
	public void setSignature(String signature) {
        putString(SHARED_KEY_SIGNATURE, signature);          
    }  
	
	public void setRealname(String realname) {
        putString(SHARED_KEY_REALNAME, realname);          
    }  
	
	public void setIdentitycard(String identitycard) {
		 putString(SHARED_KEY_IDENTITYCARD, identitycard);  	      
	}
	
	public void setIscheck(String ischeck) {
		 putString(SHARED_KEY_ISCHECK, ischeck);  	      
	}
	
	public void setConsignee(String consignee) {
		 putString(SHARED_KEY_CONSIGNEE_DEFAULT, consignee);  	      
	}
	
	public void setContact(String contact) {
		 putString(SHARED_KEY_CONTACT_DEFAULT, contact);  	      
	}
	
	public void setAddress(String address) {
		 putString(SHARED_KEY_ADDRESS_DEFAULT, address);  	      
	}
	
	public void setPostcode(String postcode) {
		 putString(SHARED_KEY_POSTCODE_DEFAULT, postcode);  	      
	}	
	
	public void setRemember(boolean isremember) {  
		putBoolean(SHARED_KEY_REMEMBER, isremember);		         
    }  
	
	public void setAutologin(boolean isautologin) {  
		putBoolean(SHARED_KEY_AUTO_LOGIN, isautologin);		         
    }  
		  
    public String getLoginname() {  
        return getString(SHARED_KEY_USER_NAME,"");  
    }  
    
    public String getPassword() {  
        return getString(SHARED_KEY_PASSWORD,"");  
    }  
    
    public String getPhone() {  
        return getString(SHARED_KEY_PHONE,"");  
    }  
    
    public String getHeadphoto() {  
        return getString(SHARED_KEY_HEADPHOTO,"");  
    } 
    
    public String getNickname() {  
        return getString(SHARED_KEY_NICKNAME,"");  
    }  
    
    public String getSex() {  
        return getString(SHARED_KEY_SEX,"");  
    } 
    
    public String getSignature() {  
        return getString(SHARED_KEY_SIGNATURE,"");  
    } 
    
    public String getRealname() {  
        return getString(SHARED_KEY_REALNAME,"");  
    } 
    
    public String getIdentitycard() {  
        return getString(SHARED_KEY_IDENTITYCARD,"");  
    }  
    
    public String getIscheck() {  
        return getString(SHARED_KEY_ISCHECK,"");  
    } 
    
    public String getConsignee() {  
        return getString(SHARED_KEY_CONSIGNEE_DEFAULT,"");  
    } 
    
    public String getContact() {  
        return getString(SHARED_KEY_CONTACT_DEFAULT,"");  
    } 
    
    public String getAddress() {  
        return getString(SHARED_KEY_ADDRESS_DEFAULT,"");  
    } 
    
    public String getPostcode() {  
        return getString(SHARED_KEY_POSTCODE_DEFAULT,"");  
    } 
        
    public boolean getRemember() {  
        return getBoolean(SHARED_KEY_REMEMBER,false);  
    }  
    
    public boolean getAutologin() {  
        return getBoolean(SHARED_KEY_AUTO_LOGIN,false);  
    }  
	
    /**
     * 登出，销毁记录
     */
    public void LoginOut() {  
        clear();
    }  

}
