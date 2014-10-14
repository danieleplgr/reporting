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
public class NewPasswordForm {

	@NotNull
	@Size(min = 5)
	private String newPassword;
	private String retypeNewPassword;

}
