package kr.co.mvp.dagger2.sample.nondagger;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.mvp.dagger2.sample.R;
import kr.co.mvp.dagger2.sample.dagger.module.FragmentMoudule;
import kr.co.mvp.dagger2.sample.mvp.base.BaseMvpActivity;
import kr.co.mvp.dagger2.sample.mvp.base.BaseMvpFragment;
import kr.co.mvp.dagger2.sample.mvp.database.DataHelper;
import kr.co.mvp.dagger2.sample.mvp.database.vo.UserInfo;
import kr.co.mvp.dagger2.sample.mvp.model.LocationInfo;
import kr.co.mvp.dagger2.sample.mvp.model.Place;
import kr.co.mvp.dagger2.sample.mvp.presenter.TasteListPresenterImpl;
import kr.co.mvp.dagger2.sample.mvp.views.TasteRecyclerAdapter;
import kr.co.mvp.dagger2.sample.mvp.views.TasteWebFragment;
import kr.co.mvp.dagger2.sample.network.service.NetworkApi;
import kr.co.mvp.dagger2.sample.network.service.NetworkManager;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by imcheol-u on 2017. 1. 2..
 */

public class TasteListNonDaggerfragment extends BaseFragment implements View.OnClickListener{



    Context mContext;


    RecyclerView recyclerView;

    TextView numbertext;

    private ArrayList<Place> searchItems = new ArrayList<>();
    private int index = 1;
    private boolean Loading;
    private int visibleHold = 2;


    private DataHelper dataHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.tastelistfragment, container, false);
        ButterKnife.bind(this, fragmentView);
        mContext=fragmentView.getContext();
        return fragmentView;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        init();
        initRecyclerView();

    }

    public void init(){
        dataHelper=new DataHelper(mContext);

        recyclerView=(RecyclerView)getView().findViewById(R.id.recyler_view);
        numbertext=(TextView)getView().findViewById(R.id.text);

        ((Button)getView().findViewById(R.id.btn)).setOnClickListener(this);
        ((Button)getView().findViewById(R.id.btn02)).setOnClickListener(this);
        ((Button)getView().findViewById(R.id.btn03)).setOnClickListener(this);
    }

    public void initRecyclerView() {
        recyclerView.setAdapter(new TasteRecyclerAdapter(parentActivity, searchItems));
        recyclerView.setLayoutManager(new LinearLayoutManager(parentActivity));
        TasteRecyclerAdapter tasteRecyclerAdapter = (TasteRecyclerAdapter) recyclerView.getAdapter();
        if (tasteRecyclerAdapter != null) {
            tasteRecyclerAdapter.setItemClickCallback(new TasteRecyclerAdapter.ItemClick() {
                @Override
                public void callWebView(String url) {
                    if (url == null || url.isEmpty()) {
                        Toast.makeText(parentActivity, getActivity().getResources().getString(R.string.weblink_blank), Toast.LENGTH_SHORT).show();
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putString("URL", url);
                        ((BaseMvpActivity) parentActivity).onCallFragment(new TasteWebFragment(), null, bundle);
                    }

                }
            });
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItemCount = linearLayoutManager.getItemCount();
                int lastVisibleCount = linearLayoutManager.findLastVisibleItemPosition();
                if (!Loading && totalItemCount <= (lastVisibleCount + visibleHold)) {
                    index++;
                    Loading = true;
                    requestGithubData(index);
                }
            }
        });
        requestGithubData(index);
    }

    public void requestGithubData(int idx) {

        Observable<LocationInfo> locationSearch = null;

        ProgressDialog progressDialog=progressDialogProvider.provide();
        progressDialog.show();

        NetworkApi networkApi= NetworkManager.getInstance(parentActivity).kumonNetworkService();
        locationSearch = networkApi.getLocation("맛집", "", "OUyULY", "LTr_CV", "1", idx + "", "15", "wcong", "ko", "android", "-1", "true", "1", "3.9.13");

        locationSearch.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<LocationInfo>() {
            @Override
            public void onCompleted() {
                progressDialog.dismiss();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                progressDialog.dismiss();
            }

            @Override
            public void onNext(LocationInfo s) {
                showData(s);
            }
        });


    }

    public void showData(LocationInfo data){


        Loading = false;
        searchItems.addAll(data.getSearchItems().getPlaces());
        TasteRecyclerAdapter tasteRecyclerAdapter = (TasteRecyclerAdapter) recyclerView.getAdapter();
        tasteRecyclerAdapter.notifyDataSetChanged();
    }


    public List<UserInfo> selectAll() throws SQLException {

        Dao<UserInfo, Integer> userInfoIntegerDao = null;
        userInfoIntegerDao = dataHelper.getUserDao();
        QueryBuilder<UserInfo, Integer> qb = userInfoIntegerDao.queryBuilder();
        return (List<UserInfo>) userInfoIntegerDao.query(qb.prepare());

    }


    public List<UserInfo> selectWhere(String userclass)throws SQLException  {

        Dao<UserInfo, Integer> userInfoIntegerDao = null;
        userInfoIntegerDao = dataHelper.getUserDao();
        QueryBuilder<UserInfo, Integer> qb = userInfoIntegerDao.queryBuilder();
        qb.where().eq("userclass", userclass);
        return (List<UserInfo>) userInfoIntegerDao.query(qb.prepare());

    }

    public void addData(){
        UserInfo userInfo=new UserInfo();

        userInfo.setUserage(System.currentTimeMillis()+"age");
        userInfo.setUserclass(System.currentTimeMillis()+"class");
        userInfo.setUserid(System.currentTimeMillis()+"id");
        userInfo.setUsername(System.currentTimeMillis()+"name");

        try {
            Dao<UserInfo,Integer> userInfoDao=dataHelper.getUserDao();
            userInfoDao.createOrUpdate(userInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getFragmentTag() {
        return "TasteListFragments";
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn:
                addData();
                break;

            case R.id.btn02:
                try {
                    List<UserInfo> userInfos=selectAll();

                    if(userInfos.size()>0){
                        //etc logic..
                        Log.e("userInfos::",new Gson().toJson(userInfos));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.btn03:
                try {
                    List<UserInfo> selectInfos=selectWhere("1483328479487class");
                    if(selectInfos.size()>0){
                        //etc logic..
                        Log.e("userInfos::",new Gson().toJson(selectInfos));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

        }
    }
}
