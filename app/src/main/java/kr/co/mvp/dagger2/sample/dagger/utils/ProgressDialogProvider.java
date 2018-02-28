package kr.co.mvp.dagger2.sample.dagger.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;

import javax.inject.Inject;

import kr.co.mvp.dagger2.sample.R;

/**
 * Created by 8454 on 2016-08-09.
 */

public class ProgressDialogProvider {
    private Context context;

    public ProgressDialogProvider(Context cxt) {
        this.context = cxt;
    }

    public ProgressDialog provide() {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(context.getString(R.string.loading_string));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        return progressDialog;
    }
}
