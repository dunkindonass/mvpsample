package kr.co.mvp.dagger2.sample.dagger.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Resources;

import javax.inject.Inject;

import kr.co.mvp.dagger2.sample.R;

/**
 * Created by 8454 on 2016-08-09.
 */

public class ProgressDialogProvider {
    private Activity activity;

    @Inject
    Resources resources;

    public ProgressDialogProvider(Activity activity) {
        this.activity = activity;
    }

    public ProgressDialog provide() {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(activity.getString(R.string.loading_string));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        return progressDialog;
    }
}
