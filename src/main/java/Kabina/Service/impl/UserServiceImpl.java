package Kabina.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Kabina.Model.User;
import Kabina.Repository.UserRepository;
import Kabina.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository usersRepository;

	public Iterable<User> findAll() {
		return usersRepository.findAll();
	}

	public void deleteUser(Long Id) {
		usersRepository.deleteById(Id);
	}

	public User addUser(User usr) {
		// TODO Auto-generated method stub
		usersRepository.save(usr);
		return usr;
	}

	public int checkUserExist(String userName) {
		User user = usersRepository.findByUsername(userName);
		if (user != null) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public User updateUser(User usr) {
		// TODO Auto-generated method stub
		System.out.println(usr.getUsername() + " from url");
		System.out.println(usr.getPhone() + " from url");
		User user = usersRepository.findById(usr.getId());
		if (user != null) {
			System.out.println(user.getFullName() + " from database");
			System.out.println(user.getPhone() + " from database");
			user.setEmail(usr.getEmail());
			user.setPhone(usr.getPhone());
			user.setPassword(usr.getPassword());
			user.setShortName(usr.getShortName());
			user.setFullName(usr.getFullName());

			System.out.println("SAVE TO DATABASE");
			usersRepository.saveAndFlush(user);
		}
		return user;
	}
}
