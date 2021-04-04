package com.trainersstocks.CustomersApp.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.abstractactivity.MyAbstractFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import im.delight.android.webview.AdvancedWebView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LiveMarketFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LiveMarketFragment extends MyAbstractFragment implements AdvancedWebView.Listener {

    @BindView(R.id.webview)
    AdvancedWebView mWebView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LiveMarketFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LiveMarketFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LiveMarketFragment newInstance(String param1, String param2) {
        LiveMarketFragment fragment = new LiveMarketFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live_market, container, false);
        ButterKnife.bind(this, view);
        mWebView.setListener(getActivity(), this);
        mWebView.setMixedContentAllowed(false);
        mWebView.loadUrl("https://www1.nseindia.com/live_market/dynaContent/live_market.htm");


        return view;
    }

    @Override
    public void onPause() {
        mWebView.onPause();
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();
        mWebView.onResume();
    }


    @Override
    public void onDestroy() {
        mWebView.destroy();
        super.onDestroy();

    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {

    }

    @Override
    public void onPageFinished(String url) {

    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {

    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }

    @Override
    public void onExternalPageRequest(String url) {

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mWebView.onActivityResult(requestCode, resultCode, data);


    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public void initlistners() {

    }
}