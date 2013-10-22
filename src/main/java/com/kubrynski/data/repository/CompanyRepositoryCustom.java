package com.kubrynski.data.repository;

import com.kubrynski.data.model.Company;

import java.util.List;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-10-22
 */
public interface CompanyRepositoryCustom {

  List<Company> findCompaniesBiggerThan(final int size);
}
