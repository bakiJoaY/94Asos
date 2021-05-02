package com.example.yao.a94Asos;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class SubmitCampaignActivity extends AppCompatActivity {

    private UserLoginTask mAuthTask = null;

    private EditText mNameView;
    private EditText mScale;
    private EditText mDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_campaign);

        mNameView = (EditText)findViewById(R.id.cam_name);
        mScale = (EditText)findViewById(R.id.cam_scale);
        mDetail = (EditText)findViewById(R.id.cam_details);
    }

    public void submitCampaign(View view) {
        // Reset errors.

        Intent intent = getIntent();
        String user = intent.getStringExtra("com.example.yao.94Asos.USERNAME");

        mNameView.setError(null);
        mScale.setError(null);
        mDetail.setError(null);

        // Store values at the time of the login attempt.
        String name = mNameView.getText().toString();
        String scale = mScale.getText().toString();
        String detail = mDetail.getText().toString();

        boolean cancel = false;
        View focusView_campaign = null;

        if (TextUtils.isEmpty(name)) {
            mNameView.setError(getString(R.string.error_field_required));
            focusView_campaign = mNameView;
            cancel = true;
        }

        if (TextUtils.isEmpty(scale)) {
            mScale.setError(getString(R.string.error_field_required));
            focusView_campaign = mScale;
            cancel = true;
        }

        if (TextUtils.isEmpty(detail)) {
            mDetail.setError(getString(R.string.error_field_required));
            focusView_campaign = mDetail;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView_campaign.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mAuthTask = new UserLoginTask(name,scale,detail,user);
            mAuthTask.execute((Void) null);
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mName;
        private final String mScale;
        private final String mDetail;
        private final String mUser;


        UserLoginTask(String name,String scale,String detail,String user) {
            mName = name;
            mScale = scale;
            mDetail = detail;
            mUser = user;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            final Context context = getApplicationContext();
            final Campaign c = new Campaign();
            c.setName(mName);
            c.setScale(mScale);
            c.setDetails(mDetail);
            c.setUser(mUser);
            c.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        Toast.makeText(context, "发布成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "发布失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;

            if (success) {
                finish();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }
}
