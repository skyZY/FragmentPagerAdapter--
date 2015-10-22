package com.syp.a.fragment;

import com.syp.a.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RecordsFragment extends BaseFragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View contentView = inflater.inflate(R.layout.layout_fragment_constant, container,false);
		return contentView;
	}
	

	@Override
	public void checkData() {

	}

	@Override
	public void init_click(View v) {

	}

}
