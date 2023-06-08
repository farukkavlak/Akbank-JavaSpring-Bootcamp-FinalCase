package com.farukkavlak.akbankbootcamp.generic.service;/*
Created by farukkavlak on 30.05.2023
@author: farukkavlak
@date: 30.05.2023
@project: akbank-bootcamp
*/

import com.farukkavlak.akbankbootcamp.generic.entity.BaseAdditionalFields;
import com.farukkavlak.akbankbootcamp.generic.entity.BaseEntity;
import com.farukkavlak.akbankbootcamp.generic.enums.ErrorMessage;
import com.farukkavlak.akbankbootcamp.generic.exceptions.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public abstract class BaseEntityService<E extends BaseEntity, D extends JpaRepository<E,Long>> {

    private final D dao;

    public Collection<E> findAll() {
        try {
            return this.dao.findAll();
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public E findById(Long id) {
            Optional<E> entity = this.dao.findById(id);
            if (entity.isPresent()) {
                return entity.get();
            } else {
                throw new ItemNotFoundException(ErrorMessage.ITEM_NOT_FOUND);
            }
    }

    public E save(E entity) {
        this.setAdditionalFields(entity);
        try {
            return this.dao.save(entity);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public List<E> saveAll(List<E> entities) {
        entities.forEach(this::setAdditionalFields);
        try {
            return this.dao.saveAll(entities);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void setAdditionalFields(E entity) {
        BaseAdditionalFields baseAdditionalFields = entity.getBaseAdditionalFields();
        if (baseAdditionalFields == null) {
            baseAdditionalFields = new BaseAdditionalFields();
            entity.setBaseAdditionalFields(baseAdditionalFields);
        }

        if (entity.getBaseAdditionalFields().getCreateDate() == null) {
            baseAdditionalFields.setCreateDate(new Date());
        }

        baseAdditionalFields.setUpdateDate(new Date());
    }

    public void delete(E e) {
        this.dao.delete(e);
    }

    public D getDao() {
        return this.dao;
    }

}
