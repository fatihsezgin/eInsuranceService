package edu.aydin.insurance.Repository;

import edu.aydin.insurance.Entites.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
}
