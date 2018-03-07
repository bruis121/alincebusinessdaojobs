package com.fangfas.alincebusinessdaojobs.Baseadapter;

import android.app.Activity;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.android.volley.toolbox.StringRequest;
import com.fangfas.alincebusinessdaojobs.Json.Jobbean;
import com.fangfas.alincebusinessdaojobs.R;
import com.fangfas.alincebusinessdaojobs.Tool.ListViewHeight;
import com.fangfas.alincebusinessdaojobs.View.SlideList.SlideView;
import com.fangfas.alincebusinessdaojobs.Httprequest.RequestJobEXperience;

import java.util.ArrayList;

public class AuthenJobAdapter extends BaseAdapter implements
		SlideView.OnSlideListener {
	private static final String TAG = "SlideAdapter";

	private Activity mContext;
	private LayoutInflater mInflater;
	private  ListView list_job;

	private ArrayList<Jobbean.Res> mMessageItems = new ArrayList<Jobbean.Res>();
	private SlideView mLastSlideViewWithStatusOn;
	private   Handler hander;
	private  StringRequest stringRequest;

	public AuthenJobAdapter(StringRequest stringRequest,Activity context, ListView list_job, Handler hander) {
		mContext = context;
		mInflater = LayoutInflater.from(mContext);
		this.list_job=list_job;
		this.hander=hander;
		this.stringRequest=stringRequest;
	}

	public void setmMessageItems(ArrayList<Jobbean.Res> mMessageItems) {
		this.mMessageItems = mMessageItems;
	}

	@Override
	public int getCount() {
		if (mMessageItems == null) {
			mMessageItems = new ArrayList<Jobbean.Res>();
		}
		return mMessageItems.size();
	}

	@Override
	public Object getItem(int position) {
		return mMessageItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		SlideView slideView = (SlideView) convertView;
		if (slideView == null) {
			View itemView = mInflater.inflate(R.layout.athenjob_item,
					null);

			slideView = new SlideView(mContext);
			slideView.setContentView(itemView);
			holder = new ViewHolder(slideView);
			slideView.setOnSlideListener(this);
			slideView.setTag(holder);
		} else {
			holder = (ViewHolder) slideView.getTag();
		}
		final Jobbean.Res item = mMessageItems.get(position);
		item.slideView = slideView;
		item.slideView.shrink();


		holder.time.setText(item.yearbegin+"~"+item.yearend);
		holder.cats.setText(item.cats);
		holder.summary.setText(item.summary);
		holder.deleteHolder.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mMessageItems.remove(position);
				new ListViewHeight(list_job);
				notifyDataSetChanged();
				new RequestJobEXperience(stringRequest,mContext,hander).delete(item.wid);


			}
		});

		return slideView;
	}

	private static class ViewHolder {
		public TextView summary;
		public TextView cats;
		public TextView time;
		public ViewGroup deleteHolder;

		ViewHolder(View view) {
			time = (TextView) view.findViewById(R.id.time);
			cats = (TextView) view.findViewById(R.id.cats);
			summary = (TextView) view.findViewById(R.id.summary);
			deleteHolder = (ViewGroup) view.findViewById(R.id.holder);
		}
	}

	@Override
	public void onSlide(View view, int status) {
		if (mLastSlideViewWithStatusOn != null
				&& mLastSlideViewWithStatusOn != view) {
			mLastSlideViewWithStatusOn.shrink();
		}

		if (status == SLIDE_STATUS_ON) {
			mLastSlideViewWithStatusOn = (SlideView) view;
		}
	}
}
