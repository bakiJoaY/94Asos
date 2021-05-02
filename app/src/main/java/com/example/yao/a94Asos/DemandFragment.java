package com.example.yao.a94Asos;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static com.example.yao.a94Asos.MainActivity.myDemandItem;


public class DemandFragment extends BasePageFragment {

    private RecyclerView recyclerView;
    private MyDemTask myDemTask;
    private View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view!=null){
            ViewGroup parent = (ViewGroup) view.getParent();
            if(parent!=null){
                parent.removeView(view);
            }
        }else{
            view=inflater.inflate(R.layout.fragment_demand,container,false);
            recyclerView = (RecyclerView)view.findViewById(R.id.dem_list);
        }




        Log.i("Start","campaignViewStart");
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void fetchData(){

        myDemTask = new MyDemTask();

        myDemandItem.clear();
        BmobQuery<Demand> demandBmobQuery = new BmobQuery<>();
        demandBmobQuery.order("-createdAt");
        demandBmobQuery.findObjects(new FindListener<Demand>() {
            @Override
            public void done(List<Demand> object, BmobException e) {
                myDemandItem.addAll(object);
                myDemTask.execute();
            }
        });
    }

    public class MyDemTask extends AsyncTask<Void,Void,String> {

        @Override
        protected String doInBackground(Void...params){
            return null;
        }

        @Override
        protected void onPostExecute(String string){
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new MyDemandRecyclerViewAdapter(myDemandItem));
            Log.i("Start","setDemandRecycler");
        }
    }


}
