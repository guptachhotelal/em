package com.em.controller;

import com.em.entity.Event;
import com.em.repository.EventRepository;
import com.em.utils.Constants;
import java.util.Date;
import java.util.Optional;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventController extends  BaseController{

    private static final Logger LOGGER = LogManager.getLogger(EventController.class.getName());

    @Resource
    private EventRepository eventRepository;

    @GetMapping(value = "/event")
    public String showForm(@ModelAttribute("event") Event event, HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        Optional<Event> oEvent = eventRepository.findById(event.getId());
        if (oEvent.isPresent()) {
            map.addAttribute("event", oEvent.get());
        } else {
            map.addAttribute("event", new Event());
        }
        return "event";
    }

    @PostMapping(value = "/event")
    public String save(@ModelAttribute("event") Event event, HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        Event e = eventRepository.save(event);
        return "event";
    }

    @InitBinder
    public void binder(WebDataBinder binder) {
        CustomDateEditor startTime = new CustomDateEditor(Constants.SDF, true);
        binder.registerCustomEditor(Date.class, "startTime", startTime);
    }
}
