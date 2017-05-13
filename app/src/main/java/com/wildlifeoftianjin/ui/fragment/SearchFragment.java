package com.wildlifeoftianjin.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.wildlifeoftianjin.R;
import com.wildlifeoftianjin.data.SearchHistoryHelper;
import com.wildlifeoftianjin.model.CreatureOverview;
import com.wildlifeoftianjin.network.Request.BaseRequest;
import com.wildlifeoftianjin.ui.framework.adapter.CreatureOverviewAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    private CreatureOverviewAdapter mCreatureViewAdapter;
    private SearchView mSearchView;
    private List<CreatureOverview> mCreatureOverviews = null;
    private SearchHistoryHelper mSearchHelper;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SearchFragment.
     */
    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSearchHelper = new SearchHistoryHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search_home, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.frontPageList);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mCreatureOverviews = new LinkedList<>();
        mCreatureViewAdapter = new CreatureOverviewAdapter();
        mCreatureViewAdapter.setDataSet(mCreatureOverviews);
        recyclerView.setAdapter(mCreatureViewAdapter);

        mSearchView = (SearchView) rootView.findViewById(R.id.searchView);

        return rootView;
    }

    public class OnFrontPageListLoadCompleteListener implements BaseRequest.OnParseCompleteListener<List<CreatureOverview>> {
        @Override
        public void onParseComplete(List<CreatureOverview> result) {
            mCreatureViewAdapter.setDataSet(result);
            mCreatureViewAdapter.notifyDataSetChanged();
        }
    }
}
