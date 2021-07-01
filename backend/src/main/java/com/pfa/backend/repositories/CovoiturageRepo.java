package com.pfa.backend.repositories;

import com.pfa.backend.models.Covoiturage;
import com.pfa.backend.models.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface CovoiturageRepo extends JpaRepository<Covoiturage, Long>{

    @Query(value = "select * FROM Covoiturage  where id_user = :id",
            nativeQuery = true)
    List<Covoiturage> FindById_user(Long id );

    @Modifying
    @Transactional
    @Query(
            value =
                    "insert into Covoiturage (nbr_places, trajet_id, id_user) values (:nbr_places, :trajet_id, :id_user)",
            nativeQuery = true)
    void insertUser( @Param(value = "nbr_places") Long nbr_places,
                     @Param(value = "trajet_id") Long trajet_id,
                     @Param(value = "id_user") Long id_user);

}
