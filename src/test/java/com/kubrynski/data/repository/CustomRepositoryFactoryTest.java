package com.kubrynski.data.repository;

import com.kubrynski.data.config.DataConfig;
import com.kubrynski.data.model.Project;
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
public class CustomRepositoryFactoryTest extends AbstractTestNGSpringContextTests {

  @Autowired
  private ProjectRepository projectRepository;

  @BeforeMethod
  public void setUp() throws Exception {
    projectRepository.deleteAll();
    projectRepository.save(new Project("big"));
    projectRepository.save(new Project("small"));
  }

  public void shouldUseGenericRepo() {
    List<Project> all = projectRepository.findAll();

    String uuid = all.get(0).getUuid();
    Project byUuid = projectRepository.findByUuid(uuid);

    assertNotNull(byUuid);
    assertEquals(byUuid, all.get(0));
  }
}
