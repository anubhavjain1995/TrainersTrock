package com.trainersstocks.CustomersApp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.trainersstocks.CustomersApp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutUsFragment extends Fragment {
    private String url = "";

    @BindView(R.id.webview)
    WebView webView;

    @BindView(R.id.bar)
    ProgressBar bar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_aboutus, container, false);
        ButterKnife.bind(this, root);
        webView.loadUrl(url);
        webView.setWebChromeClient(new MyWebViewClient());
        bar.setVisibility(View.VISIBLE);
        return root;

    }


    private class MyWebViewClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress > 60) {
                bar.setVisibility(View.GONE);
            }
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        url = bundle.getString("URL");
    }
}