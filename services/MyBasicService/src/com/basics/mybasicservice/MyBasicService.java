/*Copyright (c) 2016-2017 unionsystems.com.au All Rights Reserved.
 This software is the confidential and proprietary information of unionsystems.com.au You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with unionsystems.com.au*/

package com.basics.mybasicservice;

import com.basics.basicsdb.Person;
import com.basics.basicsdb.PersonType;
import com.basics.basicsdb.Type;
import com.basics.basicsdb.service.PersonService;
import com.basics.basicsdb.service.PersonTypeService;
import com.basics.basicsdb.service.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wavemaker.runtime.service.annotations.ExposeToClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * This is a singleton class with all of its public methods exposed to the client via controller.
 * Their return values and parameters will be passed to the client or taken
 * from the client respectively.
 */
@ExposeToClient
public class MyBasicService {

    @Autowired
    private PersonService personService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private PersonTypeService personTypeService;

    private static final Logger logger=LoggerFactory.getLogger(MyBasicService.class);

    public String associateTypesWithPerson (Person person, ArrayList<Type> types) {
        String result = null;
        Set<PersonType> personTypes = person.getPersonTypes();

        for(int i = 0 ; i < types.size(); i++){
            Type customerType = types.get(i);
            PersonType personType = new PersonType();
            personType.setPerson(person);
            personType.setType(customerType);
            personTypes.add(personType);

        }
        person.setPersonTypes(personTypes);
        return result;
    }
}
