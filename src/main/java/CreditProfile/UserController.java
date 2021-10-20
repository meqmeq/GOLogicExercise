package CreditProfile;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
class UserController {
	private final UserRepository repository;
	
	UserController(UserRepository repository) {
		this.repository = repository;
	}
	
	//Get request
	@GetMapping("/user")
	List<User> all() {
		return repository.findAll();
	}
	
	//Post Request
	@PostMapping("/user")
	User newUser(@RequestBody User newUser) {
		return repository.save(newUser);
	}
	
	//Get single User by ID
	@GetMapping("/user/{id}")
	User one(@PathVariable Long id) {
	    
	    return repository.findById(id)
	      .orElseThrow(()-> new UserNotFoundException(id));
	  }
	
	//Patch user's credit
	@PatchMapping("/user/{id}")
	User patchUser(@RequestBody User newUser, @PathVariable Long id) {
		User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		
		user.setCredit(newUser.getCredit());
		
		return repository.save(user);
		
	}
	//Change profile
	@PutMapping("/user/{id}")
	User replaceUser(@RequestBody User newUser,@PathVariable Long id) {
		
		return repository.findById(id).map(user -> {
			user.setName(newUser.getName());
			user.setEmail(newUser.getEmail());
			user.setPhone(newUser.getPhone());
			user.setCredit(newUser.getCredit());
			return repository.save(user);
		}).orElseGet(() -> {
			newUser.setId(id);
			return repository.save(newUser);
		});
	}
	
}
