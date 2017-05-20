package com.wildlifeoftianjin.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.wildlifeoftianjin.R
import com.wildlifeoftianjin.model.CreatureOverview
import com.wildlifeoftianjin.ui.fragment.homepage.SearchFragment

/**
 * Created by presisco on 2017/5/20.
 */

class SelectCreatureActivity : NetworkActivity() {
    companion object {
        val KEY_CREATURE_ID = "creature_id"
        val KEY_CREATURE_NAME = "creature_name"
    }

    private var mSearchFragment: SearchFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_frame_only)
        mSearchFragment = SearchFragment()
        mSearchFragment!!.setOnCreatureListener(onCreature)
        val trans = supportFragmentManager.beginTransaction()
        trans.replace(R.id.content, mSearchFragment)
        trans.commit()
    }

    private val onCreature = {
        overview: CreatureOverview ->
        setResult(Activity.RESULT_OK, Intent()
                .putExtra(KEY_CREATURE_ID, overview.id)
                .putExtra(KEY_CREATURE_NAME, overview.name))
        finish()
    }

}
