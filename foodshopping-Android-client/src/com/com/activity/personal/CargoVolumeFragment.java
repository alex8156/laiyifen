package com.com.activity.personal;

import com.com.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CargoVolumeFragment extends Fragment {
	View mView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		super.onCreateView(inflater, container, savedInstanceState);
		mView=inflater.inflate(R.layout.listview2, container, false);
		return mView;
	}
}
