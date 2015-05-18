package com.two_two.offshoreview.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.two_two.offshoreview.R;
import com.two_two.offshoreview.tabs.SlidingTabLayout;

/**
 * Created by marazm on 13.05.2015.
 */
public class FragmentEmoneyView extends Fragment {

    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

    public FragmentEmoneyView(){

    }


    private String[] category = {"Новости", "Статьи", "Банки и платежные системы", "Криптовалюты",
            "Форекс, трейдинг, биржи", "Интернет"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blog_with_tabs, container, false);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.name_fragment_emoney);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {

        mViewPager = (ViewPager) view.findViewById(R.id.viewPagerBlogs);
        mViewPager.setAdapter(new MyPagerAdapter());

        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.slidingTabBlogs);
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return category.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return object == view;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return category[position];
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_tabs, container, false);
            container.addView(view);



            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }

}
