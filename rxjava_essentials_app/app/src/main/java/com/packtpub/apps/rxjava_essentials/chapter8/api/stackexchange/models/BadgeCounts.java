package com.packtpub.apps.rxjava_essentials.chapter8.api.stackexchange.models;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class BadgeCounts {

    @Expose
    private Integer bronze;

    @Expose
    private Integer silver;

    @Expose
    private Integer gold;

}