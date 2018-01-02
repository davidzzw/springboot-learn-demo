package com.zzw.controller;

import com.zzw.dao.CityDao;
import com.zzw.model.City;
import com.zzw.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zzw
 * @see
 * @since 2018/1/2
 */
@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/findByName/{name}")
    public City findByName(@PathVariable("name") String name){
        return cityService.findByName(name);
    }
}
