package CreditProfile;

class UserNotFoundException extends RuntimeException {
	
	UserNotFoundException(Long id){
		super("Could not find user of that id" + id);
	}
}