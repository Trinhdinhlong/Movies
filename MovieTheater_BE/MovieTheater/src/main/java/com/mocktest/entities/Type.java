package com.mocktest.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "types", schema = "dbo")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Long id;

    @Column(name = "type_name")
    private String typeName;

    @ManyToMany(mappedBy = "typeMovies")
    private Set<Movie> movies;
}
