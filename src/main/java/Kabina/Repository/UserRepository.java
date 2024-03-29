package Kabina.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Kabina.Model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, JpaRepository<User, Long> {
	User findByUsername(String userName);
	User findById(long userId);
	
}
