package com.example.yao.a94Asos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static com.example.yao.a94Asos.MainActivity.myVIPCampaignItem;
import static com.example.yao.a94Asos.MainActivity.myVIPCampaignItemBitmap;
import static com.example.yao.a94Asos.MainActivity.myVIPCampaignItemUrl;
import static com.example.yao.a94Asos.MainActivity.myVIPDemandItem;
import static com.example.yao.a94Asos.MainActivity.myVIPDemandItemBitmap;
import static com.example.yao.a94Asos.MainActivity.myVIPDemandItemUrl;


public class RecFragment extends BasePageFragment {

    private Bitmap bitmap;
    private Bitmap bitmap2;
    private MyRecTask myRecTask;
    private MyRecTask2 myRecTask2;
    private RecyclerView recyclerDemandView;
    private RecyclerView recyclerDemandView2;
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
            view = inflater.inflate(R.layout.fragment_rec, container, false);
            recyclerDemandView = (RecyclerView) view.findViewById(R.id.rec_demand);
            recyclerDemandView2 = (RecyclerView) view.findViewById(R.id.rec_campaign);
        }

        Log.i("Start","onRecFragmentCreateView");
        return view;
    }


    @Override
    public void fetchData(){

        myRecTask = new MyRecTask();
        myRecTask2 = new MyRecTask2();

        myVIPDemandItem.clear();
        myVIPDemandItemUrl.clear();
        myVIPDemandItemBitmap.clear();
        BmobQuery<VIPDemand> vipDemandBmobQuery = new BmobQuery<>();
        vipDemandBmobQuery.order("priority");
        vipDemandBmobQuery.findObjects(new FindListener<VIPDemand>() {
            @Override
            public void done(List<VIPDemand> object, BmobException e) {

                Log.i("Start","VIPDemandStartSet");
                myVIPDemandItem.addAll(object);
                for(VIPDemand vipDemand : object){
                    myVIPDemandItemUrl.add(vipDemand.getImage().getFileUrl());
                    Log.d("Start",vipDemand.getName());
                }
                myRecTask.execute();
                Log.d("Start","ss"+myVIPDemandItemUrl.size());
            }
        });

        myVIPCampaignItem.clear();
        myVIPCampaignItemUrl.clear();
        myVIPDemandItemBitmap.clear();
        BmobQuery<VIPCampaign> vipCampaignBmobQuery = new BmobQuery<>();
        vipCampaignBmobQuery.order("priority");
        vipCampaignBmobQuery.findObjects(new FindListener<VIPCampaign>() {
            @Override
            public void done(List<VIPCampaign> object, BmobException e) {

                Log.i("Start","VIPCampaignStartSet");
                myVIPCampaignItem.addAll(object);
                for(VIPCampaign vipCampaign : object){
                    myVIPCampaignItemUrl.add(vipCampaign.getImage().getFileUrl());
                    Log.d("Start",vipCampaign.getName());
                }
                myRecTask2.execute();
                Log.d("Start","shu"+myVIPCampaignItemUrl.size());
            }
        });


    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public class MyRecTask extends AsyncTask<Void,Void,List<Bitmap>> {


        @Override
        protected List<Bitmap> doInBackground(Void...params){
            try{
                for (int i = 0; i < myVIPDemandItemUrl.size(); i++) {
                    URL myURL = new URL(myVIPDemandItemUrl.get(i));
                    InputStream is = myURL.openStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    myVIPDemandItemBitmap.add(bitmap);
                    Log.i("Start","bitmapDone");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return myVIPDemandItemBitmap;
        }

        @Override
        protected void onPostExecute(List<Bitmap> bitmapList){
            LinearLayoutManager line = new LinearLayoutManager(getContext());
            line.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerDemandView.setLayoutManager(line);
            recyclerDemandView.setAdapter(new RecDemandRecyclerViewAdapter(myVIPDemandItem, bitmapList));
            Log.i("Start","setDemandRecycler");
        }
    }

    public class MyRecTask2 extends AsyncTask<Void,Void,List<Bitmap>> {


        @Override
        protected List<Bitmap> doInBackground(Void...params){
            try{
                for (int i = 0; i < myVIPCampaignItemUrl.size(); i++) {
                    URL myURL = new URL(myVIPCampaignItemUrl.get(i));
                    InputStream is = myURL.openStream();
                    bitmap2 = BitmapFactory.decodeStream(is);
                    myVIPCampaignItemBitmap.add(bitmap2);
                    Log.i("Start","bitmap2Done");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return myVIPCampaignItemBitmap;
        }

        @Override
        protected void onPostExecute(List<Bitmap> bitmapList){
            recyclerDemandView2.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerDemandView2.setAdapter(new RecCampaignRecyclerViewAdapter(myVIPCampaignItem, bitmapList));
            Log.i("Start","seCampaignRecycler");
        }
    }
}
