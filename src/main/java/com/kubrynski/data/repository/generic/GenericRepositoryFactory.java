package com.kubrynski.data.repository.generic;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.RepositoryMetadata;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-10-22
 */
public class GenericRepositoryFactory extends JpaRepositoryFactory {

  /**
   * Creates a new {@link org.springframework.data.jpa.repository.support.JpaRepositoryFactory}.
   *
   * @param entityManager must not be {@literal null}
   */
  public GenericRepositoryFactory(EntityManager entityManager) {
    super(entityManager);
  }

  @Override
  protected <T, ID extends Serializable> GenericRepository<T> getTargetRepository(RepositoryMetadata metadata, EntityManager entityManager) {
    return new GenericRepositoryImpl<T>(getEntityInformation((Class<T>) metadata.getDomainType()), entityManager);
  }

  @Override
  protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
    return GenericRepositoryImpl.class;
  }
}
