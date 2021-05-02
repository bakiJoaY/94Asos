package com.example.yao.a94Asos;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DeleteBatchListener;
import cn.bmob.v3.listener.UpdateListener;

import static com.example.yao.a94Asos.MyCampaignRecyclerViewAdapter.ITEM_OBJECTID;
import static com.example.yao.a94Asos.MyMessageActivity.MESSAGE_DETAILS;
import static com.example.yao.a94Asos.MyMessageActivity.MESSAGE_NAME;
import static com.example.yao.a94Asos.MyMessageActivity.MESSAGE_TEL;

public class MyMessageDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message_detail);

        final Intent intent = getIntent();
        String name = intent.getStringExtra(MESSAGE_NAME);
        String tel = intent.getStringExtra(MESSAGE_TEL);
        String details = intent.getStringExtra(MESSAGE_DETAILS);
        final String objectId = intent.getStringExtra(ITEM_OBJECTID);

        TextView nameTV = (TextView)findViewById(R.id.my_message_name);
        TextView telTV = (TextView)findViewById(R.id.my_message_tel);
        TextView detailsTV = (TextView)findViewById(R.id.my_message_details);

        nameTV.setText("称呼：  "+name);
        telTV.setText("联系方式："+tel);
        detailsTV.setText("留言：  "+details);

        Button mCancelMessageButton = (Button) findViewById(R.id.cancel_message_item);
        mCancelMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context = getApplicationContext();
                final Messages m = new Messages();
                m.setObjectId(objectId);
                m.delete(new UpdateListener() {

                    @Override
                    public void done(BmobException e) {
                        if (e==null) {
                            finish();
                            Toast.makeText(context,"删除成功",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context,"删除成功",Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

    }
}
