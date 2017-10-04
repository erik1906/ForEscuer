package com.company.erde.forescuer.Model.VenueDetails;

import java.io.Serializable;

/**
 * Created by Erik on 28/09/2017.
 */

public class Items implements Serializable{

    private String prefix;
    private String suffic;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffic() {
        return suffic;
    }

    public void setSuffic(String suffic) {
        this.suffic = suffic;
    }
}
