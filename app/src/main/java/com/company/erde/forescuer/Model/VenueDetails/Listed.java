package com.company.erde.forescuer.Model.VenueDetails;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Erik on 28/09/2017.
 */

public class Listed implements Serializable{

    @SerializedName("groups")
    GroupsDesc[] groupsDesc;

    public GroupsDesc[] getGroupsDesc() {
        return groupsDesc;
    }

    public void setGroupsDesc(GroupsDesc[] groupsDesc) {
        this.groupsDesc = groupsDesc;
    }
}
