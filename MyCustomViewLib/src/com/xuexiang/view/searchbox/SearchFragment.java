package com.xuexiang.view.searchbox;

import java.util.ArrayList;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xuexiang.R;
import com.xuexiang.util.view.InputMethodUtils;
import com.xuexiang.view.searchbox.adapter.SearchHistoryAdapter;
import com.xuexiang.view.searchbox.anim.CircularRevealAnim;
import com.xuexiang.view.searchbox.db.SearchHistoryDB;
import com.xuexiang.view.searchbox.listener.IOnItemClickListener;
import com.xuexiang.view.searchbox.listener.IOnSearchClickListener;

/**
 * Created by Won on 2017/1/13.
 */

public class SearchFragment extends DialogFragment implements DialogInterface.OnKeyListener, ViewTreeObserver.OnPreDrawListener, CircularRevealAnim.AnimListener, IOnItemClickListener, View.OnClickListener {

    public static final String TAG = "SearchFragment";
    private ImageView mIvSearchBack;
    private EditText mEtSearchKeyword;
    private ImageView mIvSearchSearch;
    private RecyclerView mRvSearchHistory;
    private View mSearchUnderline;
    private TextView mTvSearchClean;
    private View mViewSearchOutside;

    private View mView;
    //动画
    private CircularRevealAnim mCircularRevealAnim;
    //历史搜索记录
    private ArrayList<String> mAllHistorys = new ArrayList<>();
    private ArrayList<String> mHistorys = new ArrayList<>();
    //适配器
    private SearchHistoryAdapter mSearchHistoryAdapter;
    //数据库
    private SearchHistoryDB mSearchHistoryDB;

