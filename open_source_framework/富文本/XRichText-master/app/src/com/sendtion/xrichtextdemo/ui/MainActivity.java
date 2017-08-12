package com.sendtion.xrichtextdemo.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.sendtion.xrichtextdemo.R;
import com.sendtion.xrichtextdemo.adapter.MyNoteListAdapter;
import com.sendtion.xrichtextdemo.bean.Note;
import com.sendtion.xrichtextdemo.db.NoteDao;
import com.sendtion.xrichtextdemo.view.SpacesItemDecoration;

import java.util.List;

/**
 * 作者：Sendtion on 2016/10/21 0021 16:43
 * 邮箱：sendtion@163.com
 * 博客：http://sendtion.cn
 * 描述：主界面
 */

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private XRecyclerView rv_list_main;
    private MyNoteListAdapter mNoteListAdapter;
    private List<Note> noteList;
    private NoteDao noteDao;
    private int groupId;//分类ID
    private String groupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_main);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "新建笔记", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                intent.putExtra("groupName", groupName);
                intent.putExtra("flag", 0);
                startActivity(intent);
            }
        });

        noteDao = new NoteDao(this);

        rv_list_main = (XRecyclerView) findViewById(R.id.rv_list_main);
        /****************** 设置XRecyclerView属性 **************************/
        rv_list_main.addItemDecoration(new SpacesItemDecoration(0));//设置item间距
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//竖向列表
        rv_list_main.setLayoutManager(layoutManager);

        rv_list_main.setLoadingMoreEnabled(true);//开启上拉加载
        rv_list_main.setPullRefreshEnabled(true);//开启下拉刷新
        rv_list_main.setRefreshProgressStyle(ProgressStyle.SquareSpin);
        rv_list_main.setLoadingMoreProgressStyle(ProgressStyle.BallScale);
        /****************** 设置XRecyclerView属性 **************************/

        mNoteListAdapter = new MyNoteListAdapter();
        mNoteListAdapter.setmNotes(noteList);
        rv_list_main.setAdapter(mNoteListAdapter);

        rv_list_main.setLoadingListener(new MainActivity.MyLoadingListener());
        mNoteListAdapter.setOnItemClickListener(new MyNoteListAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Note note) {
                showToast(note.getTitle());

                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("note", note);
                intent.putExtra("data", bundle);
                startActivity(intent);
            }
        });
        mNoteListAdapter.setOnItemLongClickListener(new MyNoteListAdapter.OnRecyclerViewItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, final Note note) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("提示");
                builder.setMessage("确定删除笔记？");
                builder.setCancelable(false);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int ret = noteDao.deleteNote(note.getId());
                        if (ret > 0){
                            showToast("删除成功");
                            refreshNoteList();
                        }
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.create().show();
            }
        });
    }

    /** 上拉加载和下拉刷新事件 **/
    private class MyLoadingListener implements XRecyclerView.LoadingListener{

        @Override
        public void onRefresh() {//下拉刷新
            rv_list_main.postDelayed(new Runnable() {
                @Override
                public void run() {
                    rv_list_main.refreshComplete();
                }
            }, 1000);
        }

        @Override
        public void onLoadMore() {//上拉加载
            rv_list_main.postDelayed(new Runnable() {
                @Override
                public void run() {
                    rv_list_main.loadMoreComplete();
                }
            }, 1000);
        }
    }

    //刷新笔记列表
    private void refreshNoteList(){
        noteList = noteDao.queryNotesAll(groupId);
        //Log.i(TAG, "###noteList: "+noteList);
        mNoteListAdapter.setmNotes(noteList);
        mNoteListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();

        refreshNoteList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new_note:
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                intent.putExtra("groupName", groupName);
                intent.putExtra("flag", 0);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
