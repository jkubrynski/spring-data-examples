package com.kubrynski.data.repository;

import com.kubrynski.data.config.DataConfig;
import com.kubrynski.data.model.Company;
import com.kubrynski.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private UserRepository userRepository;

  @BeforeClass
  public void setUp() throws Exception {
    Company company = new Company();
    company.setName(COMPANY_NAME);

    company.addUser(new User());
    company.addUser(new User());
    companyRepository.save(company);
  }

  public void shouldReturnUsersByCompanyName() {
    List<User> users = userRepository.findByCompany_Name(COMPANY_NAME);

    assertNotNull(users);
    assertEquals(users.size(), 2);
  }
}
