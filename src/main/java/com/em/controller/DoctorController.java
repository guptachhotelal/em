package com.em.controller;

import com.em.entity.Doctor;
import com.em.entity.SalesManager;
import com.em.repository.DoctorRepository;
import com.em.repository.SalesManagerRepository;
import com.em.utils.Constants;
import com.em.utils.Helper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DoctorController {

    @Resource
    private DoctorRepository doctorRepository;
    @Resource
    private SalesManagerRepository salesManagerRepository;

    private static final List<Doctor> DOCTORS = new ArrayList<>();

    private int dummyCounter;

    @GetMapping(value = "/doctor")
    public String showForm(@ModelAttribute("doctor") Doctor doctor, HttpServletRequest request, HttpServletResponse response, ModelMap map) {
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
        System.out.println(d);
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
            DOCTORS.addAll(doctorRepository.findAll());
        }
        String searchText = request.getParameter("search[value]") == null ? "" : request.getParameter("search[value]").toLowerCase().trim();
        int length = request.getParameter("length") == null ? 25 : Integer.parseInt(request.getParameter("length"));
        int start = request.getParameter("start") == null ? 0 : Integer.parseInt(request.getParameter("start"));
        int pageNumber = start / length + 1;
        int sortColumn = request.getParameter("order[0][column]") == null ? 1 : Integer.parseInt(request.getParameter("order[0][column]"));
        boolean isAsc = "asc".equals(request.getParameter("order[0][dir]"));
        Map<String, Object> dataMap = new ConcurrentHashMap<>(0);
        dataMap.put("draw", dummyCounter);
        dataMap.put("recordsTotal", DOCTORS.size());
        Map<Long, List<Doctor>> map = filter(DOCTORS, searchText, pageNumber, length, sortColumn, isAsc);
        map.forEach((key, value) -> {
            dataMap.put("recordsFiltered", key);
            dataMap.put("data", value);
        });
        return dataMap;
    }

    public Map<Long, List<Doctor>> filter(List<Doctor> doctors, String searchText, int pageNumber, int length, int sortColumn, boolean asc) {
//        Collections.sort(doctors, null);
        int idx = (pageNumber - 1) * length;
        List<Doctor> fListDoctors = doctors.stream().filter(doctor -> {
            return doctor.getName().contains(searchText);
        }).collect(Collectors.toList());
        List<Doctor> pListDoctors = new ArrayList<>(0);
        int counter = 0;
        for (; idx < doctors.size(); idx++) {
            ++counter;
            if (counter < length) {
                pListDoctors.add(fListDoctors.get(idx));
            }
        }
        Map<Long, List<Doctor>> m = new HashMap<>(0);
        m.put((long) fListDoctors.size(), pListDoctors);
        return m;
    }

    @ModelAttribute("salesManagers")
    public List<SalesManager> salesManager() {
        return salesManagerRepository.findAll();
    }

    @ModelAttribute("specialities")
    public Map<String, String> speciality() {
        return Helper.convertToMap(Helper.loadProperties(Constants.SPECIALITY_FILE_NAME));
    }

//    @RequestMapping(value = "/doctorForm", method = RequestMethod.POST)
//    public String addDoctorForm(@ModelAttribute("doctorForm") DoctorForm doctorForm, HttpServletRequest request, HttpServletResponse response) {
//        if (doctorForm.getId() > 0) {
//            doctorService.updateDoctorForm(doctorForm);
//        } else {
//            doctorService.createDoctorForm(doctorForm);
//        }
//        return "redirect:doctorList.htm";
//    }
//    @RequestMapping(value = "/doctorList")
//    public String listDoctors(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
//        List<DoctorForm> doctorList = doctorService.listAllDoctorForm();
//        map.addAttribute("doctorList", doctorList);
//        return "doctorList";
//    }
//    @RequestMapping(value = "/doctorByTeam")
//    public String listDoctorsByTeam(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
//        String tid = request.getParameter("tid");
//        if (tid != null && tid.trim().length() > 0) {
//            long teamId = Long.parseLong(tid);
//            TeamForm teamForm = teamService.findTeamFormById(teamId);
//            List<DoctorForm> doctorList = doctorService.findDoctorFormsByTeam(teamId);
//            map.addAttribute("teamForm", teamForm);
//            map.addAttribute("doctorForms", doctorList);
//        }
//        return "doctorByTeam";
//    }
}
