package com.kubrynski.data.repository.generic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-10-22
 */
@NoRepositoryBean
public interface GenericRepository<T> extends JpaRepository<T, Long> {

  T findByUuid(String uuid);
}
