package com.manisha.electronic.store.ElectronicStore.dtos;

import lombok.*;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {
    private String meessage;
    private boolean success;

    private HttpStatus status;

}
