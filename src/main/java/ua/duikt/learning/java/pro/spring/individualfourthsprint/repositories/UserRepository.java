package ua.duikt.learning.java.pro.spring.individualfourthsprint.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.duikt.learning.java.pro.spring.individualfourthsprint.entities.User;

/**
 * Created by Mykyta Sirobaba on 20.01.2026.
 * email mykyta.sirobaba@gmail.com
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
