package ru.nstu.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;


@Entity
@Getter
@Setter
@Table
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String positionName;

    @ManyToOne()
    private Client client;

    private Integer salary;

    public Request(String positionName, Integer salary, Client client) {
        this.positionName = positionName;
        this.salary = salary;
        this.client = client;
    }
}
