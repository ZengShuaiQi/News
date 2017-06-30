package fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.cleverboy.news.R;

import java.util.ArrayList;
import java.util.List;

import base.BaseFragment;

/**
 * Created by clever boy on 2017/6/27.
 */

public class MainFragment1 extends BaseFragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_main_01;
    }

    @Override
    public void initView() {
        mTabLayout = (TabLayout) mRootView.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) mRootView.findViewById(R.id.view_pager_02);
        initViewPager();
    }

    private void initViewPager() {
        final String[] titles = new String[]{"头条", "社会"
                , "科技", "财经", "体育", "汽车"};
        final String[] channelId = new String[] {
                "T1348647909107",
                "T1348648037603",
                "T1348649580692",
                "T1348648756099",
                "T1348649079062",
                "T1348654060988",
        };

        final List<Fragment> fragments = new ArrayList();
        for(int i=0;i<titles.length;i++){
            NewsFragment newsFragment = new NewsFragment();
            newsFragment.setChannelId(channelId[i]);
            fragments.add(newsFragment);
        }

        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }
}
