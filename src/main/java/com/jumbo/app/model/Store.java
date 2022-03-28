package com.jumbo.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Store {

    String uuid;
    Double longitude;
    Double latitude;
    String city;
    String postalCode;
    String street;
    String addressName;
    String todayOpen;
    String todayClose;

    public Store() { }

    public Store(
            String uuid, double longitude, double latitude, String city, String postalCode,
            String street, String addressName, String todayOpen, String todayClose) {
        this.uuid = uuid;
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.addressName = addressName;
        this.todayOpen = todayOpen;
        this.todayClose = todayClose;
    }

    public String getUuid() {
        return uuid;
    }

    public Store setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Store setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Store setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Store setCity(String city) {
        this.city = city;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Store setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public Store setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getAddressName() {
        return addressName;
    }

    public Store setAddressName(String addressName) {
        this.addressName = addressName;
        return this;
    }

    public String getTodayOpen() {
        return todayOpen;
    }

    public Store setTodayOpen(String todayOpen) {
        this.todayOpen = todayOpen;
        return this;
    }

    public String getTodayClose() {
        return todayClose;
    }

    public Store setTodayClose(String todayClose) {
        this.todayClose = todayClose;
        return this;
    }

    @Override
    public String toString() {
        return "Store{" +
                "uuid='" + uuid + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", street='" + street + '\'' +
                ", addressName='" + addressName + '\'' +
                ", todayOpen='" + todayOpen + '\'' +
                ", todayClose='" + todayClose + '\'' +
                '}';
    }
}
