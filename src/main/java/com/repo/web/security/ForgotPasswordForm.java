package com.repo.web.security;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.springframework.roo.addon.javabean.RooJavaBean;

/**
 *
 * @author '<a href="mailto:ichsan@gmail.com">Muhammad Ichsan</a>'
 *
 */
@RooJavaBean
public class ForgotPasswordForm {

	@NotNull
	@Email
	private String emailAddress;

}
