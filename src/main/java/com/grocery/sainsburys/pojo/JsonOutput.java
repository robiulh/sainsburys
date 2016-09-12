package com.grocery.sainsburys.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by rhuss on 11/09/2016.
 */
public class JsonOutput {

    private List<Product> results;
    private BigDecimal totalOfAllItems;

    public List<Product> getResults() {
        return results;
    }

    public void setResults(List<Product> results) {
        this.results = results;
    }
    //using annotation to specify the output for
    // how the naming of the total of all items should be shown in the json
    @JsonProperty("total")
    public final BigDecimal getTotalOfAllItems() {

        return totalOfAllItems;
    }

    public void setTotalOfAllItems(BigDecimal totalOfAllItems) {
        this.totalOfAllItems = totalOfAllItems;
    }
}
