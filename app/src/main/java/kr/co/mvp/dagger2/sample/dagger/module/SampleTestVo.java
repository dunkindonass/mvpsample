package kr.co.mvp.dagger2.sample.dagger.module;

/**
 * Created by imcheol-u on 2017. 1. 5..
 */

public class SampleTestVo {

    public SampleTestVo(String testVo) {
        this.testVo = testVo;
    }

    public String getTestVo() {
        return testVo;
    }

    public void setTestVo(String testVo) {
        this.testVo = testVo;
    }

    private String testVo;

}
