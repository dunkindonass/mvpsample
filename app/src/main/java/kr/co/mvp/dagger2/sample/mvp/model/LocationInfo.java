
package kr.co.mvp.dagger2.sample.mvp.model;

import javax.annotation.Generated;

import lombok.Data;

@Data
public class LocationInfo {

    public String version;
    public ApiResult apiResult;
    public String query;
    public Integer pageNo;
    public Integer pageSize;
    public SearchPoint searchPoint;
    public String suggestedResultViewType;
    public String suggestedDisplayItem;
    public Statistics statistics;
    public Description description;
    public Category category;
    public SortType sortType;
    public Integer otherSearchIndex;
    public SearchItems searchItems;

}
