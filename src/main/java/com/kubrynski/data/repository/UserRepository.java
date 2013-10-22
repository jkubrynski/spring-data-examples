package com.kubrynski.data.repository;

import com.kubrynski.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-04-11
 */
public interface UserRepository extends JpaRepository<User, Long> {

  User findUserByLogin(String login);

  List<User> findByEmailLikeIgnoreCase(String email);

  List<User> findByCompany_Name(String companyName);

  List<User> findByCompanyNameOrderByLoginAsc(String companyName);

  List<User> findByCompany_Projects_Name(String projectName);

}
