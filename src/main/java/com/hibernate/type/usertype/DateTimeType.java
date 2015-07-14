package com.hibernate.type.usertype;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

/**
 * Created by sunilp on 13/7/15.
 */
public class DateTimeType implements UserType {

    @Override
    public int[] sqlTypes() {
        return new int[]{Types.DATE};
    }

    @Override
    public Class returnedClass() {
        return Date.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == y) {
            return true;
        } else if (x == null || y == null) {
            return false;
        } else {
            return x.equals(y);
        }
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
        // if the date is 0000-00-00 return null, else return the Date
        Date result = null;
        String strResult = rs.getString(names[0]);

        if (strResult != null && !strResult.equals("0000-00-00"))
            result = rs.getDate(names[0]);

        return result;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
        // if the date is null set the value to "0000-00-00" else save the date
        if (value == null)
            st.setString(index, "0000-00-00");
        else
            st.setDate(index, (java.sql.Date) value);
    }

    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    public boolean isMutable() {
        return false;
    }

    public Serializable disassemble(Object value) throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }
}
