package com.example.yao.a94Asos;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.yao.a94Asos.MyCampaignRecyclerViewAdapter.ITEM_DETAILS;
import static com.example.yao.a94Asos.MyCampaignRecyclerViewAdapter.ITEM_NAME;
import static com.example.yao.a94Asos.MyCampaignRecyclerViewAdapter.ITEM_SCALE;
import static com.example.yao.a94Asos.MyCampaignRecyclerViewAdapter.ITEM_TARGET;
import static com.example.yao.a94Asos.MyCampaignRecyclerViewAdapter.ITEM_USER;

public class DetailActivity extends AppCompatActivity {

    public static final String MESSAGE_RECIPIENT = "message_recipient";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        final Intent intent = getIntent();
        String name = intent.getStringExtra(ITEM_NAME);
        String scale = intent.getStringExtra(ITEM_SCALE);
        String details = intent.getStringExtra(ITEM_DETAILS);
        String target = intent.getStringExtra(ITEM_TARGET);
        final String user = intent.getStringExtra(ITEM_USER);
        final String project = name;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
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

        TextView scaleTV = (TextView)findViewById(R.id.detail_scale);
        TextView detailsTV = (TextView)findViewById(R.id.detail_details);
        Button messageButton = (Button)findViewById(R.id.detail_message);

        scaleTV.setText("规模：" + scale);
        detailsTV.setText("简介：" + details);
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1= new Intent(DetailActivity.this,MessageActivity.class);
                intent1.putExtra(MESSAGE_RECIPIENT,user);
                intent1.putExtra(ITEM_NAME,project);
                startActivity(intent1);
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
