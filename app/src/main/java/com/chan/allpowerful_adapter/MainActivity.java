package com.chan.allpowerful_adapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	private ListView mListView;
	private List<Bean> mBeanList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initData(); // 初始化数据
		initView(); // 初始化视图
	}

	private void initData() {
		mBeanList = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			Bean bean = new Bean("title" + i, "谁能代表肯德基", "520", "123");
			mBeanList.add(bean);
		}
	}

	private void initView() {
		mListView = (ListView) findViewById(R.id.listView);
		mListView.setAdapter(new CommonAdapter<Bean>(this, mBeanList,
				R.layout.my_item) {
			@Override
			protected void current(ViewHolderUtils holder, Bean bean) {
				holder.setViewHolderText(R.id.title, bean.getTitle());
				holder.setViewHolderText(R.id.content, bean.getContent());
				holder.setViewHolderText(R.id.phone, bean.getPhone());
				holder.setViewHolderText(R.id.time, bean.getTime());
			}
		});
	}

}
