package com.pfa.backend.repositories;

import com.pfa.backend.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)

public interface UserRepo extends JpaRepository<User,Long> {
  User findByEmail(String email);
  @Modifying
  @Transactional
  @Query("UPDATE User u SET u.nom = :nom , u.prenom = :prenom, u.email = :email, u.tel = :tel WHERE u.id = :id")
  void updatee(@Param("id") Long id, @Param("nom") String nom, @Param("prenom") String pernom, @Param("email") String email, @Param("tel") String tel);
}
