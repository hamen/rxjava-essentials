
package com.packtpub.apps.rxjava_essentials.chapter8.api.openweathermap.models;

import com.google.gson.annotations.Expose;

public class Clouds {

    @Expose
    private Integer all;

    /**
     * 
     * @return
     *     The all
     */
    public Integer getAll() {
        return all;
    }

    /**
     * 
     * @param all
     *     The all
     */
    public void setAll(Integer all) {
        this.all = all;
    }

}
