package com.kubrynski.data.repository.generic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-10-22
 */
public class GenericRepositoryFactoryBean<T extends JpaRepository<S, ID>, S, ID extends Serializable>
    extends JpaRepositoryFactoryBean<T, S, ID> {

  /**
   * Returns a {@link org.springframework.data.repository.core.support.RepositoryFactorySupport}.
   *
   * @param entityManager
   * @return
   */
  protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
    return new GenericRepositoryFactory(entityManager);
  }
}
