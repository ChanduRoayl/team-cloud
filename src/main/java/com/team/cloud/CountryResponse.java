package com.team.cloud;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

public class CountryResponse {
    private List<Country> data;

    public List<Country> getData() {
        return data;
    }

    public void setData(List<Country> data) {
        this.data = data;
    }
}
