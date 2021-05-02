package com.example.yao.a94Asos;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static com.example.yao.a94Asos.MainActivity.myMessages;
import static com.example.yao.a94Asos.MyCampaignRecyclerViewAdapter.ITEM_OBJECTID;

public class MyMessageActivity extends AppCompatActivity {

    public static final String MESSAGE_NAME = "message_name";
    public static final String MESSAGE_TEL = "message_tel";
    public static final String MESSAGE_DETAILS = "message_details";
    public static final String MESSAGE_PROJECT = "message_project";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar7);
        setSupportActionBar(toolbar);

        View recyclerView = findViewById(R.id.my_message);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(myMessages));
    }//ITEMS=>mValues

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Messages> mValues;

        public SimpleItemRecyclerViewAdapter(List<Messages> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.my_message_item, parent, false);
            return new ViewHolder(view);
        }      //create an item xml of list

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mProjectView.setText(holder.mItem.getProject());
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, MyMessageDetailActivity.class);
                    intent.putExtra(MESSAGE_NAME, holder.mItem.getName());
                    intent.putExtra(MESSAGE_TEL, holder.mItem.getTel());
                    intent.putExtra(MESSAGE_DETAILS, holder.mItem.getDetails());
                    intent.putExtra(ITEM_OBJECTID, holder.mItem.getObjectId());
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mProjectView;
            public Messages mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mProjectView = (TextView) view.findViewById(R.id.my_message_project);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mProjectView.getText() + "'";
            }
        }
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
