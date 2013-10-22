package com.kubrynski.data.repository;

import com.kubrynski.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-04-11
 */
public interface UserRepository extends JpaRepository<User, Long> {

  User findUserByLogin(String login);
}
