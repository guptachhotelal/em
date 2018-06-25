package com.em.controller;

import com.em.entity.Doctor;
import com.em.entity.Event;
import com.em.entity.Scheduler;
import com.em.repository.DoctorRepository;
import com.em.repository.EventRepository;
import com.em.utils.Constants;
import com.em.utils.Helper;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.em.repository.SchedulerRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SchedulerController {

    @Resource
    private EventRepository eventRepository;

    @Resource
    private DoctorRepository doctorRepository;

    @Resource
    private SchedulerRepository schedulerRepository;

    private static final List<Scheduler> SCHEDULES = new ArrayList<>();

    private int dummyCounter;

    @RequestMapping("schedules.htm")
    public String listAll() {
        return "schedules";
    }

    @ResponseBody
    @PostMapping(value = "fetchSchedules.json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> fetch(HttpServletRequest request) {
        if (SCHEDULES.isEmpty()) {
            SCHEDULES.addAll(schedulerRepository.findAll());
        }
        String searchText = request.getParameter("search[value]") == null ? "" : request.getParameter("search[value]").toLowerCase().trim();
        int length = request.getParameter("length") == null ? 25 : Integer.parseInt(request.getParameter("length"));
        int start = request.getParameter("start") == null ? 0 : Integer.parseInt(request.getParameter("start"));
        int pageNumber = start / length + 1;
        int sortColumn = request.getParameter("order[0][column]") == null ? 1 : Integer.parseInt(request.getParameter("order[0][column]"));
        boolean isAsc = "asc".equals(request.getParameter("order[0][dir]"));
        Map<String, Object> dataMap = new ConcurrentHashMap<>(0);
        dataMap.put("draw", dummyCounter);
        dataMap.put("recordsTotal", SCHEDULES.size());
        Map<Long, List<Scheduler>> map = filter(SCHEDULES, searchText, pageNumber, length, sortColumn, isAsc);
        map.forEach((key, value) -> {
            dataMap.put("recordsFiltered", key);
            dataMap.put("data", value);
        });
        return dataMap;
    }

    public Map<Long, List<Scheduler>> filter(List<Scheduler> schedules, String searchText, int pageNumber, int length, int sortColumn, boolean asc) {
        Collections.sort(schedules, null);
        int idx = (pageNumber - 1) * length;
        List<Scheduler> fListSchedules = schedules.stream().filter(schedule -> {
            return schedule.getDoctor().getName().contains(searchText);
        }).collect(Collectors.toList());
        List<Scheduler> pListSchedules = new ArrayList<>(0);
        int counter = 0;
        for (; idx < schedules.size(); idx++) {
            ++counter;
            if (counter < length) {
                pListSchedules.add(fListSchedules.get(idx));
            }
        }
        Map<Long, List<Scheduler>> m = new HashMap<>(0);
        m.put((long) fListSchedules.size(), pListSchedules);
        return m;
    }

    @RequestMapping(value = "/scheduler")
    public String showForm(@ModelAttribute("scheduler") Scheduler scheduler, HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        Optional<Scheduler> oScheduler = schedulerRepository.findById(scheduler.getId());
        if (oScheduler.isPresent()) {
            map.addAttribute("scheduler", oScheduler.get());
        } else {
            map.addAttribute("scheduler", new Scheduler());
        }
        return "scheduler";
    }

    @PostMapping(value = "/scheduler")
    public String save(@ModelAttribute("scheduler") Scheduler scheduler, HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        Scheduler s = schedulerRepository.save(scheduler);
        System.out.println(s);
        return "scheduler";
    }

    @ModelAttribute("events")
    public List<Event> event() {
        return eventRepository.findAll();
    }

    @ModelAttribute("doctors")
    public List<Doctor> doctor() {
        return doctorRepository.findAll();
    }

    @ModelAttribute("modes")
    public Map<String, String> mode() {
        return Helper.convertToMap(Helper.loadProperties(Constants.MODE_FILE_NAME));
    }

    @InitBinder
    public void binder(WebDataBinder binder) {
        CustomDateEditor dateTime = new CustomDateEditor(Constants.SDF, true);
        binder.registerCustomEditor(Date.class, "arrivalDate", dateTime);
        binder.registerCustomEditor(Date.class, "departureDate", dateTime);
    }

}
