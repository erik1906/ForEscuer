package com.company.erde.forescuer.Model.VenuesSearch;

import java.io.Serializable;

/**
 * Created by Erik on 22/09/2017.
 */

public class CatIcon implements Serializable{

    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
