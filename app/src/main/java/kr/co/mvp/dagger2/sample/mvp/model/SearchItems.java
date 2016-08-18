
package kr.co.mvp.dagger2.sample.mvp.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SearchItems {

    private List<Object> addresses = new ArrayList<Object>();
    private List<Object> busStops = new ArrayList<Object>();
    private List<Place> places = new ArrayList<Place>();
    private List<Object> busLines = new ArrayList<Object>();

}
