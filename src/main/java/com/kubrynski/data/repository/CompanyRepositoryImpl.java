package com.kubrynski.data.repository;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.kubrynski.data.model.Company;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-10-22
 */
public class CompanyRepositoryImpl implements CompanyRepositoryCustom {

  @PersistenceContext
  private EntityManager em;

  @Override
  @Transactional
  public List<Company> findCompaniesBiggerThan(final int size) {
    List<Company> companies = em.createQuery("select c from Company c").getResultList();

    ArrayList<Company> result = Lists.newArrayList(Collections2.filter(companies, new Predicate<Company>() {
      @Override
      public boolean apply(Company company) {
        return company.getUsers().size() > size;
      }
    }));

    return result;

  }
}
