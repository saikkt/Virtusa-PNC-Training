package com.assessment.user;

import com.assessment.user.domain.User;
import com.assessment.user.repository.UserRepository;
import com.assessment.user.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceApplicationTests {

	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private UserService userService;

	@Test
	public void testFindAll(){
		List<User> users = new ArrayList<>();
		users.add(new User("sai","krishna"));
		users.add(new User("firstname","lastname"));
		Mockito.when(userRepository.findAll()).thenReturn(users);
		Assertions.assertEquals(2,userService.findAll().size());
	}

	@Test
	public void testFindById(){
		User user = new User("firstname","lastname");
		user.setId(1);
		Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		Assertions.assertEquals(1,userService.findById(1).get().getId());
	}

	@Test
	public void testSave(){
		User user = new User("firstname","lastname");
 		Mockito.when(userRepository.save(user)).thenReturn(user);
		Assertions.assertEquals(user,userService.save(user));
	}

}
