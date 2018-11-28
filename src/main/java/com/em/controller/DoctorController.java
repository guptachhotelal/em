package com.em.controller;

import com.em.entity.Doctor;
import com.em.entity.User;
import com.em.repository.DoctorRepository;
import com.em.repository.UserRepository;
import com.em.utils.Constants;
import com.em.utils.Helper;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DoctorController extends BaseController {

    private static final Logger LOGGER = LogManager.getLogger(DoctorController.class.getName());

    private static final List<Doctor> DOCTORS = new ArrayList<>(0);

    @Resource
    private DoctorRepository doctorRepository;
    @Resource
    private UserRepository userRepository;

    private final AtomicInteger dummyCounter = new AtomicInteger(0);

    @GetMapping(value = "/doctor")
    public String showForm(@ModelAttribute("doctor") Doctor doctor, HttpServletRequest request, HttpServletResponse response, ModelMap map, Principal principal) {
        Optional<Doctor> oDoctor = doctorRepository.findById(doctor.getId());
        if (oDoctor.isPresent()) {
            map.addAttribute("doctor", oDoctor.get());
        } else {
            map.addAttribute("doctor", new Doctor());
        }
        return "doctor";
    }

    @PostMapping(value = "/doctor")
    public String save(@ModelAttribute("doctor") Doctor doctor, HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        Doctor d = doctorRepository.save(doctor);
        LOGGER.info(d);
        return "doctor";
    }

    @GetMapping(value = "/doctors")
    public String doctors(@ModelAttribute("doctor") Doctor doctor, HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        return "doctors";
    }

    @ResponseBody
    @PostMapping(value = "fetchDoctors.json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> fetch(HttpServletRequest request) {
        if (DOCTORS.isEmpty()) {
            DOCTORS.addAll(doctorRepository.findAllByUser(getUser()));
        }
        String searchText = request.getParameter("search[value]") == null ? "" : request.getParameter("search[value]").toLowerCase().trim();
        int length = request.getParameter("length") == null ? 25 : Integer.parseInt(request.getParameter("length"));
        int start = request.getParameter("start") == null ? 0 : Integer.parseInt(request.getParameter("start"));
        int pageNumber = start / length + 1;
        int sortColumn = request.getParameter("order[0][column]") == null ? 1 : Integer.parseInt(request.getParameter("order[0][column]"));
        boolean isAsc = "asc".equals(request.getParameter("order[0][dir]"));
        Map<String, Object> dataMap = new ConcurrentHashMap<>(0);
        dataMap.put("draw", dummyCounter.incrementAndGet());
        dataMap.put("recordsTotal", DOCTORS.size());
        Map<Integer, List<Doctor>> map = filter(DOCTORS, searchText, pageNumber, length, sortColumn, isAsc);
        map.forEach((key, value) -> {
            dataMap.put("recordsFiltered", key);
            dataMap.put("data", value);
        });
        return dataMap;
    }

    public Map<Integer, List<Doctor>> filter(List<Doctor> doctors, String searchText, int pageNumber, int length, int sortColumn, boolean asc) {
//        Collections.sort(doctors, null);
        int idx = (pageNumber - 1) * length;
        List<Doctor> fListDoctors = doctors.stream().filter(doctor -> {
            return doctor.getName().contains(searchText);
        }).collect(Collectors.toList());
        List<Doctor> pListDoctors = new ArrayList<>(0);
        int counter = 0;
        for (; idx < fListDoctors.size(); idx++) {
            if (counter < length) {
                pListDoctors.add(fListDoctors.get(idx));
            }
            ++counter;
        }
        Map<Integer, List<Doctor>> m = new HashMap<>(0);
        m.put(fListDoctors.size(), pListDoctors);
        return m;
    }

    @ModelAttribute("users")
    public List<User> users() {
        return userRepository.findAll();
    }

    @ModelAttribute("specialities")
    public Map<String, String> speciality() {
        return Helper.convertToMap(Helper.loadProperties(Constants.SPECIALITY_FILE_NAME));
    }
}
