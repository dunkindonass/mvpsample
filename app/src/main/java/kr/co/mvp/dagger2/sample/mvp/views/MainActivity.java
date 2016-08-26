package kr.co.mvp.dagger2.sample.mvp.views;

import android.os.Bundle;

import kr.co.mvp.dagger2.sample.R;
import kr.co.mvp.dagger2.sample.SampleApplication;
import kr.co.mvp.dagger2.sample.dagger.component.ApplicationComponent;
import kr.co.mvp.dagger2.sample.dagger.module.ActivityModoule;
import kr.co.mvp.dagger2.sample.mvp.base.BaseMvpActivity;
import rx.Subscriber;
import rx.subjects.BehaviorSubject;

public class MainActivity extends BaseMvpActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init() {
        onCallFragment(new TasteListFragment(),ROOTFRAGMENT, null);

    }


    public void testBehavierSubject01(){
        BehaviorSubject<String> subject = BehaviorSubject.create();
        subject.filter(text->!text.equals("aaa")).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        });
    }
    public void onInjectMainActivity() {
        ((ApplicationComponent) SampleApplication.getApplicationComponent()).addActivityComponent(new ActivityModoule(this)).inject(this);
    }

}
