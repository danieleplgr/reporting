package com.repo.web.security;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;

/**
 *
 * @author '<a href="mailto:ichsan@gmail.com">Muhammad Ichsan</a>'
 *
 */
@RooJavaBean
public class ChangePasswordForm {

	@NotNull
	private String oldPassword;

	@NotNull
	@Size(min = 5, max = 100)
	private String newPassword;
	private String retypeNewPassword;

}
