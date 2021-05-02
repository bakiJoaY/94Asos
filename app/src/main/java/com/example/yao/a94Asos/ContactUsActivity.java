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

public class ContactUsActivity extends AppCompatActivity {

    private ContactUsActivity.UserLoginTask mAuthTask = null;
    private EditText mTelView;
    private EditText mDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        mTelView = (EditText)findViewById(R.id.contact_tel);
        mDetails = (EditText)findViewById(R.id.contact_details);
    }

    public void contactUs(View view) {
        // Reset errors.

        Intent intent = getIntent();
        String user = intent.getStringExtra("com.example.yao.94Asos.USERNAME");

        mTelView.setError(null);
        mDetails.setError(null);

        // Store values at the time of the login attempt.
        String tel = mTelView.getText().toString();
        String details = mDetails.getText().toString();

        boolean cancel = false;
        View focusView_contactUs = null;

        if (TextUtils.isEmpty(tel)) {
            mTelView.setError(getString(R.string.error_field_required));
            focusView_contactUs = mTelView;
            cancel = true;
        }

        if (TextUtils.isEmpty(details)) {
            mDetails.setError(getString(R.string.error_field_required));
            focusView_contactUs = mDetails;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView_contactUs.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mAuthTask = new ContactUsActivity.UserLoginTask(tel,details,user);
            mAuthTask.execute((Void) null);
        }
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mTel;
        private final String mDetails;
        private final String mUser;


        UserLoginTask(String tel,String details,String user) {
            mTel = tel;
            mDetails = details;
            mUser = user;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            final Context context = getApplicationContext();
            final Need c = new Need();
            c.setTel(mTel);
            c.setDetails(mDetails);
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
