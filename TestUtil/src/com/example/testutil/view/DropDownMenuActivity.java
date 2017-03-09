package com.example.testutil.view;

import java.util.ArrayList;
import java.util.List;

import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

import com.example.testutil.R;
import com.xuexiang.app.activity.BaseActivity;
import com.xuexiang.view.dropdownmenu.entity.DropdownItemObject;
import com.xuexiang.view.dropdownmenu.entity.TopicLabelObject;
import com.xuexiang.view.dropdownmenu.view.DropdownButton;
import com.xuexiang.view.dropdownmenu.view.DropdownListView;

/**  
 * 鍒涘缓鏃堕棿锛�2016-6-14 涓嬪崍12:16:41  
 * 椤圭洰鍚嶇О锛歎tilTest  
 * @author xuexiang
 * 鏂囦欢鍚嶇О锛欴ropDownMenuActivity.java  
 **/
public class DropDownMenuActivity extends BaseActivity {

	private static final int ID_TYPE_ALL = 0;
    private static final int ID_TYPE_MY = 1;
    private static final String TYPE_ALL = "鍏ㄩ儴璁ㄨ";
    private static final String TYPE_MY = "鎴戠殑璁ㄨ";

    private static final int ID_LABEL_ALL = -1;
    private static final String LABEL_ALL = "鍏ㄩ儴鏍囩";

    private static final String ORDER_REPLY_TIME = "璇勮鎺掑簭";
    private static final String ORDER_PUBLISH_TIME = "鍙戝竷鏃堕棿鎺掑簭";
    private static final String ORDER_HOT = "鐑棬鎺掑簭";
    private static final int ID_ORDER_REPLY_TIME = 51;
    private static final int ID_ORDER_PUBLISH_TIME = 49;
    private static final int ID_ORDER_HOT = 53;

    ListView listView;
    View mask;
    DropdownButton chooseType, chooseLabel, chooseOrder;
    DropdownListView dropdownType, dropdownLabel, dropdownOrder;

    Animation dropdown_in, dropdown_out, dropdown_mask_out;

    private List<TopicLabelObject> labels = new ArrayList<TopicLabelObject>();

