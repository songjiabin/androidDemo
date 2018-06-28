package com.example.mydemo.fragment;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.mydemo.R;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * author : 宋佳
 * time   : 2018/06/25
 * desc   :
 * version: 1.0.0
 */

public class JsToAndroidFragment extends Fragment {


    private WebView mWebView;

    public static JsToAndroidFragment newInstance(@LayoutRes int sampleLayoutRes) {
        JsToAndroidFragment fragment = new JsToAndroidFragment();
        Bundle args = new Bundle();
        args.putInt("sampleLayoutRes", sampleLayoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_android_js2, container, false);


        mWebView = (WebView) view.findViewById(R.id.webView);
        WebSettings webSettings = mWebView.getSettings();
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        // 先载入JS代码
        // 步骤1：加载JS代码
        // 格式规定为:file:///android_asset/文件名.html
        mWebView.loadUrl("file:///android_asset/javascript3.html");


        // 复写WebViewClient类的shouldOverrideUrlLoading方法
        mWebView.setWebViewClient(new WebViewClient() {
                                      @Override
                                      public boolean shouldOverrideUrlLoading(WebView view, String url) {

                                          // 步骤2：根据协议的参数，判断是否是所需要的url
                                          // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
                                          //假定传入进来的 url = "js://webview?arg1=111&arg2=222"（同时也是约定好的需要拦截的）

                                          Uri uri = Uri.parse(url);
                                          // 如果url的协议 = 预先约定的 js 协议
                                          // 就解析往下解析参数
                                          if (uri.getScheme().equals("js")) {

                                              // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
                                              // 所以拦截url,下面JS开始调用Android需要的方法
                                              if (uri.getAuthority().equals("webview")) {

                                                  //  步骤3：
                                                  // 执行JS所需要调用的逻辑

                                                  // 可以在协议上带有参数并传递到Android上
                                                  HashMap<String, String> params = new HashMap<>();
                                                  Set<String> collection = uri.getQueryParameterNames();


                                                  Iterator<String> it = collection.iterator();
                                                  while (it.hasNext()) {
                                                      //key
                                                      String key = it.next();
                                                      //value
                                                      String value = uri.getQueryParameter(key);


                                                      Toast.makeText(getActivity(), "key--" + key + "\nvalue--" + value, Toast.LENGTH_SHORT).show();
                                                  }

                                              }

                                              return true;
                                          }
                                          return super.shouldOverrideUrlLoading(view, url);
                                      }
                                  }
        );


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();

    }


}
