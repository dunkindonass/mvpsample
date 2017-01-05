package kr.co.mvp.dagger2.sample.mvp.views;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.mvp.dagger2.sample.R;
import kr.co.mvp.dagger2.sample.SampleApplication;
import kr.co.mvp.dagger2.sample.dagger.component.DaggerSampleTestComponent;
import kr.co.mvp.dagger2.sample.dagger.module.ApplicationModule;
import kr.co.mvp.dagger2.sample.dagger.module.CommonUtilModule;
import kr.co.mvp.dagger2.sample.dagger.module.NetworkModule;
import kr.co.mvp.dagger2.sample.dagger.module.SampleTestVo;
import kr.co.mvp.dagger2.sample.dagger.utils.PreferenceUtil;
import kr.co.mvp.dagger2.sample.mvp.base.BaseMvpActivity;

public class MainActivity extends BaseMvpActivity {


    @Inject
    PreferenceUtil preferenceUtil;


    @Inject
    SampleTestVo sampleTestVo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);





        DaggerSampleTestComponent.builder().applicationComponent(SampleApplication.component(this)).build().inject(this);
       // init();
        getDate();


    }



    @OnClick(R.id.btn01)
    public void btnOnClick(){
        if(preferenceUtil==null){
            Log.e("PREFERENCE==null","call");
        }else{
            Log.e("PREFERENCE!=null","call");
        }

        if(sampleTestVo==null){
            Log.e("sampleTestVo==null","call");
        }else{
            Log.e("sampleTestVo!=null","call");
        }
    }
    public void init() {
        onCallFragment(new TasteListFragment(), ROOTFRAGMENT, null);
    }

    public void getDate() {
        long currentDate = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("F");
        String result = simpleDateFormat.format(new Date(currentDate));

    }


}
