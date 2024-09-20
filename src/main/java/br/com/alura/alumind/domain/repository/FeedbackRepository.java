package br.com.alura.alumind.domain.repository;

import br.com.alura.alumind.domain.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Crisley Marques
 */
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Feedback findByDescription(String description);
}
