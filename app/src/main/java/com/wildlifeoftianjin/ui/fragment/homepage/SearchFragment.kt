package com.wildlifeoftianjin.ui.fragment.homepage

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import com.android.volley.VolleyError
import com.wildlifeoftianjin.R
import com.wildlifeoftianjin.data.SearchHistoryHelper
import com.wildlifeoftianjin.model.CreatureOverview
import com.wildlifeoftianjin.network.Request.ListRequest
import com.wildlifeoftianjin.network.Request.SearchCreatureRequest
import com.wildlifeoftianjin.ui.activity.CreatureDetailActivity
import com.wildlifeoftianjin.ui.activity.SearchFilterActivity
import com.wildlifeoftianjin.ui.fragment.NetworkFragment
import com.wildlifeoftianjin.ui.framework.recyclerview.CreatureListAdapter
import com.wildlifeoftianjin.ui.framework.recyclerview.SearchHistoryAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : NetworkFragment(), ListRequest.ListResponse<CreatureOverview>, SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener {

    private var mCreatureViewAdapter: CreatureListAdapter? = null
    private var mSearchHelper: SearchHistoryHelper? = null

    private var mSwipeRefresh: SwipeRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSearchHelper = SearchHistoryHelper(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater!!.inflate(R.layout.fragment_search_home, container, false)

        val recyclerView = rootView.findViewById(R.id.searchResultPage) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        mCreatureViewAdapter = CreatureListAdapter(context, onShowingFooter, onClickCreature)
        mCreatureViewAdapter!!.setShowingFooter(false)
        recyclerView.adapter = mCreatureViewAdapter

        val searchView = rootView.findViewById(R.id.searchView) as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)
        searchView.suggestionsAdapter = SearchHistoryAdapter(
                context,
                mSearchHelper!!.recentHistoryCursor,
                android.widget.CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER)

        mSwipeRefresh = rootView.findViewById(R.id.swipeRefresh) as SwipeRefreshLayout
        mSwipeRefresh!!.setOnRefreshListener(this)

        rootView.findViewById(R.id.buttonFilter).setOnClickListener { startActivityForResult(Intent(activity, SearchFilterActivity::class.java), REQUEST_CODE_FILTER) }

        return rootView
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        requestQueue.add(SearchCreatureRequest(query, this, this))
        mSearchHelper!!.addHistory(query)
        mSwipeRefresh!!.isRefreshing = true
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {

        return true
    }

    private val onClickCreature = {
        position: Int ->
        startActivity(
                Intent(context, CreatureDetailActivity::class.java)
                        .putExtra(
                                CreatureDetailActivity.KEY_CREATURE_ID, mCreatureViewAdapter!!
                                .getItem(position)
                                .id))
    }

    private val onShowingFooter = {

    }

    override fun onRefresh() {
        mSwipeRefresh!!.isRefreshing = true
        requestQueue.add(SearchCreatureRequest("", this, this))
    }

    override fun onResponse(response: MutableList<CreatureOverview>) {
        mCreatureViewAdapter!!.setDataSet(response)
        mCreatureViewAdapter!!.notifyDataSetChanged()
        mSwipeRefresh!!.isRefreshing = false
    }

    /**
     * Callback method that an error has been occurred with the
     * provided error code and optional user-readable message.

     * @param error
     */
    override fun onErrorResponse(error: VolleyError) {
        super.onErrorResponse(error)
        mSwipeRefresh!!.isRefreshing = false
    }

    companion object {

        private val REQUEST_CODE_FILTER = 1

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @return A new instance of fragment SearchFragment.
         */
        fun newInstance(): SearchFragment {
            val fragment = SearchFragment()
            return fragment
        }
    }

}// Required empty public constructor
