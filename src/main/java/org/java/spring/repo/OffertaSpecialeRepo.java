package org.java.spring.repo;

import org.java.spring.pojo.OffertaSpeciale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffertaSpecialeRepo extends JpaRepository<OffertaSpeciale, Integer>{

}
