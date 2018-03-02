package kr.co.mvp.dagger2.sample.mvp.views;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import kr.co.mvp.dagger2.sample.R;
import kr.co.mvp.dagger2.sample.dagger.module.FragmentMoudule;
import kr.co.mvp.dagger2.sample.mvp.base.BaseMvpActivity;
import kr.co.mvp.dagger2.sample.mvp.base.BaseMvpFragment;
import kr.co.mvp.dagger2.sample.mvp.database.vo.UserInfo;
import kr.co.mvp.dagger2.sample.mvp.model.LocationInfo;
import kr.co.mvp.dagger2.sample.mvp.model.Place;
import kr.co.mvp.dagger2.sample.mvp.presenter.TasteListPresenterImpl;
import kr.co.mvp.dagger2.sample.mvp.presenter.TasteListView;

/**
 * Created by 8454 on 2016-08-16.
 */

public class TasteListFragment extends BaseMvpFragment implements TasteListView {

    @Inject
    Resources resources;
    Context context;

    @BindView(R.id.input_text)
    EditText inputText;

    @Inject
    TasteListPresenterImpl githubListPresenter;


    @BindView(R.id.recyler_view)
    RecyclerView recyclerView;


    private ArrayList<Place> searchItems = new ArrayList<>();
    private int index = 1;
    private boolean Loading;
    private int visibleHold = 2;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.tastelistfragment, container, false);
        context=fragmentView.getContext();
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);


        init();
        githubListPresenter.attach(this);
        //githubListPresenter.requestGithubData(index);
    }


    @OnClick(R.id.search_btn)
    public void onSearchClick(){
        if (inputText.getText().toString().length() == 0){
            Toast.makeText(context, "입력하세요",Toast.LENGTH_SHORT).show();
        }else{
            String searchText = inputText.getText().toString();
            githubListPresenter.requestGithubData(searchText,0);
        }
    }


    public void init() {
        recyclerView.setAdapter(new TasteRecyclerAdapter(context, searchItems));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        TasteRecyclerAdapter tasteRecyclerAdapter = (TasteRecyclerAdapter) recyclerView.getAdapter();
        if (tasteRecyclerAdapter != null) {
            tasteRecyclerAdapter.setItemClickCallback(new TasteRecyclerAdapter.ItemClick() {
                @Override
                public void callWebView(String url) {
                    if (url == null || url.isEmpty()) {
                        Toast.makeText(parentActivity, getResources().getString(R.string.weblink_blank), Toast.LENGTH_SHORT).show();
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putString("URL", url);
                        ((BaseMvpActivity) parentActivity).onCallFragment(R.id.container_layout,new TasteWebFragment(), null, bundle);
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
                    tasteRequest();
                }
            }
        });
    }

    public void tasteRequest() {
        if (inputText.getText().toString().length() == 0){
            Toast.makeText(context, "입력하세요",Toast.LENGTH_SHORT).show();
        }else{
            String searchText = inputText.getText().toString();
            githubListPresenter.requestGithubData(searchText,index);
        }

    }

    public void select(String userclass){
        githubListPresenter.selectUserInfo(userclass);
    }

    @Override
    public void showData(LocationInfo data) {
        Loading = false;
        searchItems.addAll(data.getSearchItems().getPlaces());
        TasteRecyclerAdapter tasteRecyclerAdapter = (TasteRecyclerAdapter) recyclerView.getAdapter();
        tasteRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void getUserInfo(List<UserInfo> userInfoList) {

        Log.e("userInfoList",new Gson().toJson(userInfoList));
    }

    @Override
    public void setRecyclerView(TasteRecyclerAdapter tasteRecyclerAdapter) {

        if(recyclerView.getAdapter()==null){
            recyclerView.setAdapter(tasteRecyclerAdapter);
        }else{
            tasteRecyclerAdapter.notifyDataSetChanged();
        }

    }

    @Override
    protected String getFragmentTag() {
        return "TasteListFragments";
    }
}
