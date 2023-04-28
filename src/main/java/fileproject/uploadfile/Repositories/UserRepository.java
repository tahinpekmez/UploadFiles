package fileproject.uploadfile.Repositories;

import fileproject.uploadfile.Models.FileUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<FileUser, Long> {
    Optional<FileUser> findByUsername(String username);

    Boolean existsByUsername(String username);
}
