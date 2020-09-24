package com.cricket.packages.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties("envirnoment")
public class EnvironmentVariables {
    private int globalPts;

    public int getGlobalPts() {
        return globalPts;
    }

    public void setGlobalPts(int globalPts) {
        this.globalPts = globalPts;
    }
}
