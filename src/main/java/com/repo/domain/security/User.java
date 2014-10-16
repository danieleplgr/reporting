package com.repo.domain.security;
import com.repo.domain.security.attribute.CreateAttributes;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "users", finders = { "findUsersByEmailAddress", "findUsersByActivationDateBetweenAndEmailAddressLikeAndEnabledNotAndFirstNameLikeAndLastNameLikeAndMobilePhoneLike" })
public class User {

    @NotNull
    @Size(min = 1)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Size(min = 1)
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Size(min = 1)
    @Column(name = "email_address", unique = true)
    private String emailAddress;

    // Using groups: http://blog.codeleak.pl/2011/03/how-to-jsr303-validation-groups-in.html, http://forum.springsource.org/showthread.php?115559-Validation-using-groups-in-Spring-3-0
    @NotNull(groups = CreateAttributes.class) // Submission is required only on create
    @Size(min = 1)
    @Column(name = "password")
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    @Column(name = "activation_date")
    private Date activationDate;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "locked")
    private Boolean locked;

    /**
     */
    @Size(max = 15)
    @Column(unique = true)
    private String mobilePhone;
    
    
    
    
    public static Long countFindUsersByActivationDateBetweenAndEmailAddressLikeAndEnabledNotAndFirstNameLikeAndLastNameLikeAndMobilePhoneLike(Date minActivationDate, Date maxActivationDate, String emailAddress, Boolean enabled, String firstName, String lastName, String mobilePhone) {
        if (minActivationDate == null) throw new IllegalArgumentException("The minActivationDate argument is required");
        if (maxActivationDate == null) throw new IllegalArgumentException("The maxActivationDate argument is required");
        if (emailAddress == null || emailAddress.length() == 0) throw new IllegalArgumentException("The emailAddress argument is required");
        emailAddress = emailAddress.replace('*', '%');
        if (emailAddress.charAt(0) != '%') {
            emailAddress = "%" + emailAddress;
        }
        if (emailAddress.charAt(emailAddress.length() - 1) != '%') {
            emailAddress = emailAddress + "%";
        }
        if (enabled == null) throw new IllegalArgumentException("The enabled argument is required");
        if (firstName == null || firstName.length() == 0) throw new IllegalArgumentException("The firstName argument is required");
        firstName = firstName.replace('*', '%');
        if (firstName.charAt(0) != '%') {
            firstName = "%" + firstName;
        }
        if (firstName.charAt(firstName.length() - 1) != '%') {
            firstName = firstName + "%";
        }
        if (lastName == null || lastName.length() == 0) throw new IllegalArgumentException("The lastName argument is required");
        lastName = lastName.replace('*', '%');
        if (lastName.charAt(0) != '%') {
            lastName = "%" + lastName;
        }
        if (lastName.charAt(lastName.length() - 1) != '%') {
            lastName = lastName + "%";
        }
        if (mobilePhone == null || mobilePhone.length() == 0) throw new IllegalArgumentException("The mobilePhone argument is required");
        mobilePhone = mobilePhone.replace('*', '%');
        if (mobilePhone.charAt(0) != '%') {
            mobilePhone = "%" + mobilePhone;
        }
        if (mobilePhone.charAt(mobilePhone.length() - 1) != '%') {
            mobilePhone = mobilePhone + "%";
        }
        EntityManager em = User.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM User AS o WHERE o.activationDate BETWEEN :minActivationDate AND :maxActivationDate  AND LOWER(o.emailAddress) LIKE LOWER(:emailAddress)  AND o.enabled IS NOT :enabled  AND LOWER(o.firstName) LIKE LOWER(:firstName)  AND LOWER(o.lastName) LIKE LOWER(:lastName)  AND LOWER(o.mobilePhone) LIKE LOWER(:mobilePhone)", Long.class);
        if(minActivationDate != null)
        	q.setParameter("minActivationDate", minActivationDate);
        else {
        	Calendar c = Calendar.getInstance();
        	c.set(Calendar.YEAR, 1950);
        	q.setParameter("minActivationDate", c.getTime());
        }
        if(maxActivationDate != null)
        	q.setParameter("maxActivationDate", maxActivationDate);
        else {
        	Calendar c = Calendar.getInstance(); 
        	q.setParameter("minActivationDate", c.getTime());
        }
        if(emailAddress != null)
        	q.setParameter("emailAddress", emailAddress);
        else {
        	q.setParameter("emailAddress", "%");
        }
        if(enabled != null)
        	q.setParameter("enabled", enabled);
        else
        	q.setParameter("enabled", true);
        
        if(firstName != null)
        	q.setParameter("firstName", firstName);
        else {
        	q.setParameter("firstName", "%");
        }
        if(lastName != null)
        	q.setParameter("lastName", lastName);
        else {
        	q.setParameter("lastName", "%");
        }
        if(mobilePhone != null)
        	q.setParameter("mobilePhone", mobilePhone);
        else {
        	q.setParameter("mobilePhone", "%");
        }
        return ((Long) q.getSingleResult());
    }
    
    public static Long countFindUsersByEmailAddress(String emailAddress) {
        if (emailAddress == null || emailAddress.length() == 0) throw new IllegalArgumentException("The emailAddress argument is required");
        EntityManager em = User.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM User AS o WHERE o.emailAddress = :emailAddress", Long.class);
        q.setParameter("emailAddress", emailAddress);
        return ((Long) q.getSingleResult());
    }
    
    public static TypedQuery<User> findUsersByActivationDateBetweenAndEmailAddressLikeAndEnabledNotAndFirstNameLikeAndLastNameLikeAndMobilePhoneLike(Date minActivationDate, Date maxActivationDate, String emailAddress, Boolean enabled, String firstName, String lastName, String mobilePhone) {
        if (minActivationDate == null) throw new IllegalArgumentException("The minActivationDate argument is required");
        if (maxActivationDate == null) throw new IllegalArgumentException("The maxActivationDate argument is required");
        if (emailAddress == null || emailAddress.length() == 0) throw new IllegalArgumentException("The emailAddress argument is required");
        emailAddress = emailAddress.replace('*', '%');
        if (emailAddress.charAt(0) != '%') {
            emailAddress = "%" + emailAddress;
        }
        if (emailAddress.charAt(emailAddress.length() - 1) != '%') {
            emailAddress = emailAddress + "%";
        }
        if (enabled == null) throw new IllegalArgumentException("The enabled argument is required");
        if (firstName == null || firstName.length() == 0) throw new IllegalArgumentException("The firstName argument is required");
        firstName = firstName.replace('*', '%');
        if (firstName.charAt(0) != '%') {
            firstName = "%" + firstName;
        }
        if (firstName.charAt(firstName.length() - 1) != '%') {
            firstName = firstName + "%";
        }
        if (lastName == null || lastName.length() == 0) throw new IllegalArgumentException("The lastName argument is required");
        lastName = lastName.replace('*', '%');
        if (lastName.charAt(0) != '%') {
            lastName = "%" + lastName;
        }
        if (lastName.charAt(lastName.length() - 1) != '%') {
            lastName = lastName + "%";
        }
        if (mobilePhone == null || mobilePhone.length() == 0) throw new IllegalArgumentException("The mobilePhone argument is required");
        mobilePhone = mobilePhone.replace('*', '%');
        if (mobilePhone.charAt(0) != '%') {
            mobilePhone = "%" + mobilePhone;
        }
        if (mobilePhone.charAt(mobilePhone.length() - 1) != '%') {
            mobilePhone = mobilePhone + "%";
        }
        EntityManager em = User.entityManager();
        TypedQuery<User> q = em.createQuery("SELECT o FROM User AS o WHERE o.activationDate BETWEEN :minActivationDate AND :maxActivationDate  AND LOWER(o.emailAddress) LIKE LOWER(:emailAddress)  AND o.enabled IS NOT :enabled  AND LOWER(o.firstName) LIKE LOWER(:firstName)  AND LOWER(o.lastName) LIKE LOWER(:lastName)  AND LOWER(o.mobilePhone) LIKE LOWER(:mobilePhone)", User.class);
        q.setParameter("minActivationDate", minActivationDate);
        q.setParameter("maxActivationDate", maxActivationDate);
        q.setParameter("emailAddress", emailAddress);
        q.setParameter("enabled", enabled);
        q.setParameter("firstName", firstName);
        q.setParameter("lastName", lastName);
        q.setParameter("mobilePhone", mobilePhone);
        return q;
    }
    
    public static TypedQuery<User> findUsersByActivationDateBetweenAndEmailAddressLikeAndEnabledNotAndFirstNameLikeAndLastNameLikeAndMobilePhoneLike(Date minActivationDate, Date maxActivationDate, String emailAddress, Boolean enabled, String firstName, String lastName, String mobilePhone, String sortFieldName, String sortOrder) {
        if (minActivationDate == null) throw new IllegalArgumentException("The minActivationDate argument is required");
        if (maxActivationDate == null) throw new IllegalArgumentException("The maxActivationDate argument is required");
        if (emailAddress == null || emailAddress.length() == 0) throw new IllegalArgumentException("The emailAddress argument is required");
        emailAddress = emailAddress.replace('*', '%');
        if (emailAddress.charAt(0) != '%') {
            emailAddress = "%" + emailAddress;
        }
        if (emailAddress.charAt(emailAddress.length() - 1) != '%') {
            emailAddress = emailAddress + "%";
        }
        if (enabled == null) throw new IllegalArgumentException("The enabled argument is required");
        if (firstName == null || firstName.length() == 0) throw new IllegalArgumentException("The firstName argument is required");
        firstName = firstName.replace('*', '%');
        if (firstName.charAt(0) != '%') {
            firstName = "%" + firstName;
        }
        if (firstName.charAt(firstName.length() - 1) != '%') {
            firstName = firstName + "%";
        }
        if (lastName == null || lastName.length() == 0) throw new IllegalArgumentException("The lastName argument is required");
        lastName = lastName.replace('*', '%');
        if (lastName.charAt(0) != '%') {
            lastName = "%" + lastName;
        }
        if (lastName.charAt(lastName.length() - 1) != '%') {
            lastName = lastName + "%";
        }
        if (mobilePhone == null || mobilePhone.length() == 0) throw new IllegalArgumentException("The mobilePhone argument is required");
        mobilePhone = mobilePhone.replace('*', '%');
        if (mobilePhone.charAt(0) != '%') {
            mobilePhone = "%" + mobilePhone;
        }
        if (mobilePhone.charAt(mobilePhone.length() - 1) != '%') {
            mobilePhone = mobilePhone + "%";
        }
        EntityManager em = User.entityManager();
        String jpaQuery = "SELECT o FROM User AS o WHERE o.activationDate BETWEEN :minActivationDate AND :maxActivationDate  AND LOWER(o.emailAddress) LIKE LOWER(:emailAddress)  AND o.enabled IS NOT :enabled  AND LOWER(o.firstName) LIKE LOWER(:firstName)  AND LOWER(o.lastName) LIKE LOWER(:lastName)  AND LOWER(o.mobilePhone) LIKE LOWER(:mobilePhone)";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<User> q = em.createQuery(jpaQuery, User.class);
        q.setParameter("minActivationDate", minActivationDate);
        q.setParameter("maxActivationDate", maxActivationDate);
        q.setParameter("emailAddress", emailAddress);
        q.setParameter("enabled", enabled);
        q.setParameter("firstName", firstName);
        q.setParameter("lastName", lastName);
        q.setParameter("mobilePhone", mobilePhone);
        return q;
    }
    
    public static TypedQuery<User> findUsersByEmailAddress(String emailAddress) {
        if (emailAddress == null || emailAddress.length() == 0) throw new IllegalArgumentException("The emailAddress argument is required");
        EntityManager em = User.entityManager();
        TypedQuery<User> q = em.createQuery("SELECT o FROM User AS o WHERE o.emailAddress = :emailAddress", User.class);
        q.setParameter("emailAddress", emailAddress);
        return q;
    }
    
    public static TypedQuery<User> findUsersByEmailAddress(String emailAddress, String sortFieldName, String sortOrder) {
        if (emailAddress == null || emailAddress.length() == 0) throw new IllegalArgumentException("The emailAddress argument is required");
        EntityManager em = User.entityManager();
        String jpaQuery = "SELECT o FROM User AS o WHERE o.emailAddress = :emailAddress";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<User> q = em.createQuery(jpaQuery, User.class);
        q.setParameter("emailAddress", emailAddress);
        return q;
    }
    
}
