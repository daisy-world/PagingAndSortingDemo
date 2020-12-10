package com.app.PagingAndSortingDemo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public void saveUsers(String name,int age) {

		User user = new User();
		user.setName(name);
		user.setAge(age);
		userRepository.save(user);
		
	}

	@Override
	public List<User> getUserList() {

		List<User> users =userRepository.findAll(); 
		return users;
	}

	@Override
	public List<User> getUserList(int pageNo, int pageSize) {
		
		
		List<User> userList = new ArrayList<User>();
		Pageable paging =  PageRequest.of(pageNo, pageSize);
		 
        Page<User> pagedResult = userRepository.findAll(paging);
       
        System.out.println("total element.... "  +   pagedResult.getTotalElements());
        System.out.println("total pages.... "  +   pagedResult.getTotalPages());
        System.out.println("current page.... "  +   pagedResult.getNumber());
        System.out.println("no of elements in a page.... "  +   pagedResult.getNumberOfElements());
        if(pagedResult.hasContent()) {
        	userList = pagedResult.getContent();
            return pagedResult.getContent();
        } else {
            return userList;
        }
	}
	
	

}
