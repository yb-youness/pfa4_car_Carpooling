package  com.pfa.backend.repositories;

import com.pfa.backend.models.Trajet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface TrajetRepository extends JpaRepository<Trajet, Long> {

    @Query(value = "select * FROM Trajet  where id_user = :id",
            nativeQuery = true)
    Page<Trajet> FindById_user(Long id,Pageable pageable );


    @Query("Select t from Trajet t where t.villedepart = ?1 OR t.Villarrive = ?2 OR" +
            " t.datedepart = ?3 OR t.date_arrivee = ?4 OR t.nbr_places = ?5")
    Page<Trajet> search(String villedepart, String villarrive, Timestamp datedepart, Timestamp date_retour, Integer nbr_places,Pageable pageable);


    @Query("Select t from Trajet t where t.villedepart = ?1 OR t.Villarrive = ?1 OR t.datedepart = ?1 OR t.date_arrivee = ?1 OR t.nbr_places = ?1")
    List<Trajet> searchh(String word);

}