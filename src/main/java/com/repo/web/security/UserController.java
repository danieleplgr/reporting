package com.repo.web.security;
import com.repo.domain.security.attribute.CreateAttributes;
import com.repo.domain.security.User;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;

/**
 *
 * @author '<a href="mailto:ichsan@gmail.com">Muhammad Ichsan</a>'
 *
 */
@RequestMapping("/security/users")
@Controller
@RooWebScaffold(path = "security/users", formBackingObject = User.class)
@RooWebFinder
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private transient MailSender mailSender;

    @Autowired
    @Qualifier("accountActivationMailTemplate")
    private SimpleMailMessage mailTemplate;

    @Autowired
    private Validator validator;

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid User user, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        // Validate during creation
        validator.validate(user, CreateAttributes.class);
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, user);
            return "security/users/create";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.persist();
        return "redirect:/security/users/" + encodeUrlPathSegment(user.getId().toString(), httpServletRequest);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid User user, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, user);
            return "security/users/update";
        }
        User storedUser = User.findUser(user.getId());
        user.setPassword(storedUser.getPassword()); // Prevent overriding into null
        // If user is get enabled (but previously not), send activation mail
        if (user.getEnabled() && !storedUser.getEnabled()) {
            user.setActivationDate(new Date());
            sendActivationMail(user);
        }
        uiModel.asMap().clear();
        user.merge();
        return "redirect:/security/users/" + encodeUrlPathSegment(user.getId().toString(), httpServletRequest);
    }

    private void sendActivationMail(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage(mailTemplate);
        mailMessage.setTo(user.getEmailAddress());
        String activationKey = user.getPassword().substring(0, 5);
        mailMessage.setText(mailMessage.getText().replace("{{FIRST_NAME}}", user.getFirstName()).replace("{{LAST_NAME}}", user.getLastName()).replace("{{EMAIL_ADDRESS}}", user.getEmailAddress()).replace("{{ACTIVATION_KEY}}", activationKey));
        mailSender.send(mailMessage);
    }
}
