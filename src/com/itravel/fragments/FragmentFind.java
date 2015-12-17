package com.itravel.fragments;

import com.itravel.R;
import com.itravel.R.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentFind extends Fragment {
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
return inflater.inflate(R.layout.fragment_find, container, false);
}
}
