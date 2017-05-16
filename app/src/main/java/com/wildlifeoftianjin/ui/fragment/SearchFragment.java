package com.wildlifeoftianjin.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.wildlifeoftianjin.R;
import com.wildlifeoftianjin.data.SearchHistoryHelper;
import com.wildlifeoftianjin.model.CreatureOverview;
import com.wildlifeoftianjin.network.Request.TaskResponse;
import com.wildlifeoftianjin.ui.framework.adapter.CreatureListAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements TaskResponse<List<CreatureOverview>>, Response.ErrorListener {
    private CreatureListAdapter mCreatureViewAdapter;
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
        mCreatureViewAdapter = new CreatureListAdapter();
        mCreatureViewAdapter.setDataSet(mCreatureOverviews);
        recyclerView.setAdapter(mCreatureViewAdapter);

        mSearchView = (SearchView) rootView.findViewById(R.id.searchView);

        return rootView;
    }

    @Override
    public void onResponse(List<CreatureOverview> response) {
        mCreatureViewAdapter.setDataSet(response);
        mCreatureViewAdapter.notifyDataSetChanged();
    }

    /**
     * Callback method that an error has been occurred with the
     * provided error code and optional user-readable message.
     *
     * @param error
     */
    @Override
    public void onErrorResponse(VolleyError error) {

    }
}
