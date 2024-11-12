package com.spring.ecommerce.service;

import com.spring.ecommerce.entity.Address;

import java.util.List;

public interface AddressService {

    List<Address> findAllAddresses();

    Address findAddressById(long id);

    Address updateAddress(long id, Address address);

    Address deleteAddress(long id);

}
