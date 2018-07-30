package com.example.mydemo.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.example.mydemo.R;
import com.example.mydemo.activity.SmileViewActivity;
import com.example.mydemo.activity.SmileViewActivity2;

/**
 * author : 宋佳
 * time   : 2018/07/25
 * desc   :
 * version: 1.0.0
 */

public class SmileFragment extends Fragment {


    @LayoutRes
    int sampleLayoutRes;
    private LinearLayout backGround;
    private SeekBar seekBar;
    private ImageView smileFace;

    public static SmileFragment newInstance(@LayoutRes int sampleLayoutRes) {
        SmileFragment fragment = new SmileFragment();
        Bundle args = new Bundle();
        args.putInt("sampleLayoutRes", sampleLayoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_smile, container, false);
        backGround = (LinearLayout) view.findViewById(R.id.backGround);
        smileFace = (ImageView) view.findViewById(R.id.smileFace);
        seekBar = (SeekBar) view.findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) smileFace.getLayoutParams();
                layoutParams.bottomMargin = progress * 3;
                smileFace.setLayoutParams(layoutParams);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        view.findViewById(R.id.btn_smileView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SmileViewActivity.class);
                getActivity().startActivity(intent);
            }
        });

        view.findViewById(R.id.btn_smileView2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SmileViewActivity2.class);
                getActivity().startActivity(intent);
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
