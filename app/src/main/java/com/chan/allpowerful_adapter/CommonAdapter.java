package com.chan.allpowerful_adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Chan on 2016/5/20.
 *
 * 万能适配器,封装了一般自定义Adapter的getCount(),getItem(),getItemId(),getView()方法。
 * 使用者只需要实现current()方法就可以在getView中进行操作.其中包含了ViewHolderUtils万能的ViewHolder.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

	protected List<T> data;
	protected Context mContext;
	protected int layoutId;

	/**
	 * @param context
	 * @param data
	 *            一个泛型集合,为了让使用者使用不同内容的包装好的Bean
	 * @param layoutId
	 *            每个列表项的布局id
	 *
	 */
	public CommonAdapter(Context context, List<T> data, int layoutId) {
		this.data = data;
		mContext = context;
		this.layoutId = layoutId;
	}

	/*
	 * 封装了原自定义Adapter要实现的方法, 不管写何种的ListView，其实都是对数据集合进行操作,所以使用了泛型data
	 */

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public T getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * 封装子Adapter要重写的getView(),子Adapter就不需要再去重写getView而编写重复的代码。在getView()
	 * 中使用了ViewHolderUtils
	 * ,进行内部convertView的操作,通过调用current()操作使用者具体要进行的例如:列表项控件的获取，各个控件的填入数据.
	 * 避免子Adapter重写getView()导致重复代码。
	 * 
	 * @param position
	 * @param convertView
	 * @param parent
	 * @return
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolderUtils holder = ViewHolderUtils.get(mContext, position,
				convertView, parent, layoutId);

		current(holder, getItem(position));
		return holder.getConvertView();
	}

	/**
	 * 提供给子adapter实现，通过这个方法可以获取viewHolder的控件并对控件填入数据
	 * 
	 * @param holder
	 *            getView()中使用的viewHolder
	 * @param bean
	 *            每个列表项对应的bean
	 */
	protected abstract void current(ViewHolderUtils holder, T bean);
}
