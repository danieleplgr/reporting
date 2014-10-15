package com.repo.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminHomeController {

	@RequestMapping("/index")
	public String index() {
		return "admin/index";
	}
	
}
