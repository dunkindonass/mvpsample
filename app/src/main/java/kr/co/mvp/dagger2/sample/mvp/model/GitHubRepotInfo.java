package kr.co.mvp.dagger2.sample.mvp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a8454 on 2018. 3. 2..
 */

public class GitHubRepotInfo {

    private int total_count;
    private Boolean incomplete_results;
    private List<Item> items = new ArrayList<>();

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public Boolean getIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(Boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
