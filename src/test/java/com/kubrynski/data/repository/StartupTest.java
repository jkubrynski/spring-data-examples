package com.kubrynski.data.repository;

import com.kubrynski.data.config.DataConfig;
import com.kubrynski.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-04-09
 */
@Test
@ContextConfiguration(classes = DataConfig.class)
public class StartupTest extends AbstractTestNGSpringContextTests {

  private static final String LOGIN = "my_login";
  private static final String EMAIL = "name@domain.com";
  private static final String OTHER_LOGIN = "other_login";
  private static final String OTHER_EMAIL = "other@domain.org";

  @Autowired
  private AbstractEntityManagerFactoryBean entityManagerFactoryBean;

  @Autowired
  private UserRepository userRepository;

  @BeforeClass
  public void setUp() {
    EntityManager entityManager = entityManagerFactoryBean.getObject().createEntityManager();

    User user1 = new User();
    user1.setLogin(LOGIN);
    user1.setEmail(EMAIL);

    User user2 = new User();
    user2.setLogin(OTHER_LOGIN);
    user2.setEmail(OTHER_EMAIL);

    entityManager.getTransaction().begin();
    entityManager.persist(user1);
    entityManager.persist(user2);
    entityManager.getTransaction().commit();
  }

  public void shouldReturnUser() {
    User user = userRepository.findUserByLogin(LOGIN);

    assertNotNull(user);
    assertEquals(user.getEmail(), EMAIL);
  }
}
