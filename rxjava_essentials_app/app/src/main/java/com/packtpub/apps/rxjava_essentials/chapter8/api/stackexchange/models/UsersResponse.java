package com.packtpub.apps.rxjava_essentials.chapter8.api.stackexchange.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class UsersResponse {

    @SerializedName("items")
    @Expose
    private List<User> users;
}
