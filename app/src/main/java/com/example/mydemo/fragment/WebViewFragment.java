package com.example.mydemo.fragment;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.mydemo.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * author : 宋佳
 * time   : 2018/06/28
 * desc   :
 * version: 1.0.0
 */

public class WebViewFragment extends Fragment {


    @LayoutRes
    int sampleLayoutRes;
    private WebView mWebview;
    private TextView beginLoading;
    private TextView endLoading;
    private TextView loading;
    private TextView mtitle;
    private WebSettings mWebSettings;

    public static WebViewFragment newInstance(@LayoutRes int sampleLayoutRes) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle args = new Bundle();
        args.putInt("sampleLayoutRes", sampleLayoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webview, container, false);


        mWebview = (WebView) view.findViewById(R.id.webView1);
        beginLoading = (TextView) view.findViewById(R.id.text_beginLoading);
        endLoading = (TextView) view.findViewById(R.id.text_endLoading);
        loading = (TextView) view.findViewById(R.id.text_Loading);
        mtitle = (TextView) view.findViewById(R.id.title);

        mWebSettings = mWebview.getSettings();

        mWebview.loadUrl("http://www.baidu.com/");


        //设置不用系统浏览器打开,直接显示在当前Webview
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            // 复写shouldInterceptRequest
            //API21以下用shouldInterceptRequest(WebView view, String url)
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {

                // 步骤1:判断拦截资源的条件，即判断url里的图片资源的文件名
                // 此处网页里图片的url为:www.baidu.com/img/bd_logo1.png?where=super
                // 图片的资源文件名为:bd_logo1.png

                if (url.contains("bd_logo1.png")) {

                    InputStream is = null;
                    // 步骤2:创建一个输入流


                    try {
                        is = getActivity().getApplicationContext().getAssets().open("images/baidu.jpg");
                        // 步骤3:打开需要替换的资源(存放在assets文件夹里)
                        // 在app/src/main下创建一个assets文件夹
                        // assets文件夹里再创建一个images文件夹,baidu.png的图片

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // 步骤4:替换资源

                    WebResourceResponse response = new WebResourceResponse("image/jpg",
                            "utf-8", is);
                    // 参数1:http请求里该图片的Content-Type,此处图片为image/png
                    // 参数2:编码类型
                    // 参数3:替换资源的输入流

                    System.out.println("旧API");
                    return response;
                }

                return super.shouldInterceptRequest(view, url);
            }

            // API21以上用shouldInterceptRequest(WebView view, WebResourceRequest request)
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {

                // 步骤1:判断拦截资源的条件，即判断url里的图片资源的文件名
                // 此处图片的url为:http://s.ip-cdn.com/img/logo.gif
                // 图片的资源文件名为:logo.gif
                if (request.getUrl().toString().contains("bd_logo1.png")) {

                    InputStream is = null;
                    // 步骤2:创建一个输入流

                    try {
                        is = getActivity().getApplicationContext().getAssets().open("images/baidu.png");
                        // 步骤3:打开需要替换的资源(存放在assets文件夹里)
                        // 在app/src/main下创建一个assets文件夹
                        // assets文件夹里再创建一个images文件夹,baidu.png的图片

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //步骤4:替换资源

                    WebResourceResponse response = new WebResourceResponse("image/png",
                            "utf-8", is);
                    // 参数1：http请求里该图片的Content-Type,此处图片为image/png
                    // 参数2：编码类型
                    // 参数3：存放着替换资源的输入流（上面创建的那个）

                    return response;
                }
                return super.shouldInterceptRequest(view, request);
            }




        });

        //设置WebChromeClient类
        mWebview.setWebChromeClient(new WebChromeClient() {


            //获取网站标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                System.out.println("标题在这里");
                mtitle.setText(title);
            }


            //获取加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    String progress = newProgress + "%";
                    loading.setText(progress);
                } else if (newProgress == 100) {
                    String progress = newProgress + "%";
                    loading.setText(progress);
                }
            }
        });


        //设置WebViewClient类
        mWebview.setWebViewClient(new WebViewClient() {
            //设置加载前的函数
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                System.out.println("开始加载了");
                beginLoading.setText("开始加载了");

            }

            //设置结束加载函数
            @Override
            public void onPageFinished(WebView view, String url) {
                endLoading.setText("结束加载了");

            }
        });


        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebview != null) {
            mWebview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebview.clearHistory();

            ((ViewGroup) mWebview.getParent()).removeView(mWebview);
            mWebview.destroy();
            mWebview = null;
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            sampleLayoutRes = args.getInt("sampleLayoutRes");

        }
    }


}
