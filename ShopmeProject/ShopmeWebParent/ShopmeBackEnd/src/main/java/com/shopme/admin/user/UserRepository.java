package com.shopme.admin.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shopme.common.entity.User;
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
@Query("select u from User u where u.email = :email")
	public User getUserByEmail(@Param("email") String email);
@Query("SELECT COUNT(n) FROM User n WHERE n.id=?1")
 Long CountById(Integer id);



@Query("SELECT u from User u WHERE CONCAT(u.id,' ',u.email,' ',u.firstname, ' '," + " u.lastname) LIKE %?1%")
public Page<User> findAll(String keyword,Pageable pageable);

@Query("Update User u SET u.enabled = ?2 where u.id = ?1")
@Modifying
public void UpdateEnabledStatus(Integer id,boolean enabled);

}
