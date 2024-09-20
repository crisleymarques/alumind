package br.com.alura.alumind.domain.repository;

import br.com.alura.alumind.domain.entity.RequestedFeatures;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Crisley Marques
 */
public interface RequestedFeaturesRepository extends JpaRepository<RequestedFeatures, Long> {
    RequestedFeatures findByCode(String code);
}