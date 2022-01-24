package com.engineer.inzynier;

import com.engineer.inzynier.dto.User;
import com.engineer.inzynier.persistence.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    @Test
    public void CreateUser(){
        User user = new User();
        user.setEmail("someemail@email.com");
        user.setPassword("password");
        user.setFirstName("User");
        user.setLastName("Userro");

        User savedUser = repository.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());
        //assertThat jest czytelniejsze od tradycyjnych JUnitowych assertow
        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
    }
}
