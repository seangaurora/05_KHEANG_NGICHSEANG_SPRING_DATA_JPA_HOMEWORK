package com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.dto.request;

import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.entity.Customer;
import com.example._05_kheang_kheangngichseang_spring_data_jpa_homework.model.entity.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private String customerName;
    private String address;
    private String phoneNumber;
    private String email;

    public Customer toEntity(){
        Email customerEmail = new Email();
        customerEmail.setEmail(email);
        return new Customer(null, this.customerName, this.address, this.phoneNumber, customerEmail, null);
    }

    public Customer toEntity(Long id){
        Email customerEmail = new Email();
        customerEmail.setEmail(email);
        return new Customer(id, this.customerName, this.address, this.phoneNumber, customerEmail, null);
    }
}
