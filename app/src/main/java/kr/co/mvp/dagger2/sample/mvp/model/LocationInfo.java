
package kr.co.mvp.dagger2.sample.mvp.model;

import javax.annotation.Generated;

import lombok.Data;

@Data
public class LocationInfo {

    private String version;
    private ApiResult apiResult;
    private String query;
    private Integer pageNo;
    private Integer pageSize;
    private SearchPoint searchPoint;
    private String suggestedResultViewType;
    private String suggestedDisplayItem;
    private Statistics statistics;
    private Description description;
    private Category category;
    private SortType sortType;
    private Integer otherSearchIndex;
    private SearchItems searchItems;

}
