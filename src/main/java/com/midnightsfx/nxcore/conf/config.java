package com.midnightsfx.nxcore.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MidnightsFX on 7/28/17.
 */
@ConfigurationProperties(prefix="endpoints")
public class config {
    private List<String> endpoints = new ArrayList<String>();

    public List<String> getEndpoints() {
        return this.endpoints;
    }

}
