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

import static com.example.yao.a94Asos.DetailActivity.MESSAGE_RECIPIENT;
import static com.example.yao.a94Asos.MyCampaignRecyclerViewAdapter.ITEM_NAME;

public class MessageActivity extends AppCompatActivity {

    private MessageActivity.UserLoginTask mAuthTask = null;

    private EditText mNameView;
    private EditText mTel;
    private EditText mDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        mNameView = (EditText)findViewById(R.id.message_name);
        mTel = (EditText)findViewById(R.id.message_tel);
        mDetails = (EditText)findViewById(R.id.message_details);
    }

    public void submitMessage(View view){

        Intent intent = getIntent();
        String recipient = intent.getStringExtra(MESSAGE_RECIPIENT);
        String project = intent.getStringExtra(ITEM_NAME);

        mNameView.setError(null);
        mTel.setError(null);
        mDetails.setError(null);

        String name = mNameView.getText().toString();
        String tel = mTel.getText().toString();
        String details = mDetails.getText().toString();

        boolean cancel = false;
        View focusView_campaign = null;

        if (TextUtils.isEmpty(name)) {
            mNameView.setError(getString(R.string.error_field_required));
            focusView_campaign = mNameView;
            cancel = true;
        }

        if (TextUtils.isEmpty(tel)) {
            mTel.setError(getString(R.string.error_field_required));
            focusView_campaign = mTel;
            cancel = true;
        }

        if (TextUtils.isEmpty(details)) {
            mDetails.setError(getString(R.string.error_field_required));
            focusView_campaign = mDetails;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView_campaign.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mAuthTask = new MessageActivity.UserLoginTask(name,tel,details,recipient,project);
            mAuthTask.execute((Void) null);
        }
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mName;
        private final String mTel;
        private final String mDetails;
        private final String mRecipient;
        private final String mProject;



        UserLoginTask(String name,String tel,String details,String recipient,String project) {
            mName = name;
            mTel = tel;
            mDetails = details;
            mRecipient = recipient;
            mProject = project;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            final Context context = getApplicationContext();
            final Messages m = new Messages();
            m.setName(mName);
            m.setTel(mTel);
            m.setDetails(mDetails);
            m.setRecipient(mRecipient);
            m.setProject(mProject);
            m.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        Toast.makeText(context, "发送成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "发送失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
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
