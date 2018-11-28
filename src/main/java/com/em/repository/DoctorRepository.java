package com.em.repository;

import com.em.entity.Doctor;
import com.em.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    List<Doctor> findAllByUser(User user);
}