    private DropdownButtonsController dropdownButtonsController = new DropdownButtonsController();

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_dropdownmenu);
		
		initTitleBar(TAG);
		
		initView();
	}
	
	private void initView() {
		listView = (ListView) findViewById(R.id.listView);
        mask = findViewById(R.id.mask);
        chooseType = (DropdownButton) findViewById(R.id.chooseType);
        chooseLabel = (DropdownButton) findViewById(R.id.chooseLabel);
        chooseOrder = (DropdownButton) findViewById(R.id.chooseOrder);
        dropdownType = (DropdownListView) findViewById(R.id.dropdownType);
        dropdownLabel = (DropdownListView) findViewById(R.id.dropdownLabel);
        dropdownOrder = (DropdownListView) findViewById(R.id.dropdownOrder);

        dropdown_in = AnimationUtils.loadAnimation(this,R.anim.dropdown_in);
        dropdown_out = AnimationUtils.loadAnimation(this,R.anim.dropdown_out);
        dropdown_mask_out = AnimationUtils.loadAnimation(this,R.anim.dropdown_mask_out);

        dropdownButtonsController.init();

        //id count name
        TopicLabelObject topicLabelObject1 =  new TopicLabelObject(1,1,"Fragment");
        labels.add(topicLabelObject1);
        TopicLabelObject topicLabelObject2 =new TopicLabelObject(2,1,"CustomView");
        labels.add(topicLabelObject2);
        TopicLabelObject topicLabelObject3 =new TopicLabelObject(2,1,"Service");
        labels.add(topicLabelObject3);
        TopicLabelObject topicLabelObject4 =new TopicLabelObject(2,1,"BroadcastReceiver");
        labels.add(topicLabelObject4);
        TopicLabelObject topicLabelObject5 =new TopicLabelObject(2,1,"Activity");
        labels.add(topicLabelObject5);

        mask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropdownButtonsController.hide();
            }
        });

        dropdownButtonsController.flushCounts();
        dropdownButtonsController.flushAllLabels();
        dropdownButtonsController.flushMyLabels();

	}

	private class DropdownButtonsController implements DropdownListView.Container {
        private DropdownListView currentDropdownList;
        private List<DropdownItemObject> datasetType = new ArrayList<DropdownItemObject>(2);//鍏ㄩ儴璁ㄨ
        private List<DropdownItemObject> datasetAllLabel = new ArrayList<DropdownItemObject>();//鍏ㄩ儴鏍囩
        private List<DropdownItemObject> datasetMyLabel = new ArrayList<DropdownItemObject>();//鎴戠殑鏍囩
        private List<DropdownItemObject> datasetLabel = datasetAllLabel;//鏍囩闆嗗悎   榛樿鏄叏閮ㄦ爣锟�?        
        private List<DropdownItemObject> datasetOrder = new ArrayList<DropdownItemObject>(3);//璇勮鎺掑簭

        @Override
        public void show(DropdownListView view) {
            if (currentDropdownList != null) {
                currentDropdownList.clearAnimation();
                currentDropdownList.startAnimation(dropdown_out);
                currentDropdownList.setVisibility(View.GONE);
                currentDropdownList.button.setChecked(false);
            }
            currentDropdownList = view;
            mask.clearAnimation();
            mask.setVisibility(View.VISIBLE);
            currentDropdownList.clearAnimation();
            currentDropdownList.startAnimation(dropdown_in);
            currentDropdownList.setVisibility(View.VISIBLE);
            currentDropdownList.button.setChecked(true);
        }

        @Override
        public void hide() {
            if (currentDropdownList != null) {
                currentDropdownList.clearAnimation();
                currentDropdownList.startAnimation(dropdown_out);
                currentDropdownList.button.setChecked(false);
                mask.clearAnimation();
                mask.startAnimation(dropdown_mask_out);
            }
            currentDropdownList = null;
        }

        @Override
        public void onSelectionChanged(DropdownListView view) {
            if (view == dropdownType) {
                updateLabels(getCurrentLabels());
            }

        }

        void reset() {
            chooseType.setChecked(false);
            chooseLabel.setChecked(false);
            chooseOrder.setChecked(false);

            dropdownType.setVisibility(View.GONE);
            dropdownLabel.setVisibility(View.GONE);
            dropdownOrder.setVisibility(View.GONE);
            mask.setVisibility(View.GONE);

            dropdownType.clearAnimation();
            dropdownLabel.clearAnimation();
            dropdownOrder.clearAnimation();
            mask.clearAnimation();
        }

        void init() {
            reset();
            datasetType.add(new DropdownItemObject(TYPE_ALL, ID_TYPE_ALL, "all"));
            datasetType.add(new DropdownItemObject(TYPE_MY, ID_TYPE_MY, "my"));

            dropdownType.bind(datasetType, chooseType, this, ID_TYPE_ALL);

            datasetAllLabel.add(new DropdownItemObject(LABEL_ALL, ID_LABEL_ALL, null) {
                @Override
                public String getSuffix() {
                    return dropdownType.current == null ? "" : dropdownType.current.getSuffix();
                }
            });
            datasetMyLabel.add(new DropdownItemObject(LABEL_ALL, ID_LABEL_ALL, null));
            datasetLabel = datasetAllLabel;
            dropdownLabel.bind(datasetLabel, chooseLabel, this, ID_LABEL_ALL);

            datasetOrder.add(new DropdownItemObject(ORDER_REPLY_TIME, ID_ORDER_REPLY_TIME, "51"));
            datasetOrder.add(new DropdownItemObject(ORDER_PUBLISH_TIME, ID_ORDER_PUBLISH_TIME, "49"));
            datasetOrder.add(new DropdownItemObject(ORDER_HOT, ID_ORDER_HOT, "53"));
            dropdownOrder.bind(datasetOrder, chooseOrder, this, ID_ORDER_REPLY_TIME);

            dropdown_mask_out.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if (currentDropdownList == null) {
                        reset();
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }

        private List<DropdownItemObject> getCurrentLabels() {
            return dropdownType.current != null && dropdownType.current.id == ID_TYPE_MY ? datasetMyLabel : datasetAllLabel;
        }

        void updateLabels(List<DropdownItemObject> targetList) {
            if (targetList == getCurrentLabels()) {
                datasetLabel = targetList;
                dropdownLabel.bind(datasetLabel, chooseLabel, this, dropdownLabel.current.id);
            }
        }



        public void flushCounts() {

            datasetType.get(ID_TYPE_ALL).setSuffix(" (" + "5" + ")");
            datasetType.get(ID_TYPE_MY).setSuffix(" (" + "3" + ")");
            dropdownType.flush();
            dropdownLabel.flush();
        }

        void flushAllLabels() {
            flushLabels(datasetAllLabel);
        }

        void flushMyLabels() {
            flushLabels(datasetMyLabel);
        }

        private void flushLabels(List<DropdownItemObject> targetList) {

            while (targetList.size() > 1) targetList.remove(targetList.size() - 1);
            for (int i = 0, n = 5; i < n; i++) {

                int id = labels.get(i).getId();
                String name = labels.get(i).getName();
                if (TextUtils.isEmpty(name)) continue;
                int topicsCount = labels.get(i).getCount();
                if (topicsCount == 0 && targetList == datasetAllLabel) continue;
                DropdownItemObject item = new DropdownItemObject(name, id, String.valueOf(id));
                if (targetList == datasetAllLabel)
                    item.setSuffix("(5)");
                targetList.add(item);
            }
            updateLabels(targetList);
        }
    }

}
