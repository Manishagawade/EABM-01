package com.manisha.electronic.store.ElectronicStore.helper;

import com.manisha.electronic.store.ElectronicStore.dtos.PagebleResponse;
import com.manisha.electronic.store.ElectronicStore.dtos.UserDto;
import com.manisha.electronic.store.ElectronicStore.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class Helper {

        public static <U,V> PagebleResponse<V> getPagebleResponse(Page<U> page,Class<V>type){
            List<U> entiry= page.getContent();
            List<V> dtoList= entiry.stream().map(object -> new ModelMapper().map(object,type)).collect(Collectors.toList());
            PagebleResponse<V> response=new PagebleResponse<>();
            response.setContent((List<UserDto>) dtoList);
            response.setPageNumber(page.getNumber());
            response.setPageSize(page.getSize());
            response.setTotalElement(page.getTotalElements());
            response.setTotalPages(page.getTotalPages());
            response.setLastPage(page.isLast());
            return response;
    }
}
