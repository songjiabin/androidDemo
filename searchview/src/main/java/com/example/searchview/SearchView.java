package com.example.searchview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * author : 宋佳
 * time   : 2018/06/20
 * desc   :自定义搜索功能
 * version: 1.0.0
 */

public class SearchView extends LinearLayout {


    private float textSizeSearch;
    private int textColorSearch;
    private String textHintSearch;
    private int searchBlockHeight;
    private int searchBlockColor;
    private Context context;
    private EditText et_search;
    private LinearLayout search_block;
    private ListViewScrollView listView;
    private TextView tv_clear;
    private ImageView searchBack;

    public SearchView(Context context) {
        super(context);
        initAttrs(context, null);
        init();
    }


    /**
     * 构造函数
     * 作用：对搜索框进行初始化
     */
    public SearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        init();
    }

    public SearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        init();
    }

    /**
     * 作用：初始化自定义属性
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        this.context = context;
        // 控件资源名称
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Search_View);
        // 搜索框字体大小（dp）
        textSizeSearch = typedArray.getDimension(R.styleable.Search_View_textSizeSearch, 20);


        // 搜索框字体颜色（使用十六进制代码，如#333、#8e8e8e）
        int defaultColor = context.getResources().getColor(R.color.colorText); // 默认颜色 = 灰色
        textColorSearch = typedArray.getColor(R.styleable.Search_View_textColorSearch, defaultColor);


        // 搜索框提示内容（String）
        textHintSearch = typedArray.getString(R.styleable.Search_View_textHintSearch);


        // 搜索框高度
        searchBlockHeight = typedArray.getInteger(R.styleable.Search_View_searchBlockHeight, 150);

        // 搜索框颜色
        int defaultColor2 = context.getResources().getColor(R.color.colorDefault); // 默认颜色 = 白色
        searchBlockColor = typedArray.getColor(R.styleable.Search_View_searchBlockColor, defaultColor2);

        // 释放资源
        typedArray.recycle();


    }


    //    初始化搜索框
    private void init() {
        //初始化 View
        initView();





    }

    private void initView() {
        // 1. 绑定R.layout.search_layout 作为搜索框的xml文件
        LayoutInflater.from(context).inflate(R.layout.layout_search, this);
        // 2. 绑定搜索框EditText
        et_search = (EditText) findViewById(R.id.et_search);
        et_search.setTextSize(textSizeSearch);
        et_search.setTextColor(textColorSearch);
        et_search.setHint(textHintSearch);
        // 3. 搜索框背景颜色
        search_block = (LinearLayout) findViewById(R.id.search_block);
        LayoutParams params = (LayoutParams) search_block.getLayoutParams();
        params.height = searchBlockHeight;
        search_block.setBackgroundColor(searchBlockColor);
        search_block.setLayoutParams(params);


        // 4. 历史搜索记录 = ListView显示
        listView = (ListViewScrollView) findViewById(R.id.listView);

        // 5. 删除历史搜索记录 按钮
        tv_clear = (TextView) findViewById(R.id.tv_clear);
        tv_clear.setVisibility(INVISIBLE);

        // 6. 返回按键
        searchBack = (ImageView) findViewById(R.id.search_back);
    }


}
