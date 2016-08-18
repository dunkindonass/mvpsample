package kr.co.mvp.dagger2.sample.mvp.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import kr.co.mvp.dagger2.sample.R;
import kr.co.mvp.dagger2.sample.mvp.base.BaseMvpFragment;

/**
 * Created by 8454 on 2016-08-16.
 */

public class TasteWebFragment extends BaseMvpFragment {

    @Bind(R.id.webview)
    WebView webView;

    String URL;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.tastewebfragment, container, false);
        ButterKnife.bind(this, fragmentView);

        Bundle bundle = getArguments();
        if (bundle != null) {
            URL = bundle.getString("URL");
        }
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initWebView();

    }

    public void initWebView() {
        Display display = ((WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // javascript를 실행할 수 있도록 설정
        webSettings.setSupportMultipleWindows(true); // 여러개의 윈도우를 사용할 수 있도록 설정
        webSettings.setSupportZoom(false); // 확대,축소 기능을 사용할 수 있도록 설정
        webSettings.setBuiltInZoomControls(false); // 안드로이드에서 제공하는 줌 아이콘을 사용할 수 있도록 설정
        webSettings.setLoadsImagesAutomatically(true); // 웹뷰가 앱에 등록되어 있는 이미지 리소스를 자동으로 로드하도록 설정
        webSettings.setUseWideViewPort(true); // wide viewport를 사용하도록 설정
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 웹뷰가 캐시를 사용하지 않도록 설정

        ProgressDialog progressDialog = progressDialogProvider.provide();
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressDialog.show();
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressDialog.dismiss();
                super.onPageFinished(view, url);
            }

        });


        webView.loadUrl(URL);

    }


    @Override
    protected String getFragmentTag() {
        return "TasteWebFragment";
    }
}
