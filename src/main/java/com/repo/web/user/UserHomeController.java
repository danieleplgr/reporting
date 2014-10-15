package com.repo.web.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserHomeController {

	@RequestMapping("/homeuser")
	public String index(){
		return "user/homeuser";
	}
	
}
