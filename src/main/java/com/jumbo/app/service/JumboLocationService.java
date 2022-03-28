package com.jumbo.app.service;

import com.jumbo.app.model.Store;

import java.util.List;

public interface JumboLocationService {

    /* closets positions to a specific store */
    List<Store> getClosetsPosition(String uuid);

    List<Store> getClosetsPosition(double longitude, double latitude);

    Store addStore(Store store);
}
