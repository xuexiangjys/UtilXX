package com.example.testutil.net.model;

import com.xuexiang.app.SampleApplication;

/**
 * 常量类
 *
 */
public class HttpConsts {
	
//	public static final String TEST_URL = "http://www.weather.com.cn/adat/sk/101010100.html";	
	public static final String TEST_URL = "https://kyfw.12306.cn/otn/";	
	/** 用户信息管理服务的地址*/
	public static String app_user_url = SampleApplication.app_url + "helper/User";
	/** 上门服务信息处理请求地址*/
	public static String app_visitservice_url = SampleApplication.app_url + "helper/VisitService";
	public static String visitservice_pic_bath_url  = SampleApplication.app_url + "helper/User/VisitService/";
	
	public static String student_url = SampleApplication.app_url + "jsonweb/SendStudentInfoServlet";
	
	public static String UPLPAD_URL = SampleApplication.app_url + "UploadFileServer/UploadFileServlet";
	public static String APK_UPDATE_URL = SampleApplication.app_url + "helper/APK/1.3/helper.apk";
	public static void setAppUrl(String url) {
		SampleApplication.app_url = url;
		app_user_url = SampleApplication.app_url + "helper/User";
		app_visitservice_url = SampleApplication.app_url + "helper/VisitService";
		visitservice_pic_bath_url  = SampleApplication.app_url + "helper/User/VisitService/";
		student_url = SampleApplication.app_url + "jsonweb/SendStudentInfoServlet";
		UPLPAD_URL = SampleApplication.app_url + "UploadFileServer/UploadFileServlet";
		APK_UPDATE_URL = SampleApplication.app_url + "helper/APK/1.3/helper.apk";
	}
	
	public static final class User{
		public static final String REGISTER = "Register";
		public static final String CHECKUSER = "CheckUser";
		public static final String LOGINCHECK = "LoginCheck";
		public static final String GETUSERINFOBYLOGINNAME = "GetUserInfoByLoginname";
		public static final String UPDATENICKNAME = "UpdateNickName";
		public static final String UPDATESEX = "UpdateSex";
		public static final String UPDATESIGNATURE = "UpdateSignature";
		public static final String UPDATEPHONE = "UpdatePhone";
		public static final String UPDATEPASSWORD = "UpdatePassword";
		
	}
	
	public static final class Address{
		public static final String GETALLADDRESS = "GetAllAddress";
		public static final String ADD = "Add";
		public static final String DELETE = "Delete";
		public static final String UPDATE = "Update";
		public static final String UPDATE_ISDEFAULT = "UpdateIsdefault";
		
	}
	
	public static final class App{
		public static final String GETNEWAPPINFO = "getNewAppInfo";
		public static final String ISNEEDUPDATE = "isNeedUpdate";		
	}
	
	public static final class FeedBack {
		public static final String DELETE = "Delete";
		public static final String GET_OWN_FEEDBACKINFO_BY_PAGE = "GetOwnFeedBackInfoByPage";
		public static final String GET_ALL_FEEDBACKINFO_BY_PAGE = "GetAllFeedBackInfoByPage";
		public static final String ADD = "Add";;

	}
	
	public static final class Qiangyu{
		public static final String ADD = "Add";	
		public static final String DELETE = "Delete";
		public static final String GET_ALL_QIANGYU_BY_PAGE = "GetAllQiangYuByPage";					
		public static final String GET_OWN_QIANGYU_BY_PAGE = "GetOwnQiangYuByPage";			
		public static final String ONCLICK_LOVE = "onClickLove";	
	}
	
	public static final class VisitService{
		public static final String ADD = "Add";	
		public static final String DELETE = "Delete";	
		public static final String GET_ALL_VISITINFO_BY_PAGE = "GetAllVisitInfoByPage";					
		public static final String GET_OWN_VISITINFO_BY_PAGE = "GetOwnVisitInfoByPage";		
		public static final String GET_TYPE_VISITINFO_BY_PAGE = "GetTypeVisitInfoByPage";
		public static final String ONCLICK_LOVE = "onClickLove";	
	}
	
	public static final class Repair{
		public static final String ADD = "Add";	
		public static final String DELETE = "Delete";	
		public static final String GET_ALL_REPAIRINFO_BY_PAGE = "GetAllRepairInfoByPage";					
		public static final String GET_OWN_REPAIRINFO_BY_PAGE = "GetOwnRepairInfoByPage";		
		public static final String GET_TYPE_REPAIRINFO_BY_PAGE = "GetTypeRepairInfoByPage";
	}
	
