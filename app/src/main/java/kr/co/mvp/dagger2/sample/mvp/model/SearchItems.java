
package kr.co.mvp.dagger2.sample.mvp.model;

import java.util.ArrayList;
import java.util.List;



public class SearchItems {
    public List<Object> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Object> addresses) {
        this.addresses = addresses;
    }

    public List<Object> getBusStops() {
        return busStops;
    }

    public void setBusStops(List<Object> busStops) {
        this.busStops = busStops;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public List<Object> getBusLines() {
        return busLines;
    }

    public void setBusLines(List<Object> busLines) {
        this.busLines = busLines;
    }

    private List<Object> addresses = new ArrayList<Object>();
    private List<Object> busStops = new ArrayList<Object>();
    private List<Place> places = new ArrayList<Place>();
    private List<Object> busLines = new ArrayList<Object>();

}
