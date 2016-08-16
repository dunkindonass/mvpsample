
package kr.co.mvp.dagger2.sample.mvp.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

import lombok.Data;

@Data
public class SearchItems {

    public List<Object> addresses = new ArrayList<Object>();
    public List<Object> busStops = new ArrayList<Object>();
    public List<Place> places = new ArrayList<Place>();
    public List<Object> busLines = new ArrayList<Object>();

}
