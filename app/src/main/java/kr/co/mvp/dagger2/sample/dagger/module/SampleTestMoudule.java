package kr.co.mvp.dagger2.sample.dagger.module;

import dagger.Module;
import dagger.Provides;

/**
 * Created by imcheol-u on 2017. 1. 5..
 */
@Module
public class SampleTestMoudule {

    @Provides
    public SampleTestVo provideSampleTestVo(){
        return new SampleTestVo("바보");
    }
}
