package com.ndebugs.simjam.api.services.impl;

import com.ndebugs.simjam.api.services.CRUDService;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class CRUDServiceImpl<T, ID> implements CRUDService<T, ID> {

    protected final JpaRepository<T, ID> repository;

    public CRUDServiceImpl(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public T removeById(ID id) {
        T entity = findById(id);
        if (entity != null) {
            repository.delete(entity);
        }
        
        return entity;
    }

    @Override
    public T findById(ID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }
}
