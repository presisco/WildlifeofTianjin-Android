package com.wildlifeoftianjin.ui.activity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wildlifeoftianjin.R;
import com.wildlifeoftianjin.network.VolleyManager;
import com.wildlifeoftianjin.ui.fragment.DiscoverFragment;
import com.wildlifeoftianjin.ui.fragment.PersonalFragment;
import com.wildlifeoftianjin.ui.fragment.SearchFragment;
import com.wildlifeoftianjin.ui.fragment.UploadRecordFragment;
import com.wildlifeoftianjin.ui.framework.clicktabslayout.ClickTabsFramework;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private ClickTabsFramework mFramework = null;
    private List<ContentPage> mContentFragments = null;
    private Resources mResources;
    private int mTabColorDefault = 0;
    private int mTabColorChosen = 0;

    private void prepareContentFragments() {
        mContentFragments = new ArrayList<>();

        mContentFragments.add(
                new ContentPage(
                        SearchFragment.newInstance(),
                        mResources.getString(R.string.tab_title_search),
                        mResources.getDrawable(R.drawable.ic_tab_search_default),
                        mResources.getDrawable(R.drawable.ic_tab_search_chosen)
                )
        );

        mContentFragments.add(
                new ContentPage(
                        UploadRecordFragment.newInstance(),
                        mResources.getString(R.string.tab_title_upload),
                        mResources.getDrawable(R.drawable.ic_tab_upload_default),
                        mResources.getDrawable(R.drawable.ic_tab_upload_chosen)
                )
        );

        mContentFragments.add(
                new ContentPage(
                        DiscoverFragment.newInstance(),
                        mResources.getString(R.string.tab_title_discover),
                        mResources.getDrawable(R.drawable.ic_tab_discover_default),
                        mResources.getDrawable(R.drawable.ic_tab_discover_chosen))
        );

        mContentFragments.add(
                new ContentPage(
                        PersonalFragment.newInstance(),
                        mResources.getString(R.string.tab_title_personal),
                        mResources.getDrawable(R.drawable.ic_tab_personal_default),
                        mResources.getDrawable(R.drawable.ic_tab_personal_chosen))
        );
    }

    private void prepareRes() {
        mResources = getResources();
        mTabColorDefault = mResources.getColor(R.color.click_tabs_layout_tabs_default_color);
        mTabColorChosen = mResources.getColor(R.color.click_tabs_layout_tabs_selected_color);
    }

    private List<Fragment> getContentFragments() {
        List<Fragment> fragments = new ArrayList<>();
        for (ContentPage page : mContentFragments)
            fragments.add(page.contentFragment);
        return fragments;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        VolleyManager.init(getApplicationContext());

        setContentView(R.layout.activity_main);
        prepareRes();
        prepareContentFragments();

        mFramework = new ClickTabsFramework();
        mFramework.setContentItems(getContentFragments());
        mFramework.setDistributeEvenly(true);
        mFramework.setTabLayout(R.layout.click_tabs_item);
        mFramework.setCustomTabDraw(new CustomTabDraw());

        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.clickTabsLayout, mFramework);
        trans.commit();
    }

    private class ContentPage {
        public final Fragment contentFragment;
        public final String title;
        public final Drawable default_icon;
        public final Drawable chosen_icon;

        public ContentPage(Fragment fragment, String _title, Drawable _default_icon, Drawable _chosen_icon) {
            contentFragment = fragment;
            title = _title;
            default_icon = _default_icon;
            chosen_icon = _chosen_icon;
        }
    }

    private class CustomTabDraw implements ClickTabsFramework.TabDraw {
        @Override
        public void initDraw(View v, int pos) {
            Log.d(TAG, "initDraw: for " + pos);
            v.setBackgroundColor(mResources.getColor(R.color.click_tabs_layout_tabs_default_background_color));
            ((TextView) v.findViewById(R.id.tabTitle))
                    .setText(
                            mContentFragments.get(pos).title);
            ((ImageView) v.findViewById(R.id.tabIcon))
                    .setImageDrawable(
                            mContentFragments.get(pos).default_icon);
        }

        /**
         * call when tab@pos is clicked
         *
         * @param last    view of last clicked tab
         * @param lastpos pos of last clicked tab
         * @param now     view of clicked tab
         * @param pos     pos of clicked tab
         */
        @Override
        public void onClickedDraw(View last, int lastpos, View now, int pos) {
            if (last != null && lastpos != -1) {
                ((TextView) last.findViewById(R.id.tabTitle))
                        .setTextColor(mTabColorDefault);
                ((ImageView) last.findViewById(R.id.tabIcon))
                        .setImageDrawable(
                                mContentFragments.get(lastpos).default_icon);
            }

            ((TextView) now.findViewById(R.id.tabTitle))
                    .setTextColor(mTabColorChosen);
            ((ImageView) now.findViewById(R.id.tabIcon))
                    .setImageDrawable(
                            mContentFragments.get(pos).chosen_icon);
        }
    }
}
