package com.unal.una_huella.UNaHuellaLauncher.Repositories;

import com.unal.una_huella.UNaHuellaLauncher.Entities.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {

}