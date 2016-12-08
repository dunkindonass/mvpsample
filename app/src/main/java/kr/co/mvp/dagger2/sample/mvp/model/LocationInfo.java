
package kr.co.mvp.dagger2.sample.mvp.model;


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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ApiResult getApiResult() {
        return apiResult;
    }

    public void setApiResult(ApiResult apiResult) {
        this.apiResult = apiResult;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public SearchPoint getSearchPoint() {
        return searchPoint;
    }

    public void setSearchPoint(SearchPoint searchPoint) {
        this.searchPoint = searchPoint;
    }

    public String getSuggestedResultViewType() {
        return suggestedResultViewType;
    }

    public void setSuggestedResultViewType(String suggestedResultViewType) {
        this.suggestedResultViewType = suggestedResultViewType;
    }

    public String getSuggestedDisplayItem() {
        return suggestedDisplayItem;
    }

    public void setSuggestedDisplayItem(String suggestedDisplayItem) {
        this.suggestedDisplayItem = suggestedDisplayItem;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    public Integer getOtherSearchIndex() {
        return otherSearchIndex;
    }

    public void setOtherSearchIndex(Integer otherSearchIndex) {
        this.otherSearchIndex = otherSearchIndex;
    }

    public SearchItems getSearchItems() {
        return searchItems;
    }

    public void setSearchItems(SearchItems searchItems) {
        this.searchItems = searchItems;
    }
}
