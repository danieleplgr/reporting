package com.repo.web.security;
import com.repo.domain.security.UserRole;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/security/assignrole")
@Controller
@RooWebScaffold(path = "security/assignrole", formBackingObject = UserRole.class)
public class UserRoleController {
}
