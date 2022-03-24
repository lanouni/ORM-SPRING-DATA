package com.example.jpa2;

import com.example.jpa2.entities.Pat;
import com.example.jpa2.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Jpa2Application implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(Jpa2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(new Pat(null,"souhayl",new Date(),false, 56));
        patientRepository.save(new Pat(null,"pdije",new Date(),false, 56));
        patientRepository.save(new Pat(null,"diledijw",new Date(),false, 56));
        List<Pat> patients = patientRepository.findAll();
        patients.forEach(p->{
            System.out.println("==================");
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getDateNaissance());
            System.out.println(p.getScore());
            System.out.println(p.isMalade());
        });
        System.out.println("*************");
        //Pat patient = patientRepository.findById(1L).orElseThrow(()->new RuntimeException("patient not found"));
        Pat patient = patientRepository.findById(1L).orElse(null);
        if (patient != null){
            System.out.println(patient.getNom());
            System.out.println(patient.isMalade());
        }
        patient.setScore(999);
        patientRepository.save(patient);
        patientRepository.deleteById(1L);
        Page<Pat> pats = patientRepository.findAll(PageRequest.of(0,5));
        System.out.println(pats.getTotalPages());
        System.out.println(pats.getTotalElements());
        System.out.println(pats.getNumber());
        List<Pat> contents = pats.getContent();
        //List<Pat> malade = patientRepository.findByMalade(true);
        Page<Pat> malade = patientRepository.findByMalade(true,PageRequest.of(0,5));
//        malade.forEach(p->{
//            System.out.println("==================");
//            System.out.println(p.getId());
//            System.out.println(p.getNom());
//            System.out.println(p.getDateNaissance());
//            System.out.println(p.getScore());
//            System.out.println(p.isMalade());
//        });

    }
}
