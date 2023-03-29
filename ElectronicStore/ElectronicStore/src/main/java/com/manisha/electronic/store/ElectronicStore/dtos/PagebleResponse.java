package com.manisha.electronic.store.ElectronicStore.dtos;

import lombok.*;
import org.hibernate.validator.internal.constraintvalidators.bv.time.future.AbstractFutureJavaTimeValidator;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagebleResponse<T> {
    private List<UserDto> content;

    private int pageNumber;
private int pageSize;
    private long totalElement;
    private int totalPages;
    private boolean lastPage;
}
