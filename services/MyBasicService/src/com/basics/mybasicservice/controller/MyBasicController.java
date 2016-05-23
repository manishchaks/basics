/**This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
package com.basics.mybasicservice.controller;

import com.basics.mybasicservice.MyBasicService;
import java.lang.String;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import com.wordnik.swagger.annotations.*;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;

@RestController
@RequestMapping(value = "/myBasic", produces = { "application/json", "application/xml" })
public class MyBasicController {

    @Autowired
    private MyBasicService myBasicService;

    @RequestMapping(value = "/associateTypesWithPerson", produces = "application/json", method = RequestMethod.GET)
    public String associateTypesWithPerson(@RequestParam(value = "name", required = false) String name) {
        return myBasicService.associateTypesWithPerson(name);
    }
}