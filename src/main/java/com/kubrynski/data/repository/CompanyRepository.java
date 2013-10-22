package com.kubrynski.data.repository;

import com.kubrynski.data.model.Company;
import com.kubrynski.data.model.CompanyDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-04-11
 */
public interface CompanyRepository extends JpaRepository<Company, Long>, CompanyRepositoryCustom {

  @Query("select new com.kubrynski.data.model.CompanyDTO(c.name) from Company c")
  List<CompanyDTO> packAllIntoDTO();

  List<Company> legacyNamedQuery(Integer usersCount);

}
