package com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailResponse {
    private Long id;
    private String email;
}
