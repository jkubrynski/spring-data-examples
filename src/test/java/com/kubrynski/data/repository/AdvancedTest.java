package com.kubrynski.data.repository;

import com.kubrynski.data.config.DataConfig;
import com.kubrynski.data.model.Company;
import com.kubrynski.data.model.CompanyDTO;
import com.kubrynski.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-10-22
 */
@Test
@ContextConfiguration(classes = DataConfig.class)
public class AdvancedTest extends AbstractTestNGSpringContextTests {

  @Autowired
  private CompanyRepository companyRepository;

  @BeforeMethod
  public void setUp() throws Exception {
    companyRepository.deleteAll();

    Company wjug = new Company("WJUG");
    wjug.addUser(new User("Adam"));
    wjug.addUser(new User("Leszek"));
    wjug.addUser(new User("Zbigniew"));
    companyRepository.save(wjug);
    Company bjug = new Company("BJUG");
    bjug.addUser(new User("Kazimierz"));
    bjug.addUser(new User("Zenon"));
    companyRepository.save(bjug);
  }

  public void shouldReturnCompaniesInDTOs() {
    List<CompanyDTO> companyDTOs = companyRepository.packAllIntoDTO();
    assertNotNull(companyDTOs);
    assertEquals(companyDTOs.size(), 2);
  }

  public void shouldReturnCompaniesByNamedQuery() {
    List<Company> companies = companyRepository.legacyNamedQuery(2);
    assertNotNull(companies);
    assertEquals(companies.size(), 1);
    assertEquals(companies.get(0).getName(), "WJUG");
  }

  public void shouldReturnCompaniesByCustomMethod() {
    List<Company> companies = companyRepository.findCompaniesBiggerThan(2);
    assertNotNull(companies);
    assertEquals(companies.size(), 1);
    assertEquals(companies.get(0).getName(), "WJUG");
  }
}
