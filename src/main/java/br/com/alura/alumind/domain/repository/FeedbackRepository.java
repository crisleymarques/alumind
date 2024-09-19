package br.com.alura.alumind.domain.repository;

import br.com.alura.alumind.domain.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {}