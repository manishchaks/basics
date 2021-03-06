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
 * ServiceImpl object for domain model class Place.
 * @see com.basics.basicsdb.Place
 */
@Service("basicsDB.PlaceService")
public class PlaceServiceImpl implements PlaceService {


    private static final Logger LOGGER = LoggerFactory.getLogger(PlaceServiceImpl.class);

    @Autowired
    @Qualifier("basicsDB.PlaceDao")
    private WMGenericDao<Place, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Place, Integer> wmGenericDao){
        this.wmGenericDao = wmGenericDao;
    }
     @Transactional(readOnly = true, value = "basicsDBTransactionManager")
     public Page<Place> findAssociatedValues(Object value, String entityName, String key,  Pageable pageable){
          LOGGER.debug("Fetching all associated");
          return this.wmGenericDao.getAssociatedObjects(value, entityName, key, pageable);
     }

    @Transactional(value = "basicsDBTransactionManager")
    @Override
    public Place create(Place place) {
        LOGGER.debug("Creating a new place with information: {}" , place);
        return this.wmGenericDao.create(place);
    }

    @Transactional(rollbackFor = EntityNotFoundException.class, value = "basicsDBTransactionManager")
    @Override
    public Place delete(Integer placeId) throws EntityNotFoundException {
        LOGGER.debug("Deleting place with id: {}" , placeId);
        Place deleted = this.wmGenericDao.findById(placeId);
        if (deleted == null) {
            LOGGER.debug("No place found with id: {}" , placeId);
            throw new EntityNotFoundException(String.valueOf(placeId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(readOnly = true, value = "basicsDBTransactionManager")
    @Override
    public Page<Place> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all places");
        return this.wmGenericDao.search(queryFilters, pageable);
    }
    
    @Transactional(readOnly = true, value = "basicsDBTransactionManager")
    @Override
    public Page<Place> findAll(Pageable pageable) {
        LOGGER.debug("Finding all places");
        return this.wmGenericDao.search(null, pageable);
    }

    @Transactional(readOnly = true, value = "basicsDBTransactionManager")
    @Override
    public Place findById(Integer id) throws EntityNotFoundException {
        LOGGER.debug("Finding place by id: {}" , id);
        Place place=this.wmGenericDao.findById(id);
        if(place==null){
            LOGGER.debug("No place found with id: {}" , id);
            throw new EntityNotFoundException(String.valueOf(id));
        }
        return place;
    }
    @Transactional(rollbackFor = EntityNotFoundException.class, value = "basicsDBTransactionManager")
    @Override
    public Place update(Place updated) throws EntityNotFoundException {
        LOGGER.debug("Updating place with information: {}" , updated);
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


