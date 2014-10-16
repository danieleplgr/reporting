package com.repo.domain.security;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "users_roles", finders = { "findUserRolesByUser" })
public class UserRole {

    /**
     */
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     */
    @NotNull
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    
}
