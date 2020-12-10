package com.app.PagingAndSortingDemo;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	@GetMapping("/saveUser")
	
	public void saveUser() {
		
		String url = "http://dummy.restapiexample.com/api/v1/employees";
		
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(url, String.class);
		System.out.println("response....... "  + response);
		try {
			JSONObject obj = new JSONObject(response);
			JSONArray jsonArray= obj.getJSONArray("data");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject dataObj =jsonArray.getJSONObject(i);	
				String name = dataObj.getString("employee_name");
				int age = dataObj.getInt("employee_age");
				userService.saveUsers(name,age);

			}
		
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
	}
	
		@GetMapping("/users")
	
	public List<User> getUsers(){
		
		 List<User> users = userService.getUserList();
		return users;
		
	}
	
	@GetMapping("/users/{pageNo}")
	
	public List<User> getUserList(@PathVariable("pageNo")int pageNo){
		
		 List<User> users = userService.getUserList(pageNo-1,5);
		return users;
		
	}

}
