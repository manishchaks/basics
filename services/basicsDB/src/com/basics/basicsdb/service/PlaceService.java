/*Copyright (c) 2016-2017 unionsystems.com.au All Rights Reserved.
 This software is the confidential and proprietary information of unionsystems.com.au You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with unionsystems.com.au*/

package com.basics.basicsdb.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;

import com.basics.basicsdb.*;

/**
 * Service object for domain model class Place.
 * @see com.basics.basicsdb.Place
 */

public interface PlaceService {
   /**
	 * Creates a new place.
	 * 
	 * @param created
	 *            The information of the created place.
	 * @return The created place.
	 */
	public Place create(Place created);

	/**
	 * Deletes a place.
	 * 
	 * @param placeId
	 *            The id of the deleted place.
	 * @return The deleted place.
	 * @throws EntityNotFoundException
	 *             if no place is found with the given id.
	 */
	public Place delete(Integer placeId) throws EntityNotFoundException;

	/**
	 * Finds all places.
	 * 
	 * @return A list of places.
	 */
	public Page<Place> findAll(QueryFilter[] queryFilters, Pageable pageable);
	
	public Page<Place> findAll(Pageable pageable);
	
	/**
	 * Finds place by id.
	 * 
	 * @param id
	 *            The id of the wanted place.
	 * @return The found place. If no place is found, this method returns
	 *         null.
	 */
	public Place findById(Integer id) throws
	 EntityNotFoundException;
	/**
	 * Updates the information of a place.
	 * 
	 * @param updated
	 *            The information of the updated place.
	 * @return The updated place.
	 * @throws EntityNotFoundException
	 *             if no place is found with given id.
	 */
	public Place update(Place updated) throws EntityNotFoundException;

	/**
	 * Retrieve the total count of the places in the repository.
	 * 
	 * @param None
	 *            .
	 * @return The count of the place.
	 */

	public long countAll();


    public Page<Place> findAssociatedValues(Object value, String entityName, String key,  Pageable pageable);


}

