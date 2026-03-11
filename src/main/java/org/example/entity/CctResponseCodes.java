package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "cct_response_codes")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CctResponseCodes implements Serializable {

    @Id
    private Integer responseCode;

    @Column(nullable = false, length = 500)
    private String responseDescription;

    @Column(length = 25)
    private String responseCategory;

}
