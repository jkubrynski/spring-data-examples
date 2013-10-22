package com.kubrynski.data.repository.generic;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-10-22
 */
public class GenericRepositoryImpl<T> extends SimpleJpaRepository<T, Long> implements GenericRepository<T> {

  private JpaEntityInformation<T, Serializable> entityInformation;
  private final EntityManager entityManager;

  public GenericRepositoryImpl(JpaEntityInformation<T, Serializable> entityInformation, EntityManager entityManager) {
    super(entityInformation, entityManager);
    this.entityInformation = entityInformation;
    this.entityManager = entityManager;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T findByUuid(String uuid) {

    Query query = entityManager.createQuery("from " + entityInformation.getEntityName() + " e where e.uuid = ?1")
        .setParameter(1, uuid);

    return (T) query.getSingleResult();
  }
}
