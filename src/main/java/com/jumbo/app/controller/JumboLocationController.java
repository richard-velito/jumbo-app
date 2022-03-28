package com.jumbo.app.controller;

import com.jumbo.app.model.Store;
import com.jumbo.app.service.JumboLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class JumboLocationController {

    private final JumboLocationService jumboLocationService;

    @Autowired
    public JumboLocationController(JumboLocationService jumboLocationService) {
        this.jumboLocationService = jumboLocationService;
    }

    @GetMapping("/closets-location/{uuid}")
    public List<Store> getClosetsLocationByStore(@PathVariable("uuid") String uuid) {
        return jumboLocationService.getClosetsPosition (uuid);
    }

    @GetMapping("/closets-location/{longitude}/{latitude}")
    public List<Store> getClosetsLocationByStore(
            @PathVariable("longitude") double longitude, @PathVariable("latitude") double latitude) {
        return jumboLocationService.getClosetsPosition (longitude, latitude);
    }

    @PostMapping("/create")
    public Store create(@RequestBody Store store) {
        return jumboLocationService.addStore(store);
    }

}
