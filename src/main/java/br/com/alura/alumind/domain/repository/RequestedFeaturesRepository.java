package br.com.alura.alumind.domain.repository;

import br.com.alura.alumind.domain.entity.RequestedFeatures;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestedFeaturesRepository extends JpaRepository<RequestedFeatures, Long> {}