package com.kubrynski.data.repository;

import com.kubrynski.data.config.DataConfig;
import com.kubrynski.data.model.Company;
import com.kubrynski.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
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
public class IntermediateTest extends AbstractTestNGSpringContextTests {

  private static final String COMPANY_NAME = "WJUG";
  private static final String FIRST_USER_LOGIN = "Adam";

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private UserRepository userRepository;

  @BeforeClass
  public void setUp() throws Exception {
    companyRepository.deleteAll();

    Company company = new Company();
    company.setName(COMPANY_NAME);

    company.addUser(new User("Zenon"));
    company.addUser(new User(FIRST_USER_LOGIN));
    companyRepository.save(company);

    Company otherCompany = new Company("BJUG");
    otherCompany.addUser(new User());
    companyRepository.save(otherCompany);

    companyRepository.save(new Company("PJUG"));
  }

  public void shouldReturnUsersByCompanyName() {
    List<User> users = userRepository.findByCompany_Name(COMPANY_NAME);

    assertNotNull(users);
    assertEquals(users.size(), 2);
  }

  public void shouldReturnUsersByCompanyNameOrderedByLogin() {
    List<User> users = userRepository.findByCompanyNameOrderByLoginAsc(COMPANY_NAME);

    assertNotNull(users);
    assertEquals(users.size(), 2);
    assertEquals(users.get(0).getLogin(), FIRST_USER_LOGIN);
  }

  public void shouldReturnPaginatedCompanies() {
    Page<Company> firstPage = companyRepository.findAll(new PageRequest(0, 2));
    assertNotNull(firstPage);
    assertEquals(firstPage.getSize(), 2);
    assertEquals(firstPage.getNumberOfElements(), 2);
    assertEquals(firstPage.getNumber(), 0);
    assertEquals(firstPage.getTotalElements(), 3);
    assertEquals(firstPage.getTotalPages(), 2);

    Page<Company> secondPage = companyRepository.findAll(new PageRequest(1, 2));
    assertEquals(secondPage.getSize(), 2);
    assertEquals(secondPage.getNumberOfElements(), 1);
    assertEquals(secondPage.getNumber(), 1);

  }
}
