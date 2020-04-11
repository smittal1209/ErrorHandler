package com.services.error.handler.services;

/**
 * @author shashankmittal
 * @created 11/04/20
 */
public class DummyDao {

    public Object getEntityById(String id) {
        throw new RuntimeException("Entity with id " + id + " not found!");
    }
}

