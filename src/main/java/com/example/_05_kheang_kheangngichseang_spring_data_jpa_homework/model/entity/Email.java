package com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.entity;

import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.response.EmailResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "emails")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(mappedBy = "email")
    private Customer customer;


    public EmailResponse toResponse() {
        return new EmailResponse(this.id, this.email);
    }
}
