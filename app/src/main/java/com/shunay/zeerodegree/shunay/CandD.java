package com.example.bluedot.example;
/**
 * Created by z on 4/20/2017.
 */
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class CandD  extends Fragment {
    private WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.candd, container, false);
        if (CheckNetwork.isInternetAvailable(getActivity())) {
            webView = (WebView) rootView.findViewById(R.id.webView2);
            webView.loadUrl("https://stackoverflow.com/");
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.setWebViewClient(new WebViewClient() {
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    String url2 = "https://stackoverflow.com/";
                    if (url != null && url.startsWith(url2)) {
                        return false;
                    } else {
                        view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                        return true;
                    }
                }
            });

        }else{
            new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Oops")
                    .setContentText("You don't have internet connection!")
                    .setConfirmText("Refresh")
                    .setCancelText("OK")
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            getActivity().finish();

                        }
                    })
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            startActivity(new Intent(getActivity(),MainActivity.class));
                        }
                    })
                    .show();
        }
        return rootView;
    }
}
