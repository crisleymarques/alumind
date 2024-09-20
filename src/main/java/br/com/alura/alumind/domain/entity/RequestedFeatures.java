package br.com.alura.alumind.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * @author Crisley Marques
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "requested_features")
public class RequestedFeatures {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 45)
    @Column(length = 45)
    private String code;

    @NotBlank
    @Size(min = 3, max = 255)
    private String reason;

    @ManyToMany(mappedBy = "requestedFeatures")
    private List<Feedback> feedbacks = new ArrayList<>();
}

