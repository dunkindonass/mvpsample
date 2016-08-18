
package kr.co.mvp.dagger2.sample.mvp.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
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

}
