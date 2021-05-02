package com.example.yao.a94Asos;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

import static com.example.yao.a94Asos.MyCampaignRecyclerViewAdapter.ITEM_DETAILS;
import static com.example.yao.a94Asos.MyCampaignRecyclerViewAdapter.ITEM_NAME;
import static com.example.yao.a94Asos.MyCampaignRecyclerViewAdapter.ITEM_OBJECTID;
import static com.example.yao.a94Asos.MyCampaignRecyclerViewAdapter.ITEM_SCALE;
import static com.example.yao.a94Asos.MyCampaignRecyclerViewAdapter.ITEM_TARGET;
import static com.example.yao.a94Asos.MyCampaignRecyclerViewAdapter.ITEM_USER;

public class ReleasedDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_released_detail);

        Intent intent = getIntent();
        String name = intent.getStringExtra(ITEM_NAME);
        String scale = intent.getStringExtra(ITEM_SCALE);
        String details = intent.getStringExtra(ITEM_DETAILS);
        String target = intent.getStringExtra(ITEM_TARGET);
        String user = intent.getStringExtra(ITEM_USER);
        final String objectId = intent.getStringExtra(ITEM_OBJECTID);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar6);
        setSupportActionBar(toolbar);

        if(target==null){
            setTitle(name);
            scale += "人";
        }else {
            name += "(" ;
            target += ")";
            name += target;
            setTitle(name);
            scale += "元";
        }

        TextView scaleTV = (TextView)findViewById(R.id.released_detail_scale);
        TextView detailsTV = (TextView)findViewById(R.id.released_detail_details);

        scaleTV.setText("规模：" + scale);
        detailsTV.setText("简介：" + details);

        Button mCancelButton = (Button) findViewById(R.id.cancel_item);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context = getApplicationContext();

                final Campaign c = new Campaign();
                c.setObjectId(objectId);
                c.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        finish();
                        Toast.makeText(context,"撤销成功",Toast.LENGTH_SHORT).show();
                    }
                });

                final Demand d = new Demand();
                d.setObjectId(objectId);
                d.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        finish();
                        Toast.makeText(context,"撤销成功",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
