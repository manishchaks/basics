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
import com.wavemaker.runtime.data.expression.AttributeType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.wavemaker.runtime.service.annotations.ExposeToClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;

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

        // Person, PersonTypes and Types
        // 0. Iterate over each Type supplied.
        // 1. Check if entry exists in PersonType table with PERSON_ID and TYPE_ID set to type
        // 2. If entry does not exist, create a PersonType with supplied PERSON_ID and TYPE_ID

        // also write a service endpoint which lets  you know if a PersonType exists with given TYPE_ID and PERSON_ID

        String result = null;
        for(int i=0; i < types.size() ; i++){
            Type aType = types.get(i);

            QueryFilter personIdQueryFilter = new QueryFilter();
            personIdQueryFilter.setAttributeName("PERSON_ID");
            personIdQueryFilter.setAttributeType(AttributeType.INTEGER);
            personIdQueryFilter.setAttributeValue(person.getId());

            QueryFilter typeIDQueryFilter = new QueryFilter();
            typeIDQueryFilter.setAttributeName("TYPE_ID");
            typeIDQueryFilter.setAttributeType(AttributeType.INTEGER);
            typeIDQueryFilter.setAttributeValue(aType.getId());

            ArrayList<QueryFilter> queryFilterArrayList = new ArrayList<QueryFilter>();
            queryFilterArrayList.add(personIdQueryFilter);
            queryFilterArrayList.add(typeIDQueryFilter);
            Page<PersonType> all = personTypeService.findAll((QueryFilter[]) queryFilterArrayList.toArray(), null);
            result += all.toString();

        }
        return result;
    }

    public boolean checkPersonTypeExists(int person_id , int type_id){
        QueryFilter personIdQueryFilter = new QueryFilter();
        personIdQueryFilter.setAttributeName("PERSON_ID");
        personIdQueryFilter.setAttributeType(AttributeType.INTEGER);
        personIdQueryFilter.setAttributeValue(person_id);

        QueryFilter typeIDQueryFilter = new QueryFilter();
        typeIDQueryFilter.setAttributeName("TYPE_ID");
        typeIDQueryFilter.setAttributeType(AttributeType.INTEGER);
        typeIDQueryFilter.setAttributeValue(type_id);

        QueryFilter queryFilters[] = {personIdQueryFilter,typeIDQueryFilter};
        Page<PersonType> all = personTypeService.findAll(queryFilters,null);

        if(all.getTotalElements() > 0){
            return true;
        }else {
            return false;
        }
    }
}

