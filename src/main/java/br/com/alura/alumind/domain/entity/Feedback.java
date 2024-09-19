package br.com.alura.alumind.domain.entity;

import br.com.alura.alumind.enumeration.Sentiment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * @author Crisley Marques
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToMany(fetch = LAZY)
    @JoinTable(
            name = "feedback_requested_features",
            joinColumns = @JoinColumn(name = "id_feedback"),
            inverseJoinColumns = @JoinColumn(name = "id_requested_feature")
    )
    private List<RequestedFeatures> requestedFeatures = new ArrayList<>();

    @NotNull
    @Enumerated(EnumType.STRING)
    private Sentiment sentiment;

    @NotNull
    private String description;
}
