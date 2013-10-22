package com.kubrynski.data.repository;

import com.kubrynski.data.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-04-11
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
