/**This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
package com.basics.mybasicservice.controller;

import com.basics.mybasicservice.MyBasicService;
import com.basics.basicsdb.Person;
import com.basics.basicsdb.Type;
import java.util.ArrayList;
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

    @RequestMapping(value = "/associateTypesWithPerson", produces = "application/json", method = RequestMethod.POST, consumes = "multipart/form-data")
    public String associateTypesWithPerson(@RequestPart(value = "person") Person person, @RequestPart ArrayList<Type> types) {
        return myBasicService.associateTypesWithPerson(person, types);
    }

    @RequestMapping(value = "/checkPersonTypeExists", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean checkPersonTypeExists(@RequestParam(value = "person_id", required = false) int person_id, @RequestParam(value = "type_id", required = false) int type_id) {
        return myBasicService.checkPersonTypeExists(person_id, type_id);
    }
}
