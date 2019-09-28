package com.yunyang.guidepageslidingintro.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yunyang.guidepageslidingintro.R;

/**
 * Created by YunYang.
 * Date: 2019/9/28
 * Time: 13:21
 * Des: ViewPager+Fragment
 */
public class EmptyFragment extends Fragment {

    public EmptyFragment() {
    }

    public static EmptyFragment newInstance() {
        EmptyFragment fragment = new EmptyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_empty_vp, container, false);
    }

}
