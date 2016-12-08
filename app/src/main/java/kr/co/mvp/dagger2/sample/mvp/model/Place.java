
package kr.co.mvp.dagger2.sample.mvp.model;

import java.util.ArrayList;
import java.util.List;



public class Place {

    private String storeViewId;
    private Boolean hasStoreView;
    private Boolean isPremium;
    private Float rating;
    private String primaryPhone;
    private String tvShow;
    private Integer reviewCount;
    private String webLink;
    private Integer commentCount;
    private Boolean hasDiscountTicket;
    private String imageLink;
    private List<String> prices = new ArrayList<String>();
    private Boolean hasFreeCoupon;
    private String id;
    private String type;
    private String name;
    private Float x;
    private Float y;
    private List<String> category = new ArrayList<String>();
    private String address;
    private String streetNameAddress;
    private Float metersFromSearchPoint;
    private Integer viewLevel;
    private Boolean inView;
    private Boolean hasPathData;
    private RoadViewParams roadViewParams;
    private String brand;
    private String roadLength;
    private String roadTripDuration;

    public String getStoreViewId() {
        return storeViewId;
    }

    public void setStoreViewId(String storeViewId) {
        this.storeViewId = storeViewId;
    }

    public Boolean getHasStoreView() {
        return hasStoreView;
    }

    public void setHasStoreView(Boolean hasStoreView) {
        this.hasStoreView = hasStoreView;
    }

    public Boolean getPremium() {
        return isPremium;
    }

    public void setPremium(Boolean premium) {
        isPremium = premium;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    public String getTvShow() {
        return tvShow;
    }

    public void setTvShow(String tvShow) {
        this.tvShow = tvShow;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Boolean getHasDiscountTicket() {
        return hasDiscountTicket;
    }

    public void setHasDiscountTicket(Boolean hasDiscountTicket) {
        this.hasDiscountTicket = hasDiscountTicket;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public List<String> getPrices() {
        return prices;
    }

    public void setPrices(List<String> prices) {
        this.prices = prices;
    }

    public Boolean getHasFreeCoupon() {
        return hasFreeCoupon;
    }

    public void setHasFreeCoupon(Boolean hasFreeCoupon) {
        this.hasFreeCoupon = hasFreeCoupon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreetNameAddress() {
        return streetNameAddress;
    }

    public void setStreetNameAddress(String streetNameAddress) {
        this.streetNameAddress = streetNameAddress;
    }

    public Float getMetersFromSearchPoint() {
        return metersFromSearchPoint;
    }

    public void setMetersFromSearchPoint(Float metersFromSearchPoint) {
        this.metersFromSearchPoint = metersFromSearchPoint;
    }

    public Integer getViewLevel() {
        return viewLevel;
    }

    public void setViewLevel(Integer viewLevel) {
        this.viewLevel = viewLevel;
    }

    public Boolean getInView() {
        return inView;
    }

    public void setInView(Boolean inView) {
        this.inView = inView;
    }

    public Boolean getHasPathData() {
        return hasPathData;
    }

    public void setHasPathData(Boolean hasPathData) {
        this.hasPathData = hasPathData;
    }

    public RoadViewParams getRoadViewParams() {
        return roadViewParams;
    }

    public void setRoadViewParams(RoadViewParams roadViewParams) {
        this.roadViewParams = roadViewParams;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getRoadLength() {
        return roadLength;
    }

    public void setRoadLength(String roadLength) {
        this.roadLength = roadLength;
    }

    public String getRoadTripDuration() {
        return roadTripDuration;
    }

    public void setRoadTripDuration(String roadTripDuration) {
        this.roadTripDuration = roadTripDuration;
    }
}
