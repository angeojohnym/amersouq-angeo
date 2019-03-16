
package com.shopping.techxform.amersouq.RetrofitHelpers.Models.LoginOutputModule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResult {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("profile_pic")
    @Expose
    private String profilePic;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("iemi")
    @Expose
    private String iemi;
    @SerializedName("contact_number")
    @Expose
    private String contactNumber;
    @SerializedName("register_token")
    @Expose
    private String registerToken;
    @SerializedName("postal_code")
    @Expose
    private String postalCode;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("country_code")
    @Expose
    private String countryCode;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("is_profile_completed")
    @Expose
    private Boolean isProfileCompleted;
    @SerializedName("package_end_date")
    @Expose
    private String packageEndDate;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("package")
    @Expose
    private Integer _package;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("package_start_date")
    @Expose
    private String packageStartDate;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("email")
    @Expose
    private String email;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIemi() {
        return iemi;
    }

    public void setIemi(String iemi) {
        this.iemi = iemi;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getRegisterToken() {
        return registerToken;
    }

    public void setRegisterToken(String registerToken) {
        this.registerToken = registerToken;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsProfileCompleted() {
        return isProfileCompleted;
    }

    public void setIsProfileCompleted(Boolean isProfileCompleted) {
        this.isProfileCompleted = isProfileCompleted;
    }

    public String getPackageEndDate() {
        return packageEndDate;
    }

    public void setPackageEndDate(String packageEndDate) {
        this.packageEndDate = packageEndDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getPackage() {
        return _package;
    }

    public void setPackage(Integer _package) {
        this._package = _package;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPackageStartDate() {
        return packageStartDate;
    }

    public void setPackageStartDate(String packageStartDate) {
        this.packageStartDate = packageStartDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
