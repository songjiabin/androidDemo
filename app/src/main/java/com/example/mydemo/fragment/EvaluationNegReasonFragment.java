package com.example.mydemo.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydemo.R;
import com.example.mydemo.view.EvaluationNegReasonsLayout;
import com.example.mydemo.view.MyDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.dmoral.toasty.Toasty;

/**
 * author : 宋佳
 * time   : 2018/07/19
 * desc   :
 * version: 1.0.0
 */

public class EvaluationNegReasonFragment extends Fragment {


    @LayoutRes
    int sampleLayoutRes;
    private EvaluationNegReasonsLayout mReasonLayout;
    private View mBtnAdd;
    private View mBtnRemove;
    private MyAdapter mAdapter;

    public static EvaluationNegReasonFragment newInstance(@LayoutRes int sampleLayoutRes) {
        EvaluationNegReasonFragment fragment = new EvaluationNegReasonFragment();
        Bundle args = new Bundle();
        args.putInt("sampleLayoutRes", sampleLayoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_evaluation_neg_reason, container, false);
        mReasonLayout = (EvaluationNegReasonsLayout) view.findViewById(R.id.reason_layout);
        mBtnAdd = view.findViewById(R.id.btn_add);
        mBtnRemove = view.findViewById(R.id.btn_remove);


        view.findViewById(R.id.alert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog myDialog=new MyDialog(getActivity());
                myDialog.show();
            }
        });




        mAdapter = new MyAdapter(getActivity());
        mReasonLayout.setAapter(mAdapter);
        List<String> reasonsData = new ArrayList<>();
        reasonsData.add("swift");
        reasonsData.add("Android");
        reasonsData.add("Java");
        reasonsData.add("Python");
        mAdapter.setData(reasonsData);
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.add(createData());
            }
        });
        mBtnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.remove();
            }
        });
        mReasonLayout.setOnReasonSelectListener(new EvaluationNegReasonsLayout.OnReasonSelectListener() {
            @Override
            public void onItemSelect(EvaluationNegReasonsLayout parent, List<Integer> selectedList) {
                StringBuilder sb = new StringBuilder();
                for (Integer integer : selectedList) {
                    sb.append((String) parent.getAdapter().getItem(integer)).append(",");
                }
                Toasty.info(getActivity(), "starCount:\t" + sb.toString(), Toast.LENGTH_SHORT, false).show();
            }
        });


        return view;
    }


    private String createData() {
        String[] reasons = new String[]{"AndroidAndroidAndroidAndroid", "C", "PASCALPASCALPASCALPASCALPASCALPASCALPASCAL",
                "PythonPythonPythonPythonPythonPythonPythonPythonPythonPythonPythonPython", "PHPPHPPHPPHPPHPPHPPHPPHP", "Ruby", "Lua", "JavaScript", "JavaJavaJavaJavaJavaJavaJavaJava", "Virtual Basic", "C++", "C#", "HTML5", "我是宋佳宾宋佳宾宋佳宾宋佳宾宋佳宾宋佳宾宋佳宾宋佳宾宋佳宾宋佳宾宋佳宾宋佳宾宋佳宾宋佳宾"};
        Random random = new Random();
        return reasons[random.nextInt(reasons.length - 1)];
    }

    static class MyAdapter extends BaseAdapter {

        private Context mContext;
        private List<String> mData;

        MyAdapter(Context context) {
            mContext = context;
            mData = new ArrayList<>();
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public String getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(mContext, R.layout.item_negative_reason, null);
            TextView textView = (TextView) view.findViewById(R.id.tv_negative_reason);
            textView.setText(mData.get(position));
            return view;
        }

        void setData(List<String> data) {
            if (data == null) {
                return;
            }
            mData = data;
            notifyDataSetChanged();
        }

        void remove() {
            if (mData != null) {
                if (mData.size() > 1) {
                    Random random = new Random();
                    int index = random.nextInt(mData.size() - 1);
                    mData.remove(index);
                } else if (mData.size() > 0) {
                    mData.remove(0);
                }
            }
            notifyDataSetChanged();
        }

        void add(String reason) {
            if (mData != null) {
                if (mData.size() > 1) {
                    Random random = new Random();
                    int index = random.nextInt(mData.size() - 1);
                    mData.add(index, reason);
                } else {
                    mData.add(reason);
                }
            }
            notifyDataSetChanged();
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
