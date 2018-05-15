package com.gcp.starhttp.activity;

import android.view.View;
import android.widget.TextView;

import com.gcp.starhttp.R;
import com.gcp.starhttp.bean.BaseBean;
import com.gcp.starhttp.bean.BooksInfo;
import com.gcp.starhttp.bean.UserInfo;
import com.gcp.starhttp.nohttp.BaseRequest;
import com.gcp.starhttp.nohttp.HttpListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private TextView mTvResult;

    @Override
    public int intiLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        findViewById(R.id.get_user).setOnClickListener(this);
        findViewById(R.id.get_book).setOnClickListener(this);
        mTvResult = (TextView) findViewById(R.id.tv_result);
    }

    @Override
    public void initData() {
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.get_user) {// 获取用户信息
            String url = "http://172.16.10.240:8080/noHttpServer/UserInfoServlet";
            Request<BaseBean> request = new BaseRequest<BaseBean>(url, BaseBean.class);
            request(0, request, callBack, false, true);
        } else if (v.getId() == R.id.get_book) {// 获取书籍信息
            String url = "http://172.16.10.240:8080/noHttpServer/BookServlet";
            Request<BaseBean> request = new BaseRequest<BaseBean>(url, BaseBean.class);
            request(1, request, callBack, false, true);
        }
    }

    private HttpListener<BaseBean> callBack = new HttpListener<BaseBean>() {
        @Override
        public void onSucceed(int what, Response<BaseBean> response) {
            if (what == 0) {
                BaseBean baseBean = response.get();
                if (baseBean.isSuccess()) {
                    UserInfo content = baseBean.parseData(UserInfo.class);
                    String s = "用户名：" + content.getUserName() + "；密码：" + content.getPassword();
                    mTvResult.setText(s);
                } else {
                    mTvResult.setText("业务处理层数据失败");
                }
            } else if (what == 1) {
                BaseBean baseBean = response.get();
                if (baseBean.isSuccess()) {
                    BooksInfo content = baseBean.parseData(BooksInfo.class);
                    List<String> list =content.getList();

                    String s="";
                    for(int i=0;i<list.size();i++){
                       s+= "书籍名字" +i + "：" + list.get(i)+"\n";
                    }
                    mTvResult.setText(s);
                } else {
                    mTvResult.setText("业务处理层数据失败");
                }
            }
        }

        @Override
        public void onFailed(int what, Response<BaseBean> response) {
            mTvResult.setText("请求失败");
        }
    };

}
