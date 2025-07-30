package com.rgb.training.app.data.repository;

import com.rgb.training.app.data.model.ModelVehicles;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author marccunillera
 */
public class ModelVehicleJTARepository {
    
    @PersistenceContext(unitName = "testdbd")
    private EntityManager entityManager;

    public ModelVehicleJTARepository() {
    }

    public ModelVehicles get(Long entryId) {
        ModelVehicles result = null;
        try {
            result = (ModelVehicles) entityManager.createQuery("SELECT mv FROM ModelVehicles mv WHERE mv.id = :entryId")
                    .setParameter("entryId", entryId)
                    .getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public List<ModelVehicles> getAll() {
        return getAll(null, null);
    }

    public List<ModelVehicles> getAll(Integer offset, Integer maxResults) {
        List<ModelVehicles> results = new ArrayList<>();
        offset = offset == null ? 0 : offset;
        maxResults = maxResults == null ? 500 : maxResults;
        try {
            results = entityManager.createQuery("SELECT mv FROM ModelVehicles mv ORDER BY mv.id")
                    .setFirstResult(offset)
                    .setMaxResults(maxResults)
                    .getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return results;
    }

    public ModelVehicles create(ModelVehicles entry) {
        try {
            entityManager.joinTransaction();
            entityManager.persist(entry);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return entry;
    }

    public ModelVehicles update(ModelVehicles entry) {
        try {
            entityManager.joinTransaction();
            entry = entityManager.merge(entry);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return entry;
    }

    public ModelVehicles delete(ModelVehicles entry) {
        try {
            entityManager.remove(entry);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return entry;
    }

    public Long delete(Long entryId) {
        Long result = -1L;
        try {
            ModelVehicles reference = entityManager.getReference(ModelVehicles.class, entryId);
            if (reference != null) {
                entityManager.remove(reference);
                result = entryId;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @PreDestroy
    public void close() {
        if (this.entityManager != null) {
            this.entityManager.close();
        }
    }
}
