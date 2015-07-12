package gym.doubles;

import gym.entities.Entity;

import java.lang.CloneNotSupportedException;import java.lang.RuntimeException;import java.lang.String;import java.lang.SuppressWarnings;import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GatewayUtilities<T extends Entity>
{
    private HashMap<String, T> entities;

    public GatewayUtilities()
    {
        this.entities = new HashMap<>();
    }

    public List<T> getEntities()
    {
        List<T> clonedEntities = new ArrayList<>();
        for (T entity : entities.values())
            addCloneToList(entity, clonedEntities);
        return clonedEntities;
    }

    @SuppressWarnings("unchecked")
    private void addCloneToList(T entity, List<T> newEntities)
    {
        try {
            newEntities.add((T) entity.clone());
        }
        catch (CloneNotSupportedException e) {
            throw new UnCloneableEntity();
        }
    }

    public T save(T entity)
    {
        if (entity.getId() == null)
            entity.setId(UUID.randomUUID().toString());
        String id = entity.getId();
        saveCloneInMap(id, entity);
        return entity;
    }

    @SuppressWarnings("unchecked")
    private void saveCloneInMap(String id, T entity)
    {
        try {
            entities.put(id, (T) entity.clone());
        }
        catch (CloneNotSupportedException e) {
            throw new UnCloneableEntity();
        }
    }

    public void delete(T entity)
    {
        entities.remove(entity.getId());
    }

    private static class UnCloneableEntity extends RuntimeException
    {
    }
}
