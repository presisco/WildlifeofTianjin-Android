package com.wildlifeoftianjin.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.wildlifeoftianjin.R
import com.wildlifeoftianjin.utils.ImageUtils
import kotlinx.android.synthetic.main.activity_login.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : NetworkActivity() {

    private var mode = MODE_SIGN_IN

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        headshotLayout.visibility = View.INVISIBLE
        repeatLayout.visibility = View.GONE
    }

    fun onPickHeadShot(v: View) {
        startActivityForResult(Intent(Intent.ACTION_GET_CONTENT).setType("image/*"), REQUEST_CODE_PICK_HEADSHOT)
    }

    /**
     * Dispatch incoming result to the correct fragment.

     * @param requestCode
     * *
     * @param resultCode
     * *
     * @param data
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PICK_HEADSHOT) {
            if (resultCode == Activity.RESULT_OK) {
                imageHeadShot.setImageBitmap(ImageUtils.getThumbnail(this, data.data, 512, 512))
            }
        }
    }

    fun onBack(v: View) {
        finish()
    }

    fun onSignUp(v: View) {
        if (mode == MODE_SIGN_IN) {
            headshotLayout.visibility = View.VISIBLE
            repeatLayout.visibility = View.VISIBLE

            mode = MODE_SIGN_UP
        } else {

        }
    }

    fun onSignIn(v: View) {
        if (mode == MODE_SIGN_UP) {
            headshotLayout.visibility = View.INVISIBLE
            repeatLayout.visibility = View.GONE
            mode = MODE_SIGN_IN
        } else {

        }
    }

    companion object {
        private val REQUEST_CODE_PICK_HEADSHOT = 200
        private val MODE_SIGN_IN = 1
        private val MODE_SIGN_UP = 2
    }

}