	public static final class CommentNet{
		public static final String GETALLCOMMENT = "GetAllComment";	
		public static final String ADD = "Add";	
		public static final String DELETE = "Delete";
	}
	
	public static final class FoodShopService{		
		public static final String REGISTER = "Register";	
		public static final String DELETE = "Delete";
		public static final String GET_FOODSHOP_BY_ID = "GetFoodShopById";	
		public static final String GET_FOODSHOP_BY_TYPE_PAGE = "GetFoodShopByTypeAndPage";	
		public static final String GET_FOODSHOP_BY_PAGE = "GetFoodShopByPage";	
		public static final String GET_FOODSHOP_BY_TYPE = "GetFoodShopByType";	
		public static final String GET_FOODSHOP_BY_CITY_PAGE = "GetFoodShopByCityAndPage";	
		public static final String GET_FOODSHOP_BY_CITY_TYPE_PAGE = "GetFoodShopByCityAndTypeAndPage";
		public static final String GET_FOODSHOP_BY_CITY_TYPE = "GetFoodShopByCityAndType";
		public static final String GET_TYPEFOODSHOP_BY_COMPLEX_CONDITION = "GetTypeFoodShopByComplexCondition";		
	}
	
	public static final class FoodService{		
		public static final String ADD = "Add";	
		public static final String DELETE = "Delete";
		public static final String GET_FOODINFO_BY_ID = "GetFoodInfoById";	
		public static final String GET_FOODINFO_BY_SHOPID = "GetFoodInfoByShopId";	
		public static final String GET_FOODTYPE_BY_SHOPID = "GetFoodTypeByShopId";			
	}
	
	
	
	/**
	 * 声明广播的action
	 */
	public static final class Brodcast_Action{
		public static final String ACTION_POLL_SERVICE="com.bmob.helpertest.poll.action.MESSAGE";
	}
	
	public static final class key {
		public static final String EXTRA_POLL_MESSAGE_STRING="com.bmob.helpertest.poll.message";
		public static final String EXTRA_PICTURE="com.bmob.helpertest.db.Picture";
		public static final String EXTRA_QIANGYU="com.bmob.helpertest.adapter.AIContentAdapter.QIANGYU";
		public static final String EXTRA_USERINFO="com.bmob.helpertest.entity.UserInfo";
		public static final String EXTRA_VISITSERVICE="com.bmob.helpertest.entity.VisitInfo";
		public static final String EXTRA_FOODSHOP="com.bmob.helpertest.entity.FoodShop";
		public static final String EXTRA_BIG_PICTURE_URL = "com.bmob.helpertest.entity.BigPictureUrl";
		public static final String EXTRA_ORDERLOCAL="com.bmob.helpertest.db.OrderLocal";
	}
	
	
	public static final class Result{
		public static final String ERROR_ACCESS_SERVER="com.xuexiang.util.consts.Error.ERROR_ACCESS_SERVER";
		public static final String ERROR_READ_DATA="com.xuexiang.util.consts.Error.ERROR_READ_DATA";
		public static final String ERROR_ACCESS_TIMEOUT="com.xuexiang.util.consts.Error.ERROR_ACCESS_TIMEOUT";
		public static final String ERROR_ACCESS_OUT_OF_MEMORY="com.xuexiang.util.consts.Error.ERROR_OUT_OF_MEMORY";
		public static final String ERROR_REQUEST_SERVER_FAIL="ERROR_REQUEST_SERVER_FAIL";//请求失败
		public static final String ERROR_ADDRESS_FORMAT="ERROR_ADDRESS_FORMAT";
		public static final String ERROR_RECEIVE_EXCEPTION="com.xuexiang.util.consts.Error.ERROR_RECEIVE_EXCEPTION";
		
		public static final String REQUEST_SERVER_SUCCESS="REQUEST_SERVER_SUCCESS";
		public static final String FILE_NOT_FOUND_ERROR="FILE_NOT_FOUND_ERROR";
		public static final String UPLOAD_SUCCESS="UPLOAD_SUCCESS";
		public static final String UPLOAD_FAILED="UPLOAD_FAILED";
		public static final String FILE_NOT_FOUND="FILE_NOT_FOUND";
		
	}
	
	/**
	 * Handle消息的what
	 * 
	 */
	public static final class What{
		public static final int ACCESS_SERVER__FAILED=20001;
		public static final int ALREADY_CAPTURE_REQUEST_FALL=20002;
		public static final int REQUEST_TIMEOUT=20003;
		public static final int REQUEST_OUT_OF_MEMORY=20004;
		public static final int REQUEST_EXCEPTION = 20035;
		public static final int REQUEST_SUCCESS = 20050;
		public static final int REQUEST_SUCCESS_MODE = 20051;
		public static final int REQUEST_FAIL = 20052;
		public static final int CODE_CHECK = 20053;
		
		
		
	}
	
