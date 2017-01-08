package com.example.mycustomview.custom;

import android.view.View;
import android.widget.Toast;

import com.example.mycustomview.R;
import com.xuexiang.app.BaseActivity;
import com.xuexiang.view.custom.BottomDialog;
import com.xuexiang.view.custom.BottomDialog.ActionItem;
import com.xuexiang.view.custom.BottomDialog.OnItemClickListener;

public class BottomDialogActivity extends BaseActivity {

	@Override
	public void onCreateActivity() {
		setContentView(R.layout.activity_bottomdialog);

		initTitleBar(TAG);
		
		initView();
        
	}

	private void initView() {
		findViewById(R.id.horizontal_single).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new BottomDialog(BottomDialogActivity.this)
                        .title(R.string.share_title)
                        .orientation(BottomDialog.HORIZONTAL)
                        .inflateMenu(R.menu.menu_share, new OnItemClickListener() {
                            @Override
                            public void click(ActionItem item) {
                                Toast.makeText(BottomDialogActivity.this, getString(R.string.share_title) + item.getTitle(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        findViewById(R.id.horizontal_multi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new BottomDialog(BottomDialogActivity.this)
                        .title(R.string.share_title)
                        .orientation(BottomDialog.HORIZONTAL)
                        .inflateMenu(R.menu.menu_share, new OnItemClickListener() {
                            @Override
                            public void click(ActionItem item) {
                                Toast.makeText(BottomDialogActivity.this, getString(R.string.share_title) + item.getTitle(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .inflateMenu(R.menu.menu_main, new OnItemClickListener() {
                            @Override
                            public void click(ActionItem item) {
                                Toast.makeText(BottomDialogActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        findViewById(R.id.vertical).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new BottomDialog(BottomDialogActivity.this)
                        .title(R.string.title_item)
                        .orientation(BottomDialog.VERTICAL)
                        .inflateMenu(R.menu.menu_share, new OnItemClickListener() {
                            @Override
                            public void click(ActionItem item) {
                                Toast.makeText(BottomDialogActivity.this, getString(R.string.share_title) + item.getTitle(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        findViewById(R.id.grid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new BottomDialog(BottomDialogActivity.this)
                        .title(R.string.title_item)
                        .layout(BottomDialog.GRID)
                        .orientation(BottomDialog.VERTICAL)
                        .inflateMenu(R.menu.menu_grid, new OnItemClickListener() {
                            @Override
                            public void click(ActionItem item) {
                                Toast.makeText(BottomDialogActivity.this, getString(R.string.share_title) + item.getTitle(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
		
	}

}
