package com.wildlifeoftianjin.ui.activity

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Toast
import com.wildlifeoftianjin.R
import com.wildlifeoftianjin.network.Constants
import com.wildlifeoftianjin.network.Request.PostFormRequest
import com.wildlifeoftianjin.utils.ImageUtils
import kotlinx.android.synthetic.main.activity_login.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : NetworkActivity() {

    private var mode = MODE_SIGN_IN

    private var mPreferences: SharedPreferences? = null

    infix fun pop(content: String) {
        Toast.makeText(this@LoginActivity, content, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        headshotLayout.visibility = View.INVISIBLE
        usernameLayout.visibility = View.GONE
        repeatLayout.visibility = View.GONE
        mPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
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
            usernameLayout.visibility = View.VISIBLE
            mode = MODE_SIGN_UP
        } else if (editPassword.text.toString()
                .equals(editRepeat.text.toString())) {
            val form = hashMapOf<String, String>()

            form.put("username", editUsername.text.toString())
            form.put("telephone", editPhoneNum.text.toString())
            form.put("pwd", editPassword.text.toString())

            requestQueue.add(PostFormRequest(Constants.PATH_SIGN_UP, form, onResponse, this))

            showLoadingIndicator()
        } else {
            pop("password dont match")
        }
    }

    fun onSignIn(v: View) {
        if (mode == MODE_SIGN_UP) {
            headshotLayout.visibility = View.INVISIBLE
            repeatLayout.visibility = View.GONE
            usernameLayout.visibility = View.GONE
            mode = MODE_SIGN_IN
        } else {
            val form = hashMapOf<String, String>()

            form.put("telephone", editPhoneNum.text.toString())
            form.put("pwd", editPassword.text.toString())

            requestQueue.add(PostFormRequest(Constants.PATH_SIGN_IN, form, onResponse, this))

            showLoadingIndicator()
        }
    }

    private val onResponse = {
        result: Map<String, String> ->
        when (result["status"]) {
            "-1" -> pop("user not exist")
            "0" -> pop("failed")
            "1" -> {
                setResult(Activity.RESULT_OK)
                val editor = mPreferences!!.edit()
                with(editor) {
                    putString("user_id", result["id"])
                    putString("user_name", result["name"])
                    putString("telephone", result["telephone"])
                    commit()
                }
                finish()
            }
        }
        hideLoadingIndicator()
    }

    companion object {
        private val REQUEST_CODE_PICK_HEADSHOT = 200
        private val MODE_SIGN_IN = 1
        private val MODE_SIGN_UP = 2
    }

}

