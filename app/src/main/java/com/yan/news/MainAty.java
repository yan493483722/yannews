package com.yan.news;

import android.graphics.Movie;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.orhanobut.logger.Logger;
import com.yan.news.annotation.ActivityFragmentInject;
import com.yan.news.base.BaseAty;
import com.yan.news.module.news.entity.Person;

import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;


@ActivityFragmentInject(contentViewId = R.layout.aty_main)
public class MainAty extends BaseAty {


    @BindView(R.id.tv_main)
    TextView tv_main;

    @Override
    protected void initView() {
        Logger.d("initView");
        String string = "{" +
                "    \"people\": [" +
                "        {" +
                "            \"firstName\": \"Brett\"," +
                "            \"lastName\": \"McLaughlin\"," +
                "            \"email\": \"aaaa\"" +
                "        }," +
                "        {" +
                "            \"firstName\": \"Jason\"," +
                "            \"lastName\": \"Hunter\"," +
                "            \"email\": \"bbbb\"" +
                "        }," +
                "        {" +
                "            \"firstName\": \"Elliotte\"," +
                "            \"lastName\": \"Harold\"," +
                "            \"email\": \"cccc\"" +
                "        }" +
                "    ]" +
                "}";
        JSONObject jsonObject = JSON.parseObject(string);
        List<Person> persons = JSON.parseArray(jsonObject.getString("people"), Person.class);
        Logger.e(string);
        Logger.json(string);
        if (this.getResources().getString(R.string.release_id).equals(BuildConfig.APPLICATION_ID)) {
            tv_main.setText(this.getResources().getString(R.string.app_name));
        } else if (this.getResources().getString(R.string.preview_id).equals(BuildConfig.APPLICATION_ID)) {
            tv_main.setText(this.getResources().getString(R.string.app_name_preview));
        } else {
            tv_main.setText(this.getResources().getString(R.string.app_name_debug));
        }

    }

}
