package com.maidservice.maidtoorder.controller;

import com.maidservice.maidtoorder.entity.Customer;
import com.maidservice.maidtoorder.entity.User;
import com.maidservice.maidtoorder.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/loginpage")
    public String loginPage() {
        return "LoginPage";
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        if (userService.findByUsername(username).isEmpty()) {
            modelAndView.setViewName("LoginPage");
            modelAndView.addObject("error", "Username does not exist");
        } else if (!userService.validateUser(username, password)) {
            modelAndView.setViewName("LoginPage");
            modelAndView.addObject("error", "Invalid password");
        } else {
            session.setAttribute("username", username);
            modelAndView.setViewName("redirect:/dashboard");
        }
        return modelAndView;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/loginpage";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public ModelAndView register(@RequestParam String name, @RequestParam String username, @RequestParam String email,
                                 @RequestParam String password, @RequestParam String phoneNumber,
                                 @RequestParam String address, @RequestParam String gender) {
        ModelAndView modelAndView = new ModelAndView();
        if (userService.findByUsername(username).isPresent()) {
            modelAndView.setViewName("register");
            modelAndView.addObject("error", "Username already exists");
        } else {
            User user = new User();
            user.setName(name);
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setPhoneNumber(phoneNumber);
            user.setAddress(address);
            user.setGender(gender);
            userService.saveUser(user);
            modelAndView.setViewName("redirect:/loginpage");
        }
        return modelAndView;
    }

    @GetMapping("/forgotpassword")
    public String forgotPasswordPage() {
        return "forgotpassword";
    }

    @PostMapping("/forgotpassword")
    public ModelAndView forgotPassword(@RequestParam String email) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent()) {
            // Logic to send password reset code to the user's email
            modelAndView.setViewName("passwordreset");
        } else {
            modelAndView.setViewName("forgotpassword");
            modelAndView.addObject("error", "Email not found");
        }
        return modelAndView;
    }

    @PostMapping("/resetpassword")
    public ModelAndView resetPassword(@RequestParam String email, @RequestParam String newPassword, @RequestParam String confirmPassword) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent()) {
            if (newPassword.equals(confirmPassword)) {
                User existingUser = user.get();
                existingUser.setPassword(newPassword);
                userService.saveUser(existingUser);
                modelAndView.setViewName("redirect:/loginpage");
            } else {
                modelAndView.setViewName("passwordreset");
                modelAndView.addObject("error", "Passwords do not match");
            }
        } else {
            modelAndView.setViewName("passwordreset");
            modelAndView.addObject("error", "Email not found");
        }
        return modelAndView;
    }

    @GetMapping("/contactus")
    public ModelAndView contactUs(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        if (session.getAttribute("username") != null) {
            modelAndView.setViewName("contactusLoggedIn");
        } else {
            modelAndView.setViewName("contactus");
        }
        return modelAndView;
    }
    @GetMapping("/dashboard")
    public ModelAndView dashboard(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        if (session.getAttribute("username") != null) {
            modelAndView.setViewName("dashboardLoggedIn");
        } else {
            modelAndView.setViewName("dashboard");
        }
        return modelAndView;
    }
    @GetMapping("/reviews")
    public ModelAndView reviews(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        if (session.getAttribute("username") != null) {
            modelAndView.setViewName("reviewspageLoggedIn");
        } else {
            modelAndView.setViewName("reviewspage");
        }
        return modelAndView;
    }
    @GetMapping("/services")
    public ModelAndView services(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        if (session.getAttribute("username") != null) {
            modelAndView.setViewName("servicesLoggedIn");
        } else {
            modelAndView.setViewName("services");
        }
        return modelAndView;
    }
    @GetMapping("/profile")
    public ModelAndView profile(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("profilepage");
        String username = (String) session.getAttribute("username");
        if (username != null) {
            Optional<User> user = userService.findByUsername(username);
            user.ifPresent(value -> modelAndView.addObject("user", value));
        }
        return modelAndView;
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute User user, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            userService.updateUserDetails(username, user);
        }
        return "redirect:/profile";
    }
}