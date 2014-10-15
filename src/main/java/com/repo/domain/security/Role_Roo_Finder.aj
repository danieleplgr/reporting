// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.repo.domain.security;

import com.repo.domain.security.Role;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Role_Roo_Finder {
    
    public static TypedQuery<Role> Role.findRolesByNameEquals(String name) {
        if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
        EntityManager em = Role.entityManager();
        TypedQuery<Role> q = em.createQuery("SELECT o FROM Role AS o WHERE o.name = :name", Role.class);
        q.setParameter("name", name);
        return q;
    }
    
}