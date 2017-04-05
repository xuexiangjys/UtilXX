package com.example.mycustomview.custom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.example.mycustomview.R;
import com.xuexiang.view.searchbox.SearchFragment;
import com.xuexiang.view.searchbox.listener.IOnSearchClickListener;

public class SearchBoxDialogActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, IOnSearchClickListener {

	@Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.search_info)
    TextView searchInfo;

    private SearchFragment searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbox_dialog);
        ButterKnife.bind(this);

        toolbar.setTitle("SearchDialog");//标题
        setSupportActionBar(toolbar);

        searchFragment = SearchFragment.newInstance();

        toolbar.setOnMenuItemClickListener(this);

        searchFragment.setOnSearchClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载菜单文件
        getMenuInflater().inflate(R.menu.menu_searchbox, menu);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search://点击搜索
                searchFragment.show(getSupportFragmentManager(), SearchFragment.TAG);
                break;
        }
        return true;
    }

    @Override
    public void OnSearchClick(String keyword) {
        searchInfo.setText(keyword);
    }
}
