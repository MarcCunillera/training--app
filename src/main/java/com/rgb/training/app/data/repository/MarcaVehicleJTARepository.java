package com.rgb.training.app.data.repository;

import com.rgb.training.app.data.model.MarcaVehicle;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marccunillera
 */
@Stateless
public class MarcaVehicleJTARepository {
        @PersistenceContext(unitName = "testdbd")
    private EntityManager entityManager;

    public MarcaVehicleJTARepository() {
    }

    public MarcaVehicle get(Long entryId) {
        MarcaVehicle result = null;
        try {
            result = (MarcaVehicle)entityManager.createQuery("SELECT mv FROM MarcaVehicle mv WHERE mv.id = :entryId")
                    .setParameter("entryId", entryId)
                    .getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public List<MarcaVehicle> getAll() {
        return getAll(null, null);
    }

    public List<MarcaVehicle> getAll(Integer offset, Integer maxResults) {
        List<MarcaVehicle> results = new ArrayList<>();
        offset = offset == null ? 0 : offset;
        maxResults = maxResults == null ? 500 : maxResults;
        try {
            results = entityManager.createQuery("SELECT mv FROM MarcaVehicle mv ORDER BY mv.id")
                    .setFirstResult(offset)
                    .setMaxResults(maxResults)
                    .getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return results;
    }
    
    public MarcaVehicle create(MarcaVehicle entry) {
        try {
            entityManager.joinTransaction();
            entityManager.persist(entry);
        } catch (Exception ex) {
            System.err.println("Error a create(): " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
        return entry;
    }

    public MarcaVehicle update(MarcaVehicle entry) {
        try {
            entityManager.joinTransaction();
            entry = entityManager.merge(entry);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return entry;
    }

    public MarcaVehicle delete(MarcaVehicle entry) {
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
            MarcaVehicle reference = entityManager.getReference(MarcaVehicle.class, entryId);
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

