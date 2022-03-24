package com.example.jpa2.repositories;

import com.example.jpa2.entities.Pat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PatientRepository extends JpaRepository<Pat,Long> {
//    public List<Pat> findByMalade(boolean m);
      public Page<Pat> findByMalade(boolean m, Pageable pageable);
//    List<Pat> findByMaladeAndAndScoreIsLessThan(boolean b,int score);
//    List<Pat> findByMaladeIsTrueAndAndScoreIsLessThan(boolean b,int score);
//    List<Pat> findByDateNaissanceBetween(Date date1, Date D2);
//    List<Pat> findByDateNaissanceBetweenAndMaladeIsTrueOrNomLike(Date date1, Date D2,String nom);

    @Query(value = "select * from Pat where nom like :z and score > :x",nativeQuery = true)
    List<Pat> chercherPatients(@Param("x") int score,@Param("z") String nom);
// @Query("select p from Pat p where  p.dateNaissance between :x and :y or p.nom like :z")
//    List<Pat> chercherPatients(@Param("x") Date date1,@Param("y") Date date2,@Param("z") String nom);

}
