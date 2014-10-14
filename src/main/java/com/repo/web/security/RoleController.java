package com.repo.web.security;
import com.repo.domain.security.Role;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/security/roles")
@Controller
@RooWebScaffold(path = "security/roles", formBackingObject = Role.class)
public class RoleController {
}
