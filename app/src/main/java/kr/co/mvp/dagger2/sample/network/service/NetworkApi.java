package kr.co.mvp.dagger2.sample.network.service;

import kr.co.mvp.dagger2.sample.mvp.model.LocationInfo;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 8454 on 2016-08-09.
 */

public interface NetworkApi {

    @GET("/mm/v2/search/integratedSearch.json")
    Observable<LocationInfo> getLocation(@Query("q") String q,@Query("type") String type,@Query("x") String x,@Query("y") String y,@Query("ect") String ect,@Query("pageNo") String pageNo,@Query("result") String result,@Query("ct") String ct,@Query("lang") String lang,@Query("pf") String pf,@Query("reverse") String reverse,@Query("useGuide") String useGuide,@Query("qidx") String qidx,@Query("appVersion") String appVersion);

}
