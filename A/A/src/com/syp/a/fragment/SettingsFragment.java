package com.syp.a.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.syp.a.R;
import com.syp.a.utils.Logs;

public class SettingsFragment extends BaseFragment {
	
	private Activity mActivity;
	private ExpandableListView expandlist;
	MyExpandableListViewAdapter expandAdapter;
	
	private String[] list_first = new String[]{"基本设置","音视频设置","协议设置","诊断维护"};
	
	private Integer[] list_icon = new Integer[]{R.drawable.base_setting,R.drawable.video_setting,R.drawable.protocol_setting,R.drawable.check_setting};
	
	private String[][] list_second = new String[][]{{"一级一", "一级二", "一级三", "一级四"},{"二级一", "二级二", "二级三", "二级四", "二级五"},{"三级一", "三级二", "三级三", "三级四"},{"四级一", "四级二", "四级三", "四级四", "四级五", "四级六"}};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View convertView = inflater.inflate(R.layout.layout_fragment_settings, container, false);
		
		expandlist = (ExpandableListView) convertView.findViewById(R.id.expendlist);
		expandAdapter = new MyExpandableListViewAdapter();
		expandlist.setAdapter(expandAdapter);
		expandlist.setOnChildClickListener(child_listener);
		expandlist.setOnGroupClickListener(group_listener);
		return convertView;
	}
	
	OnChildClickListener child_listener = new OnChildClickListener() {
		
		@Override
		public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
			Toast.makeText(mActivity, "OnChildClickListener->" + "点击了" + groupPosition + " 的 " + childPosition, Toast.LENGTH_SHORT);
			Logs.i("OnChildClickListener->" + "groupPosition = " + groupPosition + "\t childPosition = " + childPosition);
			return false;
		}
	};
	
	OnGroupClickListener group_listener = new OnGroupClickListener() {
		
		@Override
		public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
		Toast.makeText(mActivity, "OnChildClickListener->" + "点击了" + groupPosition, Toast.LENGTH_SHORT);
		Logs.i("OnGroupClickListener->" + "groupPosition = " + groupPosition );
			return false;
		}
	};

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mActivity = activity;
	}



	@Override
	public void checkData() {
		
	}

	@Override
	public void init_click(View v) {
		
	}
	
	
	
	class MyExpandableListViewAdapter extends BaseExpandableListAdapter {
			 
		@Override
		public int getGroupCount() {
			return list_first.length;
		}
		 
		@Override
		public int getChildrenCount(int groupPosition) {
			return list_second[groupPosition].length;
		}
		 
		@Override
		public Object getGroup(int groupPosition) {
			return list_first[groupPosition];
		}
		 
		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return list_second[groupPosition][childPosition];
		}
		 
		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}
		 
		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}
		 
		@Override
		public boolean hasStableIds() {
			return true;
		}
		 
		@Override
		public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
			Logs.i("getGroupView->" + "groupPosition = " + groupPosition );
			GroupHolder groupHolder = null;
		    if (convertView == null) {convertView = (View) mActivity.getLayoutInflater().from(mActivity).inflate(R.layout.item_expand_first, null);
			    groupHolder = new GroupHolder();
			    groupHolder.group_text = (TextView) convertView.findViewById(R.id.expand_first_title);
			    groupHolder.group_img = (ImageView) convertView.findViewById(R.id.expand_first_icon);
			    convertView.setTag(groupHolder);
		   	} else {
		    	groupHolder = (GroupHolder) convertView.getTag();
		    }
		   	groupHolder.group_text.setText(list_first[groupPosition]);
		   	groupHolder.group_img.setImageResource(list_icon[groupPosition]);
		   	return convertView;
	  }
	 
	  @Override
	  public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
  		  Logs.i("getChildView->" + "groupPosition = " + groupPosition + "\t childPosition = " + childPosition);
		  ItemHolder itemHolder = null;
		  if (convertView == null) {
			  convertView = (View) mActivity.getLayoutInflater().from(mActivity).inflate(R.layout.item_expand_second, null);
			  itemHolder = new ItemHolder();
			  itemHolder.item_txt = (TextView) convertView.findViewById(R.id.expand_second_title);
			  itemHolder.item_img = (ImageView) convertView.findViewById(R.id.expand_second_icon);
			  convertView.setTag(itemHolder);
		  } else {
		      itemHolder = (ItemHolder) convertView.getTag();
		  }
		  itemHolder.item_txt.setText(list_second[groupPosition][childPosition]);
		  itemHolder.item_img.setBackgroundResource(R.drawable.disclosureindicator);
		  return convertView;
		}
		 
		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
		   return true;
	  	}
	 
	}
	
	class GroupHolder {
		public TextView group_text;
		public ImageView group_img;
	}
		 
	class ItemHolder {
		public ImageView item_img;
		public TextView item_txt;
	}

}
