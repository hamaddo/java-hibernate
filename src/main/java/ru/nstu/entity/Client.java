package ru.nstu.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import ru.nstu.util.Gender;

import java.util.List;

@Entity
@Getter
@Setter
@Table
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String patronymic;

    @Formula(value = "concat(surname, ' ', name, ' ', patronymic)")
    private String fullName;

    private Integer registry_number;

    private String address;

    private Gender gender;

    private Integer receipt_number;

    private String phone;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Request> requests;

    public Client(String name, String surname, String patronymic, String address, String phone, Gender gender) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
    }
}
