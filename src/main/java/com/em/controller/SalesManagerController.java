package com.em.controller;

import com.em.entity.SalesManager;
import com.em.repository.SalesManagerRepository;
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
public class SalesManagerController {

    private static final Logger LOGGER = LogManager.getLogger(SalesManagerController.class.getName());

    @Resource
    private SalesManagerRepository salesManagerRepository;

    @RequestMapping(value = "/salesManager")
    public String showForm(@ModelAttribute("salesManager") SalesManager salesManager, HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        Optional<SalesManager> oSalesManager = salesManagerRepository.findById(salesManager.getId());
        if (oSalesManager.isPresent()) {
            map.addAttribute("salesManager", oSalesManager.get());
        } else {
            map.addAttribute("salesManager", new SalesManager());
        }
        return "salesmanager";
    }

    @ModelAttribute("divisions")
    public Map<String, String> division() {
        return Helper.convertToMap(Helper.loadProperties(Constants.DIVISION_FILE_NAME));
    }
}
