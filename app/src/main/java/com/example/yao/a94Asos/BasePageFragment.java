package com.example.yao.a94Asos;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

/**
 * Created by yaojunl on 2017/3/2.
 */

public abstract class BasePageFragment extends Fragment {

    protected boolean isViewInitiated;
    protected boolean isVisibleToUser;
    protected boolean isDataInitiated;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        this.isViewInitiated = true;
        prepareFetchData();
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }

    public abstract void fetchData();

    public boolean prepareFetchData(){
        return prepareFetchData(false);
    }

    public boolean prepareFetchData(boolean forceUpdate){
        if(isVisibleToUser&&isViewInitiated&&(!isDataInitiated||forceUpdate)){
            fetchData();
            isDataInitiated = true;
            return true;
        }

        return false;
    }

}
