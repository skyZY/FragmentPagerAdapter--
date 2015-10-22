package com.syp.a;

import java.util.ArrayList;
import java.util.List;

import com.syp.a.fragment.BaseFragment;
import com.syp.a.fragment.CallPhoneFragment;
import com.syp.a.fragment.RecordsFragment;
import com.syp.a.fragment.ContactFragment;
import com.syp.a.fragment.SettingsFragment;
import com.syp.a.utils.Logs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity implements OnPageChangeListener {

	private ViewPager mPagers;
	private List<BaseFragment> mContents;
	MyFragmentPageAdapter fragmentAdapter;
	private RadioGroup main_radiogroup;
	private RadioButton radio_call;
	private RadioButton radio_records;
	private RadioButton radio_settings;
	private RadioButton radio_contacts;
	private int currentpage = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findId();
	}

	void findId() {
		mPagers = (ViewPager) findViewById(R.id.pagers);
		mPagers.setOnPageChangeListener(this);

		mContents = new ArrayList<BaseFragment>();
		mContents.add(new CallPhoneFragment());
		mContents.add(new RecordsFragment());
		mContents.add(new SettingsFragment());
		mContents.add(new ContactFragment());
		
		main_radiogroup = (RadioGroup) findViewById(R.id.main_radiogroup);
		main_radiogroup.setOnCheckedChangeListener(group_listener);
		radio_call = (RadioButton) findViewById(R.id.radio_call);
		radio_records = (RadioButton) findViewById(R.id.radio_records);
		radio_settings = (RadioButton) findViewById(R.id.radio_settings);
		radio_contacts = (RadioButton) findViewById(R.id.radio_contacts);
		
		
		fragmentAdapter = new MyFragmentPageAdapter(getSupportFragmentManager(), mContents);
		mPagers.setAdapter(fragmentAdapter);
		
		

	}
	
	OnCheckedChangeListener group_listener = new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			
			if(checkedId == R.id.radio_call){
				currentpage = 0;
			}else if(checkedId == R.id.radio_records){
				currentpage = 1;
			}else if(checkedId == R.id.radio_settings){
				currentpage = 2;
			}else if(checkedId == R.id.radio_contacts){
				currentpage = 3;
			}
			Logs.i("OnCheckedChangeListener" + "->checkedId = " + checkedId + "\t currentpage = " + currentpage);
			setCurView(currentpage);
		}
	};
	
	private class MyFragmentPageAdapter extends FragmentPagerAdapter {
		private List<BaseFragment> mContents;

		public MyFragmentPageAdapter(FragmentManager fm) {
			super(fm);
		}

		public MyFragmentPageAdapter(FragmentManager fm,
				List<BaseFragment> contents) {
			super(fm);
			mContents = contents;
		}

		@Override
		public Fragment getItem(int arg0) {
			return mContents.get(arg0);
		}

		@Override
		public int getCount() {
			return mContents.size();
		}
	}

	/**
	 * set current page
	 */
	private void setCurView(int position) {
		int len = mContents.size();
		if (position < 0 || position >= len) {
			return;
		}
		mPagers.setCurrentItem(position);
		if(position == 0){
			radio_call.setChecked(true);
		}else if(position == 1){
			radio_records.setChecked(true);
		}else if(position == 2){
			radio_settings.setChecked(true);
		}else if(position == 3){
			radio_contacts.setChecked(true);
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		Logs.i("onPageScrollStateChanged" + "->arg0 = " + arg0);
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		Logs.i("onPageScrolled" + "->arg0 = " + arg0 + "\t arg1 = " + arg1 + "\t arg2 = " + arg2);
	}

	@Override
	public void onPageSelected(int position) {
		Logs.i("onPageSelected" + "->position = " + position);
		setCurView(position);
	}

}
