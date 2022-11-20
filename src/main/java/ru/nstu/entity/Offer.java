package ru.nstu.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import ru.nstu.util.Gender;

@Entity
@Getter
@Setter
@Table
@NoArgsConstructor
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String positionName;

    @ManyToOne()
    private Employer employer;

    private Integer salary;

    private Gender gender;


    public Offer(String positionName, Integer salary, Gender gender, Employer employer) {
        this.positionName = positionName;
        this.gender = gender;
        this.salary = salary;
        this.employer = employer;
    }
}
