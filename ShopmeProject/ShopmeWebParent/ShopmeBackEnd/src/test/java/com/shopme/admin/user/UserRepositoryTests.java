package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository repo;
	@Autowired
	private TestEntityManager entitymanager;
	
	@Test
	public void testCreateUser()
	{
		Role roleAdmin = entitymanager.find(Role.class,1);
		User userNamHM = new User("Giri@codejava.net","giri2020","Giri","ha fimh");
		
		userNamHM.addRole(roleAdmin);
		
		User savedUser =  repo.save(userNamHM);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void createNewUserWithTwoRoles()
	{
		
		User userRavi = new User("Ravi@codejava.net","Ravi2020","Ravi","Kumar");
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);
		
		userRavi.addRole(roleEditor);
		userRavi.addRole(roleAssistant);
		
		User savedUser = repo.save(userRavi);
		assertThat(savedUser.getId()).isGreaterThan(0);
	
	}
	@Test
	public void testListAllUsers()
	{
		
		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(user->System.out.println(user));
		
	}
	
	@Test
	public void testGetUserById()
	{
		User userNam = repo.findById(1).get();
		
		System.out.println(userNam);
		
		assertThat(userNam).isNotNull();
		
	}
	
	@Test
	public void testUpdateUserDetails()
	{
		User userNam = repo.findById(17).get();
		userNam.setEnabled(true);
		userNam.setEmail("namjavaprogrammer@gmail.com");
		repo.save(userNam);
	}
	
	@Test
	public void testUpdateUserRoles()
	{
		User userRavi = repo.findById(3).get();
		Role roleEditor = new Role(4);
		Role roleSalesPerson = new Role(3);
		userRavi.getRoles().remove(roleEditor);
		userRavi.addRole(roleSalesPerson);
		
		repo.save(userRavi);
   	}
	
	@Test
	public void testGetUserByEmailId()
	{
		String email = "Ravi@codejava.net";
		User user = repo.getUserByEmail(email);
		assertThat(user).isNotNull();
	}
	
	@Test
	public void testDeleteUser()
	{
		Integer userId = 7;
		repo.deleteById(userId);
	}
	
	@Test
	public void testCountById()
	{
		
		Integer id =17;
		Long countById = repo.CountById(id);
		assertThat(countById).isNotNull().isGreaterThan(0);
		
		
		
	}
	
   @Test
   public void testDisableUser()
   {
	   Integer id =1;
	   repo.UpdateEnabledStatus(id, false);
   }
   
   @Test
   public void testEnableUser()
   {
	   Integer id =18;
	   repo.UpdateEnabledStatus(id, true);
   }
   
   @Test
   public void testListFirstPage()
   {
	   int pageNumber = 1;
	   int pageSize = 4;
	   Pageable pageble = PageRequest.of(pageNumber, pageSize);
	   Page<User> page = repo.findAll(pageble);
	 List<User> listusers =  page.getContent();
	 listusers.forEach(user->System.out.println(user));
	 assertThat(listusers.size()).isEqualTo(pageSize);
   }
   
   @Test
   public void testSearchUsers() {
	   String keyword = "bruce";
	   int pageNumber = 0;
	   int pageSize = 4;
	   Pageable pageable = PageRequest.of(pageNumber, pageSize);
	   Page<User> page = repo.findAll(keyword,pageable);
	 List<User> listusers =  page.getContent();
	 listusers.forEach(user->System.out.println(user));
	 assertThat(listusers.size()).isGreaterThan(0);

	   
	   
	   
   }
	
	

}
