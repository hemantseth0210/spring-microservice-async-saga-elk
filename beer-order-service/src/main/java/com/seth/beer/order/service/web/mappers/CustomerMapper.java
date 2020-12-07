package com.seth.beer.order.service.web.mappers;

import com.seth.beer.order.service.domain.Customer;
import com.seth.brewery.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface CustomerMapper {
    CustomerDto customerToDto(Customer customer);

    Customer dtoToCustomer(Customer dto);
}
