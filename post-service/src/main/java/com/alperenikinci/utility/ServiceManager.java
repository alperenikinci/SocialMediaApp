package com.alperenikinci.utility;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public class ServiceManager<T, ID> implements IServices<T, ID> {
    private final MongoRepository<T, ID> repository;

    public ServiceManager(MongoRepository<T, ID> repository) {

        this.repository = repository;
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public T update(T entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }


    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }
}
