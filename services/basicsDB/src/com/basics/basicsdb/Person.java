/*Copyright (c) 2016-2017 unionsystems.com.au All Rights Reserved.
 This software is the confidential and proprietary information of unionsystems.com.au You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with unionsystems.com.au*/

package com.basics.basicsdb;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import javax.persistence.PrimaryKeyJoinColumn;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import java.util.Arrays;

import javax.persistence.Transient;
import javax.persistence.CascadeType;
import javax.persistence.UniqueConstraint;



/**
 * Person generated by hbm2java
 */
@Entity
@Table(name="`PERSON`"
)

public class Person  implements java.io.Serializable {

    
    private Integer id;
    
    private String name;
    
    private Integer age;
    
    private Place place;
    
    private Set<PersonType> personTypes = new HashSet<PersonType>(0);

    public Person() {
    }


    @Id @GeneratedValue(strategy=IDENTITY)
    

    @Column(name="`ID`", precision=10)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    

    @Column(name="`NAME`")
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    

    @Column(name="`AGE`", precision=10)
    public Integer getAge() {
        return this.age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="`PLACE_ID`")
    public Place getPlace() {
        return this.place;
    }
    
    public void setPlace(Place place) {
        this.place = place;
    }

    @OneToMany(fetch=FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy="person")
    public Set<PersonType> getPersonTypes() {
        return this.personTypes;
    }
    
    public void setPersonTypes(Set<PersonType> personTypes) {
        this.personTypes = personTypes;
    }





    public boolean equals(Object o) {
         if (this == o) return true;
		 if ( (o == null )) return false;
		 if ( !(o instanceof Person) )
		    return false;

		 Person that = (Person) o;

		 return ( (this.getId()==that.getId()) || ( this.getId()!=null && that.getId()!=null && this.getId().equals(that.getId()) ) );
    }

    public int hashCode() {
         int result = 17;

         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );

         return result;
    }


}
