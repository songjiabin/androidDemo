package com.example.mydemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydemo.R;
import com.example.mydemo.view.EvaluationRatingBar;

import es.dmoral.toasty.Toasty;

/**
 * author : 宋佳
 * time   : 2018/07/18
 * desc   :
 * version: 1.0.0
 */

public class EvaluationRatingFragment extends Fragment {


    private final String[] mDescriptions = new String[]{"非常不满意", "不满意，请积极改善", "一般，还需提升", "满意，服务不错", "非常满意，一百个赞"};


    @LayoutRes
    int sampleLayoutRes;
    private TextView textview;

    public static EvaluationRatingFragment newInstance(@LayoutRes int sampleLayoutRes) {
        EvaluationRatingFragment fragment = new EvaluationRatingFragment();
        Bundle args = new Bundle();
        args.putInt("sampleLayoutRes", sampleLayoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_evaluation_rationbar, container, false);
        EvaluationRatingBar evaluationRatingBar = (EvaluationRatingBar) view.findViewById(R.id.evaluartion);
        textview = (TextView) view.findViewById(R.id.textView);
        evaluationRatingBar.setOnRatingChangeListener(new EvaluationRatingBar.OnRatingChangeListener() {
            @Override
            public void onChange(int electedCount) {
                if (textview.getVisibility() == View.GONE) {
                    textview.setVisibility(View.VISIBLE);
                }
                if (electedCount > 0 && electedCount <= mDescriptions.length) {
                    textview.setText(mDescriptions[electedCount - 1]);
                }

                Toasty.info(getActivity(), "starCount:\t" + electedCount, Toast.LENGTH_SHORT, false).show();

            }
        });

        return view;
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
