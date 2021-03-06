/*Copyright (c) 2016-2017 unionsystems.com.au All Rights Reserved.
 This software is the confidential and proprietary information of unionsystems.com.au You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with unionsystems.com.au*/

package com.basics.basicsdb.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/





import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wavemaker.runtime.data.dao.*;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;

import com.basics.basicsdb.*;


/**
 * ServiceImpl object for domain model class Person.
 * @see com.basics.basicsdb.Person
 */
@Service("basicsDB.PersonService")
public class PersonServiceImpl implements PersonService {


    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
    @Qualifier("basicsDB.PersonDao")
    private WMGenericDao<Person, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Person, Integer> wmGenericDao){
        this.wmGenericDao = wmGenericDao;
    }
     @Transactional(readOnly = true, value = "basicsDBTransactionManager")
     public Page<Person> findAssociatedValues(Object value, String entityName, String key,  Pageable pageable){
          LOGGER.debug("Fetching all associated");
          return this.wmGenericDao.getAssociatedObjects(value, entityName, key, pageable);
     }

    @Transactional(value = "basicsDBTransactionManager")
    @Override
    public Person create(Person person) {
        LOGGER.debug("Creating a new person with information: {}" , person);
        return this.wmGenericDao.create(person);
    }

    @Transactional(rollbackFor = EntityNotFoundException.class, value = "basicsDBTransactionManager")
    @Override
    public Person delete(Integer personId) throws EntityNotFoundException {
        LOGGER.debug("Deleting person with id: {}" , personId);
        Person deleted = this.wmGenericDao.findById(personId);
        if (deleted == null) {
            LOGGER.debug("No person found with id: {}" , personId);
            throw new EntityNotFoundException(String.valueOf(personId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(readOnly = true, value = "basicsDBTransactionManager")
    @Override
    public Page<Person> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all persons");
        return this.wmGenericDao.search(queryFilters, pageable);
    }
    
    @Transactional(readOnly = true, value = "basicsDBTransactionManager")
    @Override
    public Page<Person> findAll(Pageable pageable) {
        LOGGER.debug("Finding all persons");
        return this.wmGenericDao.search(null, pageable);
    }

    @Transactional(readOnly = true, value = "basicsDBTransactionManager")
    @Override
    public Person findById(Integer id) throws EntityNotFoundException {
        LOGGER.debug("Finding person by id: {}" , id);
        Person person=this.wmGenericDao.findById(id);
        if(person==null){
            LOGGER.debug("No person found with id: {}" , id);
            throw new EntityNotFoundException(String.valueOf(id));
        }
        return person;
    }
    @Transactional(rollbackFor = EntityNotFoundException.class, value = "basicsDBTransactionManager")
    @Override
    public Person update(Person updated) throws EntityNotFoundException {
        LOGGER.debug("Updating person with information: {}" , updated);
        this.wmGenericDao.update(updated);

        Integer id = (Integer)updated.getId();

        return this.wmGenericDao.findById(id);
    }

    @Transactional(readOnly = true, value = "basicsDBTransactionManager")
    @Override
    public long countAll() {
        return this.wmGenericDao.count();
    }
}


