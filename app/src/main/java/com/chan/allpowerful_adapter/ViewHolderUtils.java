package com.chan.allpowerful_adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Chan on 2016/5/20.
 *
 * 通用的ViewHolder,封装了平常写自定义Adapter中复用的getView中的代码,并抽取出一些常用组件如TextView,
 * ImageView的设置方法,大大简化调用时的代码
 */
public class ViewHolderUtils {

	private Context mContext;
	private int mPosition;
	private View mConvertView;
	// 类似于Map,保存键值对,Key为int,value为object,效率比Map高
	private SparseArray<View> mSparseArray;

	public ViewHolderUtils(Context context, int position, View convertView,
			ViewGroup parent, int layoutId) {
		this.mContext = context;
		this.mPosition = position;
		this.mConvertView = convertView;
		mSparseArray = new SparseArray<>();

		convertView.setTag(this);
	}

	/**
	 * 入口方法:封装了判断convertView是否为空的判断,如果convertView
	 * 为空则创建一个ViewHolderUtils对象，在ViewHolderUtils构造方法里加载convertView的布局
	 * ,如果convertView 不为空则通过convertView.getTag(),返回viewHolder。
	 * 可以把这个方法看做是在这个ViewHolderUtils对BaseAdapter的getView()内部操作的封装抽取 并在万能适配器
	 * CommonAdapter 中的getView()中使用
	 * 
	 * @param mContext
	 * @param position
	 * @param convertView
	 * @param parent
	 * @param layoutId
	 * @return
	 */
	public static ViewHolderUtils get(Context mContext, int position,
			View convertView, ViewGroup parent, int layoutId) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(layoutId,
					parent, false);
			return new ViewHolderUtils(mContext, position, convertView, parent,
					layoutId);
		} else {
			ViewHolderUtils holder = (ViewHolderUtils) convertView.getTag();
			return holder;
		}
	}

	/**
	 * 获取ViewHolder中存入的各种控件的实例，获取后强转为具体的控件类
	 * 
	 * @param resId
	 *            控件的ID
	 * @param <T>
	 * @return View的泛型
	 */
	public <T extends View> T getSingleView(int resId) {
		View view = mSparseArray.get(resId);

		if (view == null) {
			view = mConvertView.findViewById(resId);
			mSparseArray.put(resId, view);
		}

		return (T) view;
	}

	/**
	 * 设置viewHolder中的TextView
	 *
	 * @param id
	 *            要设置TextView控件的id,用于在mSparseArray中找到控件
	 * @param text
	 *            要设置的文字
	 */
	public void setViewHolderText(int id, String text) {
		TextView tv = getSingleView(id);
		tv.setText(text);
	}

	/**
	 * 获取一顿操作后的convertView
	 * 
	 * @return
	 */
	public View getConvertView() {
		return mConvertView;
	}
}
