package ru.kpfu.itis.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.services.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
}
