package kr.co.mvp.dagger2.sample.mvp.views.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.mvp.dagger2.sample.R;
import kr.co.mvp.dagger2.sample.mvp.base.BaseMvpActivity;
import kr.co.mvp.dagger2.sample.mvp.base.BaseMvpFragment;
import kr.co.mvp.dagger2.sample.mvp.presenter.GithubReopView;
import kr.co.mvp.dagger2.sample.mvp.presenter.GithubRepoPresenterImpl;
import kr.co.mvp.dagger2.sample.mvp.views.adapter.GithubReopRecyclerAdapter;

/**
 * Created by a8454 on 2018. 3. 2..
 */

public class GithubRepoFragment extends BaseMvpFragment implements GithubReopView, AdapterView.OnItemSelectedListener {

    Context context;

    @BindView(R.id.repo_input_text)
    TextView repoInputText;

    @Inject
    GithubRepoPresenterImpl githubRepoPresenter;

    @BindView(R.id.github_repo_recyler_view)
    RecyclerView githubRepoRecylerView;

    @BindView(R.id.sorting_spinner)
    Spinner sortingSpinner;
    @BindView(R.id.order_spinner)
    Spinner orderSpinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_githubrepo, container, false);
        context = fragmentView.getContext();
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
    }

    public void init() {
        githubRepoPresenter.attach(this);

        sortingSpinner.setOnItemSelectedListener(this);
        orderSpinner.setOnItemSelectedListener(this);
    }

    @OnClick(R.id.repo_search_btn)
    public void onClickSearchBtn() {
        requestGithubList();
        hideKeyBoard();
    }

    public void requestGithubList() {
        if (repoInputText.getText().toString().length() == 0) {
            Toast.makeText(context, "Please enter a search term", Toast.LENGTH_LONG).show();
        } else {
            String searchText = repoInputText.getText().toString();
            String sortText = sortingSpinner.getSelectedItem().toString();
            String orderText = orderSpinner.getSelectedItem().toString();
            githubRepoPresenter.getReopList(searchText, sortText, orderText);
        }
    }

    public void hideKeyBoard() {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(repoInputText.getWindowToken(), 0);
    }

    @Override
    protected String getFragmentTag() {
        return "GithubRepoFragment";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void showGithubRepoList(GithubReopRecyclerAdapter githubReopRecyclerAdapter) {
        if (githubRepoRecylerView.getAdapter() == null) {
            githubRepoRecylerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            githubReopRecyclerAdapter.setListener(onItemSelectedCallBack);
            githubRepoRecylerView.setAdapter(githubReopRecyclerAdapter);
        } else {
            githubReopRecyclerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (repoInputText.getText().toString().length() > 0) {
            requestGithubList();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    GithubReopRecyclerAdapter.OnItemSelectedCallBack onItemSelectedCallBack = new GithubReopRecyclerAdapter.OnItemSelectedCallBack() {
        @Override
        public void onSelected(String url) {
            Bundle bundle = new Bundle();
            bundle.putString("URL", url);
            ((BaseMvpActivity) parentActivity).onCallFragment(R.id.container_layout, new GithubWebFragment(), null, bundle);

        }
    };
}
