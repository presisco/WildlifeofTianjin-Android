package com.wildlifeoftianjin.ui.fragment.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.android.volley.VolleyError;
import com.wildlifeoftianjin.R;
import com.wildlifeoftianjin.data.SearchHistoryHelper;
import com.wildlifeoftianjin.model.CreatureOverview;
import com.wildlifeoftianjin.network.Request.ListRequest;
import com.wildlifeoftianjin.network.Request.SearchCreatureRequest;
import com.wildlifeoftianjin.ui.activity.CreatureDetailActivity;
import com.wildlifeoftianjin.ui.activity.SearchFilterActivity;
import com.wildlifeoftianjin.ui.fragment.NetworkFragment;
import com.wildlifeoftianjin.ui.framework.recyclerview.CreatureListAdapter;
import com.wildlifeoftianjin.ui.framework.recyclerview.DragAppendAdapter;
import com.wildlifeoftianjin.ui.framework.recyclerview.SearchHistoryAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends NetworkFragment
        implements ListRequest.ListResponse<CreatureOverview>
        , DragAppendAdapter.FooterListener
        , CreatureListAdapter.CreatureListener
        , SwipeRefreshLayout.OnRefreshListener
        , SearchView.OnQueryTextListener {

    private static final int REQUEST_CODE_FILTER = 1;

    private CreatureListAdapter mCreatureViewAdapter;
    private SearchView mSearchView;
    private SearchHistoryHelper mSearchHelper;
    private SwipeRefreshLayout mSwipeRefresh;

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

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.searchResultPage);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mCreatureViewAdapter = new CreatureListAdapter(getContext(), this, this);
        mCreatureViewAdapter.setShowingFooter(false);
        recyclerView.setAdapter(mCreatureViewAdapter);

        mSearchView = (SearchView) rootView.findViewById(R.id.searchView);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSuggestionsAdapter(
                new SearchHistoryAdapter(
                        getContext(),
                        mSearchHelper.getRecentHistoryCursor(),
                        android.widget.CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER));

        mSwipeRefresh = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefresh);
        mSwipeRefresh.setOnRefreshListener(this);

        rootView.findViewById(R.id.buttonFilter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), SearchFilterActivity.class), REQUEST_CODE_FILTER);
            }
        });

        return rootView;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        getRequestQueue().add(new SearchCreatureRequest(query, this, this));
        mSearchHelper.addHistory(query);
        mSwipeRefresh.setRefreshing(true);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        return true;
    }

    @Override
    public void onClickCreature(int position) {
        startActivity(
                new Intent(getContext(), CreatureDetailActivity.class)
                        .putExtra(
                                CreatureDetailActivity.KEY_CREATURE_ID
                                , mCreatureViewAdapter
                                        .getItem(position)
                                        .getID()));
    }

    @Override
    public void onShowingFooter() {

    }

    @Override
    public void onRefresh() {
        mSwipeRefresh.setRefreshing(true);
        getRequestQueue().add(new SearchCreatureRequest("", this, this));
    }

    @Override
    public void onResponse(List<CreatureOverview> response) {
        mCreatureViewAdapter.setDataSet(response);
        mCreatureViewAdapter.notifyDataSetChanged();
        mSwipeRefresh.setRefreshing(false);
    }

    /**
     * Callback method that an error has been occurred with the
     * provided error code and optional user-readable message.
     *
     * @param error
     */
    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
        mSwipeRefresh.setRefreshing(false);
    }

}
