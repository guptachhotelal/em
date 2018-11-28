package com.em.controller;

import com.em.entity.User;
import com.em.repository.UserRepository;
import com.em.utils.Constants;
import com.em.utils.Helper;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController extends BaseController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class.getName());

    @Resource
    private UserRepository userRepository;

    @RequestMapping(value = "/user")
    public String showForm(@ModelAttribute("user") User user, HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        Optional<User> oUser = userRepository.findById(user.getId());
        if (oUser.isPresent()) {
            map.addAttribute("user", oUser.get());
        } else {
            map.addAttribute("user", new User());
        }
        return "user";
    }

    @ModelAttribute("divisions")
    public Map<String, String> division() {
        return Helper.convertToMap(Helper.loadProperties(Constants.DIVISION_FILE_NAME));
    }
}
