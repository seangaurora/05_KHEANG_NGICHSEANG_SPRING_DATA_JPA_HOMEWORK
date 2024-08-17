package com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private HttpStatus status;
    private String message;
    private T payload;
}
