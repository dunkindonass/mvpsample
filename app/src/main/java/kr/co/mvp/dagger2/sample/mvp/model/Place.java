
package kr.co.mvp.dagger2.sample.mvp.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

import lombok.Data;

@Data
public class Place {

    public String storeViewId;
    public Boolean hasStoreView;
    public Boolean isPremium;
    public Float rating;
    public String primaryPhone;
    public String tvShow;
    public Integer reviewCount;
    public String webLink;
    public Integer commentCount;
    public Boolean hasDiscountTicket;
    public String imageLink;
    public List<String> prices = new ArrayList<String>();
    public Boolean hasFreeCoupon;
    public String id;
    public String type;
    public String name;
    public Float x;
    public Float y;
    public List<String> category = new ArrayList<String>();
    public String address;
    public String streetNameAddress;
    public Float metersFromSearchPoint;
    public Integer viewLevel;
    public Boolean inView;
    public Boolean hasPathData;
    public RoadViewParams roadViewParams;
    public String brand;
    public String roadLength;
    public String roadTripDuration;

}
