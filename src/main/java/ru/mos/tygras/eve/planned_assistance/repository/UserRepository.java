package ru.mos.tygras.eve.planned_assistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.mos.tygras.eve.planned_assistance.model.entity.User;


public interface UserRepository extends JpaRepository<User, Long> , JpaSpecificationExecutor<User> {

    User findUserByLogin(String login);

}
