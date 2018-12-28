package ningjiaxin1.bwie.com.week3_lian2.untils;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUntils {
    private static OkHttpUntils instance;
    private OkHttpClient mClient;
    private Handler hander=new Handler(Looper.getMainLooper());
    //单例
    public static OkHttpUntils getInstance(){
        if(instance==null) {
            synchronized (OkHttpUntils.class) {
                instance = new OkHttpUntils();
            }
           }
             return instance;
        }
    public OkHttpUntils(){
        //日志
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        HttpLoggingInterceptor interceptor1 = interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mClient = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }
    //接口
    public interface OkCallBack{
        void onSuccess(Object o);
        void fail(Exception e);
    }
    //get请求
    public void getRequest(String url, final Class clazz, final OkCallBack okCallBack){
        final Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                hander.post(new Runnable() {
                    @Override
                    public void run() {
                        okCallBack.fail(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                final Object o = gson.fromJson(result,clazz);
                hander.post(new Runnable() {
                    @Override
                    public void run() {
                        okCallBack.onSuccess(o);
                    }
                });
            }
        });
    }
    //post请求
    public void postRequest(String url, Map<String,String> parmas, final Class clazz, final OkCallBack okCallBack){
        FormBody.Builder builder = new FormBody.Builder();
        for(Map.Entry<String,String> entry:parmas.entrySet()){
            builder.add(entry.getKey(),entry.getValue());
        }
        FormBody build = builder.build();
        Request build1 = new Request.Builder()
                .url(url)
                .post(build)
                .build();
        Call call = mClient.newCall(build1);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                hander.post(new Runnable() {
                    @Override
                    public void run() {
                        okCallBack.fail(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                final Gson gson = new Gson();
                final Object o = gson.fromJson(result, clazz);
                hander.post(new Runnable() {
                    @Override
                    public void run() {
                        okCallBack.onSuccess(o);
                    }
                });
            }
        });
    }
 }
