package com.example.mydemo.utils;

import android.app.Activity;
import android.os.Build;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mydemo.R;
import com.example.mydemo.adapter.PopularAdapter;
import com.example.mydemo.bean.PopularWindowBean;

import java.util.ArrayList;
import java.util.List;

/**
 * author : 宋佳
 * time   : 2018/08/16
 * desc   :
 * version: 1.0.0
 */

public class CustomerPopular implements View.OnClickListener, AdapterView.OnItemClickListener {


    private TextView tv_popular_cancel;
    private TextView tv_popular_ok;
    private ListView lv_popular_data;
    private LinearLayout ll_popular_buttons;
    private TextView tv_popular_title;
    private ImageView iv_popular_down;
    private final CommonPopularWindow.Builder budilder;
    private Activity context;
    private View view;
    private static int CHOICE_STATE = 0;
    private PopularAdapter adapter;
    private List<PopularWindowBean> datas;
    private CommonPopularWindow popupWindow;

    public CustomerPopular(Activity context) {
        this.context = context;
        budilder = new CommonPopularWindow.Builder(context);
        initView();
        initListener();
    }


    private void initView() {
        view = LayoutInflater.from(context).inflate(R.layout.popular_products, null);
        tv_popular_cancel = (TextView) view.findViewById(R.id.tv_popular_cancel);//取消
        tv_popular_ok = (TextView) view.findViewById(R.id.tv_popular_ok);//确定
        ll_popular_buttons = (LinearLayout) view.findViewById(R.id.ll_popular_buttons);//确定  取消的 父布局
        lv_popular_data = (ListView) view.findViewById(R.id.lv_popular_data);//listView
        tv_popular_title = (TextView) view.findViewById(R.id.tv_popular_title);//标题
        iv_popular_down = (ImageView) view.findViewById(R.id.iv_popular_down);
    }


    private void initListener() {
        tv_popular_cancel.setOnClickListener(this);
        tv_popular_ok.setOnClickListener(this);
        iv_popular_down.setOnClickListener(this);
        lv_popular_data.setOnItemClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_popular_ok:
                //确定按钮
                getSelectPostion();
                break;
            case R.id.tv_popular_cancel:
                //取消按钮的
                popupWindow.dismiss();
                break;
            default:
                break;
        }
    }

    private void getSelectPostion() {
        SparseBooleanArray checkedItems = lv_popular_data.getCheckedItemPositions();
        if (checkedItems == null || checkedItems.size() == 0) {
            return;
        }

        int arrays[] = new int[checkedItems.size()];
        for (int i = 0; i < checkedItems.size(); ++i) {
            final int position = checkedItems.keyAt(i);//点击的 position
            final boolean isChecked = checkedItems.valueAt(i);  //是否点击
            if (isChecked) {
                arrays[i] = position;
            }
        }
        popularItemOnclick.getOnItemPosition(arrays);
        popupWindow.dismiss();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //当选择的模式是单选的时候 则返回它当前的id
        if (CHOICE_STATE == ListView.CHOICE_MODE_SINGLE) {
            //当只有单选模式才是可以的
            popularItemOnclick.getOnItemPosition(new int[]{position});
            popupWindow.dismiss();
            return;
        }
    }


    /**
     * 刷新数据
     *
     * @param datas
     * @param parnent
     * @param type
     * @param indexs
     * @return
     */
    public CustomerPopular refreshListDatas(List<PopularWindowBean> datas, View parnent, int type, int[] indexs) {
        if (this.datas == null)
            this.datas = new ArrayList<>();
        this.datas.clear();
        this.datas.addAll(datas);
        setAdapter(parnent, type, indexs);
        return this;
    }


    private void setAdapter(View parent, int type, int[] index) {
        //items upcheck
        this.CHOICE_STATE = type;
        clearSelection();
        lv_popular_data.setChoiceMode(type);
        if (adapter == null) {
            lv_popular_data.setItemsCanFocus(false);
            adapter = new PopularAdapter(context, datas);
            lv_popular_data.setAdapter(adapter);
        }
        //liseview显示最上面
        for (int i = 0; index != null && i < index.length; i++) {
            lv_popular_data.setItemChecked(index[i], true);
        }
        lv_popular_data.setSelection(0);
        adapter.notifyDataSetChanged();

        showAsDropDown(parent);
    }


    //清除状态
    private void clearSelection() {
        final int itemCount = lv_popular_data.getCount();
        for (int i = 0; i < itemCount; ++i) {
            lv_popular_data.setItemChecked(i, false);
        }
    }


    private void showAsDropDown(View parent) {
        popupWindow = budilder.setView(view).setBackGroundLevel(0.5f)
                .setOutsideTouchable(true)
                .create();


        changePopularSize();

        if (Build.VERSION.SDK_INT >= 24) {
            int[] location = new int[2]; // 记录anchor在屏幕中的位置
            parent.getLocationOnScreen(location);
            int offsetY = location[1] + parent.getHeight();
            if (Build.VERSION.SDK_INT == 25) { // Android 7.1中，PopupWindow高度为 match_parent 时，会占据整个屏幕
                // 故而需要在 Android 7.1上再做特殊处理
                int screenHeight = ScreenUtils.getScreenHeight(context); // 获取屏幕高度
                popupWindow.setHeight(screenHeight - offsetY); // 重新设置 PopupWindow 的高度
            }
            popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);

        } else {
            popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
        }

    }

    //改变popular的大小
    private void changePopularSize() {
        int height_default = (int) (ScreenUtils.getScreenHeight(context) * 0.7f);
        view.measure(0, 0);
        int measuredHeight = view.getMeasuredHeight();
        int count = 0;
        int size = adapter.getCount();
        for (int i = 0; i < size; i++) {
            View childAt = adapter.getView(i, null, lv_popular_data);
            if (childAt != null) {
                childAt.measure(0, 0);
                int measuredHeight2 = childAt.getMeasuredHeight();
                count += measuredHeight2;
            }
        }
        measuredHeight += count;
        if (measuredHeight < height_default) {
            popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        } else {
            popupWindow.setHeight(height_default);
        }
    }


    /**
     * 接口 点击 popular
     */
    public interface OnProductPopularItemOnclick {
        /**
         * 得到点解的postion
         *
         * @param
         */

        void getOnItemPosition(int[] positionArray);
    }

    public OnProductPopularItemOnclick popularItemOnclick;

    public void setPopularItemOnclick(OnProductPopularItemOnclick popularItemOnclick) {
        this.popularItemOnclick = popularItemOnclick;
    }


}
