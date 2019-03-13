package com.xiangrong.yunyang.indexscanicon.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiangrong.yunyang.indexscanicon.R;

/**
 * 作者    yunyang
 * 时间    2019/3/13 10:34
 * 文件    YunyangBlogDemo
 * 描述   示例Fragment
 */
public class ExampleFragment extends Fragment {

    private static final String SECTION_STRING = "fragment_string";

    public static ExampleFragment newInstance(String sectionNumber) {
        ExampleFragment fragment = new ExampleFragment();
        Bundle args = new Bundle();
        args.putString(SECTION_STRING, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_example, container, false);
        TextView textView = (TextView) view.findViewById(R.id.index_bottom_bar_fragment_example);
        textView.setText(getString(R.string.fragment_example_string, getArguments().getString(SECTION_STRING)));
        return view;
    }
}
