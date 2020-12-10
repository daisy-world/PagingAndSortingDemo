package com.app.PagingAndSortingDemo;

import java.util.List;

public interface IUserService {
	
	
	public void saveUsers(String name,int age);
	
	public List<User> getUserList();
	
	public List<User> getUserList(int pageNo,int pageSize);

}
