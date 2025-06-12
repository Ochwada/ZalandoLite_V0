package com.zalando.lite.managerSystem;


/**
 * *******************************************************
 * Package: com.zalando.lite
 * File: EntityManager.java
 * Author: Ochwada
 * Date: Friday, 06.Jun.2025, 12:47 PM
 * Description:
 * Objective:
 * *******************************************************
 */

import java.util.*;

/**
 * A generic manager for handling CRUD operations on any entity type.
 *
 * @param <T> The entity type to manage (e.g., Product, Customer, Order)
 */

public class EntityManager<T> {  private final Map<Integer, T> entityMap = new HashMap<>();
    private int nextId = 1;

    /**
     * Adds an entity to the manager and assigns a unique ID.
     *
     * @param entity The entity to add
     * @return The assigned ID
     */
    public int addEntity(T entity) {
        int id = nextId++;
        entityMap.put(id, entity);
        return id;
    }

    /**
     * Retrieves an entity by ID.
     *
     * @param id The entity's ID
     * @return The entity, or null if not found
     */
    public T getEntityById(int id) {
        return entityMap.get(id);
    }

    /**
     * Lists all stored entities.
     *
     * @return A list of all entities
     */
    public List<T> getAllEntities() {
        return new ArrayList<>(entityMap.values());
    }

    /**
     * Removes an entity by ID.
     *
     * @param id The ID of the entity to remove
     * @return true if removed successfully, false if not found
     */
    public boolean removeEntity(int id) {
        return entityMap.remove(id) != null;
    }

    /**
     * Checks if the manager has any entities.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return entityMap.isEmpty();
    }
}