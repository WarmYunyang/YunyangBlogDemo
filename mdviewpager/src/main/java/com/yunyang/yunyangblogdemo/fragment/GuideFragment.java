package com.yunyang.yunyangblogdemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.yunyang.yunyangblogdemo.R;

/**
 * 作者    yunyang
 * 时间    2018/11/13 11:23
 * 文件    YunyangBlogDemo
 * 描述   引导页ViewPager中Fragment
 */
public class GuideFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "guide_number";

    private ImageView mImageView;

    private int[] bgs = new int[]{
            R.drawable.ic_like_24dp,
            R.drawable.ic_house_24dp,
            R.drawable.ic_helper_24dp};

    public static GuideFragment newInstance(int sectionNumber) {
        GuideFragment fragment = new GuideFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager, container, false);
        TextView textView = (TextView) view.findViewById(R.id.fragment_pager_text_label);
        textView.setText(getString(R.string.guide_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        mImageView = (ImageView) view.findViewById(R.id.fragment_pager_img);
        mImageView.setBackgroundResource(bgs[getArguments().getInt(ARG_SECTION_NUMBER) - 1]);
        return view;
    }

}