	/**
	 * fragment ID
	 * 
	 */
	public static final class fragment{
		public static final int HOME_F = 0;
		public static final int DISCOVER_F = 1;
		public static final int VISIT_F = 2;
		public static final int CART_F = 3;
		public static final int USER_F = 4;
		
	}
	
	/**
	 * 商铺类型 
	 */
	public static final class ShopType{
		public static final String REPAIR= "repair";		
		public static final String DECORATION = "decoration";
		public static final String HOMEMAKING = "homemaking";
		public static final String FOODS = "foods";
		public static final String VISIT = "visit";		
	}
	
	/**
	 * 上门服务类型 
	 */
	public static final class VisitType{
		/** 家教*/
		public static final String PRIVATE_TEACHER= "privateteacher";	
		/** 美甲*/
		public static final String MANICURE = "manicure";
		/** 按摩*/
		public static final String MASSAGE = "massage";
		/** 大厨*/
		public static final String COOK = "cook";
		/** 洗衣*/
		public static final String LAUNDRY = "laundry";		
		/** 保养车辆*/
		public static final String KEEP_CAR = "keep_car";		
		/** 健康*/
		public static final String HEALTHY = "healthy";		
		/** 其他*/
		public static final String OTHER = "other";		
	}
	
	/**
	 * 美食店铺类型
	 */
	public static final class FoodShopType{
		/** 美食*/
		public static final String DELICIOUS_FOOD= "deliciousfood";	
		/** 超市*/
		public static final String SUPERMARKET = "supermarket";
		/** 果蔬生鲜*/
		public static final String FRESH_FRUITS = "freshfruits";
		/** 甜点饮品*/
		public static final String DESSERT_DRINK = "Dessertdrink";
		/** 连锁品牌*/
		public static final String CHAIN_BRAND = "Chainbrand";		
		/** 乐居专送*/
		public static final String SPECIAL_DELIVERY = "Specialdelivery";		
		/** 鲜花*/
		public static final String FLOWER = "flower";		
		/** 送药上门*/
		public static final String SEND_MEDICINE = "sendmedicine";
	}
	
	/**
	 * 维修类型 
	 */
	public static final class RepairType{
		/** 水电类*/
		public static final String PLUMBER= "plumber";	
		/** 木工类*/
		public static final String WOODWORKER = "woodworker";
		/** 泥瓦类*/
		public static final String MASON = "mason";
		/** 油漆类*/
		public static final String PAINTER = "painter";
	}
	
	/**
	 * 银行类型
	 */
	public static final class BankType{
		/** 中国建设银行*/
		public static final String CCB= "ccb";	
		/** 中国农业银行*/
		public static final String ABC = "abc";
		/** 中国工商银行*/
		public static final String ICBC = "icbc";
		/** 中国银行*/
		public static final String BOC = "boc";
		/** 中国民生银行*/
		public static final String CMBC = "cmbc";		
		/** 招商银行*/
		public static final String CMB = "cmb";		
		/** 兴业银行 */
		public static final String CIB = "cib";		
		/** 交通银行*/
		public static final String BCM = "bcm";		
		/**中国光大银行*/
		public static final String CEB = "ceb";	
		/**广东发展银行*/
		public static final String GDB = "gdb";	
	}
	
	
	public static final class ShopList {		
		// shoplist中商铺类型
	    public static final String[] SHOPLIST_TYPE = { "全部品类", "美食", "超市",
					"果蔬生鲜", "甜点饮品", "连锁品牌", "乐居专送", "鲜花", "送药上门"}; 
	    
	    // shoplist中排序条件
	 	public static final String[] SHOPLIST_CONDITION = { "综合排序", "销量最高", "速度最快",
	 			"评分最高", "起送价最低", "配送费最低" };	 	
	    // shoplist中排序条件
	 	public static final String[] SHOPLIST_CONDITION_HTTP = { "", "salednum", "delivertime",
	 	 			"starnum", "startingprice", "deliverprice" };
	 	
	    // shoplist中促销条件筛选
	    public static final String[] SHOPLIST_PROMOTION = { "筛选", "首单立减", "满减优惠", "折扣优惠",
					"满返代金券", "提前下单优惠", "满赠活动", "满免配送费" };	 
	    // shoplist中促销条件筛选
	    public static final String[] SHOPLIST_PROMOTION_HTTP = { "", "firstorder", "fullreduction", "discount",
					"vouchers", "preorder", "fullofgifts", "freedistribution" };	 
	}

	
	
}
