package com.example.springboot.controller;

import com.example.springboot.Util.Utility;
import com.example.springboot.dao.User.User;
import com.example.springboot.dao.exception.UserNotFoundException;
import com.example.springboot.dao.repository.UserRepository;
import com.example.springboot.dto.UserRegistrationDto;
import com.example.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@RestController
@RequiredArgsConstructor
public class ForgotPasswordController {
    private final JavaMailSender mailSender;
    private final UserService userService;
    private final UserRepository userRepository;
//    @GetMapping("/forgot_password")
//    public String showForgotPasswordForm() {
//
//    }

    @PostMapping("/forgot_password")
    public void processForgotPassword(HttpServletRequest request) {
        String email = request.getParameter("email");
        String token = RandomString.make(30);

        try {
            userService.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
            System.out.println(resetPasswordLink);
            sendEmail(email, resetPasswordLink);


        } catch (UserNotFoundException ex) {
            throw new RuntimeException("Error");
        } catch (UnsupportedEncodingException | MessagingException e) {
            throw new RuntimeException("Error while sending email");
        }

    }
    public void sendEmail(String email, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("contact@university.com", "University Support");
        helper.setTo(email);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                +"<p>Token to reset password: "+ userRepository.findTokenByEmail(email) +"</p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }

    public void sendEmail(){

    }


//    @GetMapping("/reset_password")
//    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
//        User user = userService.getByResetPasswordToken(token);
//        model.addAttribute("token", token);
//
//        if (user == null) {
//            model.addAttribute("message", "Invalid Token");
//            return "message";
//        }
//        return "reset_password_form";
//    }

    @PostMapping("/reset_password")
    public void processResetPassword(HttpServletRequest request) throws UserNotFoundException {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User user = userService.getByResetPasswordToken(token);


        if (user == null) {
            throw new UserNotFoundException("Can't find the user");
        } else {
            userService.updatePassword(user, password);
        }

    }

}

