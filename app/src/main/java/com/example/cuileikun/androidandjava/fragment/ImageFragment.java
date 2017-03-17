package com.example.cuileikun.androidandjava.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cuileikun.androidandjava.R;
import com.qk.applibrary.widget.DownloadImageView;


/**
 * Created by april on 2016/11/10.
 */

public class ImageFragment extends Fragment {

    private static final String TAG = ImageFragment.class.getSimpleName();
    private DownloadImageView imageView;

    private String url;

    public static final String IMAGE_URL = "IMAGE_URL";

//    private OnItemClickListener onItemClickListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getArguments().getString(IMAGE_URL);
//        if (getActivity() instanceof OnItemClickListener) {
//            onItemClickListener = (OnItemClickListener) getActivity();
//        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_fragment, container, false);
        imageView = (DownloadImageView) view.findViewById(R.id.iv_photo);
        imageView.loadNativePic(url);
        imageView.loadNetworkPic(url);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return view;
    }
}
