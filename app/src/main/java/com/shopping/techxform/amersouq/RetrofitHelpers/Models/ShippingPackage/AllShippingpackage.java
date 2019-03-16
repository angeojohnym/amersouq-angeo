
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.ShippingPackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllShippingpackage {

    @SerializedName("ship_package_id")
    @Expose
    private String shipPackageId;
    @SerializedName("package_name")
    @Expose
    private String packageName;

    public String getShipPackageId() {
        return shipPackageId;
    }

    public void setShipPackageId(String shipPackageId) {
        this.shipPackageId = shipPackageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

}
