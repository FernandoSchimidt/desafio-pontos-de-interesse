package com.fernandoschimidt.pontointeresse.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table()
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer x;
    private Integer y;
}
