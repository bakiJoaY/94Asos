package com.example.yao.a94Asos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

import static com.example.yao.a94Asos.MainActivity.myMessages;
import static com.example.yao.a94Asos.MainActivity.myReleasedCampaignItem;
import static com.example.yao.a94Asos.MainActivity.myReleasedDemandItem;

/**
 * Created by yaojunl on 2017/2/28.
 */

public class Start extends AppCompatActivity {


    private String BmobID="ad5072ea8f82252224187a845da9c817";

    private void toast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Bmob.initialize(this,BmobID);
        final Context context = getApplicationContext();

        BmobUser bmobUser = BmobUser.getCurrentUser();

        if(bmobUser != null){

            final String username = (String) BmobUser.getObjectByKey("username");

            myReleasedDemandItem.clear();
            BmobQuery<Demand> demandReleasedBmobQuery = new BmobQuery<>();
            demandReleasedBmobQuery.addWhereEqualTo("user",username);
            demandReleasedBmobQuery.order("-createdAt");
            demandReleasedBmobQuery.findObjects(new FindListener<Demand>() {
                @Override
                public void done(List<Demand> object, BmobException e) {
                    myReleasedDemandItem.addAll(object);
                }
            });

            myReleasedCampaignItem.clear();
            BmobQuery<Campaign> campaignReleasedBmobQuery = new BmobQuery<>();
            campaignReleasedBmobQuery.addWhereEqualTo("user",username);
            campaignReleasedBmobQuery.order("-createdAt");
            campaignReleasedBmobQuery.findObjects(new FindListener<Campaign>() {
                @Override
                public void done(List<Campaign> object, BmobException e) {
                    myReleasedCampaignItem.addAll(object);
                }
            });

            myMessages.clear();
            BmobQuery<Messages> messageBmobQuery = new BmobQuery<>();
            messageBmobQuery.addWhereEqualTo("recipient",username);
            messageBmobQuery.order("-createdAt");
            messageBmobQuery.findObjects(new FindListener<Messages>() {
                @Override
                public void done(List<Messages> object, BmobException e) {
                    myMessages.addAll(object);
                }
            });

            Intent intent = new Intent(Start.this,MainActivity.class);
            intent.putExtra("com.example.yao.94Asos.USERNAME", username);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(com.example.yao.a94Asos.Start.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
