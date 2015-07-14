package com.hibernate.type.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


/**
 * Created by sunilp on 13/7/15.
 */
@Entity
@Table(name="person"
)
public class Person {

    private int id;

    @Column(name="dob", length=10)
    @Type(type="com.hibernate.type.usertype.DateTimeUserType")
    private Date dob;

    public Person() {
    }



    @Id
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Date getDob() {
        return this.dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }



    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ( (o == null ) )
            return false;
        if ( !(o instanceof Person) )
            return false;

        Person that = (Person) o;

        return (this.getId()==that.getId());

    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + this.getId();

        return result;
    }
}
