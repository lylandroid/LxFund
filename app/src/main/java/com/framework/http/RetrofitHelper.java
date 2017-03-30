package com.framework.http;

import android.support.annotation.NonNull;

import com.framework.util.NetworkUtils;
import com.kscf.app.android.app.App;
import com.kscf.app.android.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * retrofit2工具类
 * Created by lyl on 2016/12/3.
 */

public class RetrofitHelper {

    private static SoftReference<RetrofitHelper> sInstance;

    private static String[] sBaseUrls = new String[NetCount.URL_TOTAL.getValue()];
    /**
     * 解决同一个项目多个域名
     */
    private Retrofit[] mRetrofits = new Retrofit[NetCount.URL_TOTAL.getValue()];

    private RetrofitHelper() {
        createOkHttp(NetCount.URL_1);
    }

    private void createOkHttp(NetCount netCount) {
        OkHttpClient okHttpClient = createOkhttpAndCache();
        mRetrofits[netCount.getValue()] = new Retrofit.Builder()
                .baseUrl(sBaseUrls[netCount.getValue()])
                .client(okHttpClient)
                .addConverterFactory(StringConverterFactory.create())
                //.addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static RetrofitHelper getInstance() {
        return getInstance(NetCount.URL_1);
    }

    public static RetrofitHelper getInstance(@NonNull NetCount netCount) {
        if (sInstance == null || sInstance.get() == null) {
            synchronized (RetrofitHelper.class) {
                if (sInstance == null || sInstance.get() == null) {
                    sInstance = new SoftReference(new RetrofitHelper());
                }
            }
        }
        RetrofitHelper helper = sInstance.get();
        int retrofitIndex = netCount.getValue();
        if (helper.mRetrofits[retrofitIndex] == null) {
            helper.createOkHttp(netCount);
        }
        return helper;
    }

    public Retrofit getRetrofit(NetCount netCount) {
        return mRetrofits[netCount.getValue()];
    }


    /**
     * 发送网络请求
     *
     * @param observable
     * @param httpSubscriber
     * @param <T>
     */
    public static <T> Subscription sendNet(Observable observable, final HttpSubscriber<T> httpSubscriber) {
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(httpSubscriber);
        /*return observable.subscribeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(httpSubscriber);*/

    }


    public static void setBaseUrl(NetCount netCount, String baseUrl) {
        sBaseUrls[netCount.getValue()] = baseUrl;
    }

    public static void setBaseUrl(String baseUrl) {
        setBaseUrl(NetCount.URL_1, baseUrl);
    }

    /**
     * 创建okhttp & 添加缓存
     *
     * @return
     */
    private OkHttpClient createOkhttpAndCache() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //http缓存路径
        File cacheFile = new File(FileUtils.getCacheDir());
        //http缓存大小10M
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 10);

        //添加网络过滤 & 实现网络数据缓存
        Interceptor interceptor = createHttpInterceptor();
        builder.addNetworkInterceptor(interceptor);
        builder.addInterceptor(interceptor);

        //给okhttp添加缓存
        builder.cache(cache);

        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);

        //错误重连
        builder.retryOnConnectionFailure(true);

        return builder.build();
    }


    /**
     * 创建网络过滤对象，添加缓存
     *
     * @return
     */
    private Interceptor createHttpInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                boolean isNetwork = NetworkUtils.isConnected(App.getInstance());
                //没有网络情况下，仅仅使用缓存（CacheControl.FORCE_CACHE;）
                if (!isNetwork) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (isNetwork) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
    }

    public static Map<String, Object> getHttpParam() {
        return new HashMap<>();
    }
}
