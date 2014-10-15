package com.repo.domain.security;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "roles", finders = { "findRolesByNameEquals" })
public class Role {

    /**
     */
    @NotNull
    @Column(name = "name", unique = true)
    @Size(min = 1)
    @Pattern(regexp = "^ROLE_[A-Z]*")
    private String name;

    /**
     */
    @NotNull
    @Column(name = "description")
    @Size(max = 200)
    private String description;
}
