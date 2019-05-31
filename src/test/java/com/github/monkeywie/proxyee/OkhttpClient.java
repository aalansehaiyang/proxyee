package com.github.monkeywie.proxyee;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.CookieManager;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

/**
 * @author onlyone
 */
public class OkhttpClient {

    public static void main(String[] args) throws IOException {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 设置连接超时时间
        builder.connectTimeout(1, TimeUnit.MINUTES);
        // 设置代理,需要替换
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 9999));
        builder.proxy(proxy);
        // cookie管理器
        CookieManager cookieManager = new CookieManager();
        OkHttpClient client = builder.build();

        Request request = new Request.Builder().url("http://www.baidu.com/").get().build();
        Response execute = client.newCall(request).execute();
        System.out.println(execute.body().string());
        execute.close();

    }
}
