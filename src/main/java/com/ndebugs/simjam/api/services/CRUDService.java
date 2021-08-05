package com.ndebugs.simjam.api.services;

import java.util.List;

public interface CRUDService<T, ID> {
    
    T save(T entity);
    
    T removeById(ID id);
    
    T findById(ID id);
    
    List<T> findAll();
}
