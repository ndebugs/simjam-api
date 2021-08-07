package com.ndebugs.simjam.api.services.impl;

import com.ndebugs.simjam.api.services.CRUDService;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class CRUDServiceImpl<T, ID> implements CRUDService<T, ID> {

    protected abstract JpaRepository<T, ID> getRepository();
    
    @Override
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public T removeById(ID id) {
        T entity = findById(id);
        if (entity != null) {
            getRepository().delete(entity);
        }
        
        return entity;
    }

    @Override
    public T findById(ID id) {
        return getRepository().findById(id).orElse(null);
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }
}
