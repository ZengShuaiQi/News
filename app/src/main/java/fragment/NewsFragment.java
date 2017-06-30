package fragment;

import android.widget.ListView;
import android.widget.TextView;

import com.example.cleverboy.news.R;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import adapter.NewsAdapter;
import base.BaseFragment;
import bean.NewsEntity;
import global.URLManager;

/**
 * Created by yls on 2017/6/28.
 */

public class NewsFragment extends BaseFragment {
    /**
     * 新闻类别id
     */
    private String channelId;
    private ListView lv_01;


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_news;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;

    }

    @Override
    public void initView() {
        lv_01 = (ListView) mRootView.findViewById(R.id.lv_01);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        getDatafromServer();
    }

    private void getDatafromServer() {

        String url = URLManager.getUrl(channelId);

        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String json = responseInfo.result;
                json = json.replace(channelId,"result");
                Gson gson = new Gson();
                NewsEntity newsDatas = gson.fromJson(json, NewsEntity.class);
                System.out.println("----解析json:" + newsDatas.getResult().size());
                //显示数据到列表中
                showDatas(newsDatas);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                    System.out.print("sdfsdfsdfdsfsdfsdf"+error+msg);
            }
        });
    }

    private void showDatas(NewsEntity newsDatas) {
        if (newsDatas == null || newsDatas.getResult() == null || newsDatas.getResult().size() == 0) {
            return ;
        }
        NewsAdapter newsAdapter = new NewsAdapter(mActivity,newsDatas.getResult());
        lv_01.setAdapter(newsAdapter);
    }
}