    public static SearchFragment newInstance() {
        Bundle bundle = new Bundle();
        SearchFragment searchFragment = new SearchFragment();
        searchFragment.setArguments(bundle);
        return searchFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogStyle);
    }

    @Override
    public void onStart() {
        super.onStart();
        initDialog();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.dialog_search, container, false);

        init();//实例化

        return mView;
    }

    private void init() {
        mIvSearchBack = (ImageView) mView.findViewById(R.id.iv_search_back);
        mEtSearchKeyword = (EditText) mView.findViewById(R.id.et_search_keyword);
        mIvSearchSearch = (ImageView) mView.findViewById(R.id.iv_search_search);
        mRvSearchHistory = (RecyclerView) mView.findViewById(R.id.rv_search_history);
        mSearchUnderline = (View) mView.findViewById(R.id.search_underline);
        mTvSearchClean = (TextView) mView.findViewById(R.id.tv_search_clean);
        mViewSearchOutside = (View) mView.findViewById(R.id.view_search_outside);

        //实例化动画效果
        mCircularRevealAnim = new CircularRevealAnim();
        //监听动画
        mCircularRevealAnim.setAnimListener(this);

        getDialog().setOnKeyListener(this);//键盘按键监听
        mIvSearchSearch.getViewTreeObserver().addOnPreDrawListener(this);//绘制监听

        //实例化数据库
        mSearchHistoryDB = new SearchHistoryDB(getContext(), SearchHistoryDB.DB_NAME, null, 1);

        mAllHistorys = mSearchHistoryDB.queryAllHistory();
        setAllHistorys();
        //初始化recyclerView
        mRvSearchHistory.setLayoutManager(new LinearLayoutManager(getContext()));//list类型
        mSearchHistoryAdapter = new SearchHistoryAdapter(getContext(), mHistorys);
        mRvSearchHistory.setAdapter(mSearchHistoryAdapter);

        //设置删除单个记录的监听
        mSearchHistoryAdapter.setOnItemClickListener(this);
        //监听编辑框文字改变
        mEtSearchKeyword.addTextChangedListener(new TextWatcherImpl());
        //监听点击
        mIvSearchBack.setOnClickListener(this);
        mViewSearchOutside.setOnClickListener(this);
        mIvSearchSearch.setOnClickListener(this);
        mTvSearchClean.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_search_back || view.getId() == R.id.view_search_outside) {
            hideAnim();
        } else if (view.getId() == R.id.iv_search_search) {
            search();
        } else if (view.getId() == R.id.tv_search_clean) {
            mSearchHistoryDB.deleteAllHistory();
            mHistorys.clear();
            mSearchUnderline.setVisibility(View.GONE);
            mSearchHistoryAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 初始化SearchFragment
     */
    private void initDialog() {
        Window window = getDialog().getWindow();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = (int) (metrics.widthPixels * 0.98); //DialogSearch的宽
        window.setLayout(width, WindowManager.LayoutParams.MATCH_PARENT);
        window.setGravity(Gravity.TOP);
        window.setWindowAnimations(R.style.DialogEmptyAnimation);//取消过渡动画 , 使DialogSearch的出现更加平滑
    }

    /**
     * 监听键盘按键
     */
    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            hideAnim();
        } else if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            search();
        }
        return false;
    }

    /**
     * 监听搜索键绘制时
     */
    @Override
    public boolean onPreDraw() {
        mIvSearchSearch.getViewTreeObserver().removeOnPreDrawListener(this);
        mCircularRevealAnim.show(mIvSearchSearch, mView);
        return true;
    }

    /**
     * 搜索框动画隐藏完毕时调用
     */
    @Override
    public void onHideAnimationEnd() {
        mEtSearchKeyword.setText("");
        dismiss();
    }

    /**
     * 搜索框动画显示完毕时调用
     */
    @Override
    public void onShowAnimationEnd() {
        if (isVisible()) {
            InputMethodUtils.showInputMethod(mEtSearchKeyword);
        }
    }

    /**
     * 监听编辑框文字改变
     */
    private class TextWatcherImpl implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String keyword = editable.toString();
            if (TextUtils.isEmpty(keyword.trim())) {
                setAllHistorys();
                mSearchHistoryAdapter.notifyDataSetChanged();
            } else {
                setKeyWordHistorys(editable.toString());
            }
        }
    }

    /**
     * 点击单个搜索记录
     */
    @Override
    public void onItemClick(String keyword) {
        iOnSearchClickListener.OnSearchClick(keyword);
        hideAnim();
    }

    /**
     * 删除单个搜索记录
     */
    @Override
    public void onItemDeleteClick(String keyword) {
        mSearchHistoryDB.deleteHistory(keyword);
        mHistorys.remove(keyword);
        checkHistorySize();
        mSearchHistoryAdapter.notifyDataSetChanged();
    }

    private void hideAnim() {
    	InputMethodUtils.hideKeyboard(mEtSearchKeyword);
        mCircularRevealAnim.hide(mIvSearchSearch, mView);
    }

    private void search() {
        String searchKey = mEtSearchKeyword.getText().toString();
        if (TextUtils.isEmpty(searchKey.trim())) {
            Toast.makeText(getContext(), "请输入关键字", Toast.LENGTH_SHORT).show();
        } else {
            iOnSearchClickListener.OnSearchClick(searchKey);//接口回调
            mSearchHistoryDB.insertHistory(searchKey);//插入到数据库
            hideAnim();
        }
    }

    private void checkHistorySize() {
        if (mHistorys.size() < 1) {
            mSearchUnderline.setVisibility(View.GONE);
        } else {
            mSearchUnderline.setVisibility(View.VISIBLE);
        }
    }

    private void setAllHistorys() {
        mHistorys.clear();
        mHistorys.addAll(mAllHistorys);
        checkHistorySize();
    }

    private void setKeyWordHistorys(String keyword) {
        mHistorys.clear();
        for (String string : mAllHistorys) {
            if (string.contains(keyword)) {
                mHistorys.add(string);
            }
        }
        mSearchHistoryAdapter.notifyDataSetChanged();
        checkHistorySize();
    }

    private IOnSearchClickListener iOnSearchClickListener;

    public void setOnSearchClickListener(IOnSearchClickListener iOnSearchClickListener) {
        this.iOnSearchClickListener = iOnSearchClickListener;
    }

}
