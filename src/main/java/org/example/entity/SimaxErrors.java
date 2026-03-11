package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "simax_errors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimaxErrors implements Serializable {

    @Id
    private Integer errorCode;

    @Column(nullable = false, length = 500)
    private String errorDescription;

    @Column(length = 50)
    private String errorCategory;
}
