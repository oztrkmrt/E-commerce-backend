package com.spring.ecommerce.service;

import com.spring.ecommerce.entity.Address;
import com.spring.ecommerce.exceptions.EcommerceException;
import com.spring.ecommerce.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> findAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Address findAddressById(long id) {
        Optional<Address> foundedAddress = addressRepository.findById(id);
        if (foundedAddress.isPresent()){
            return foundedAddress.get();
        }
        throw new EcommerceException("Address with given id not exist: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public Address updateAddress(long id, Address updatedAddress) {
        Optional<Address> existingAddressOpt = addressRepository.findById(id);

        if (existingAddressOpt.isPresent()){
            Address existingAddress = existingAddressOpt.get();
            existingAddress.setTitle(updatedAddress.getTitle());
            existingAddress.setDescription(updatedAddress.getDescription());
            existingAddress.setUser(updatedAddress.getUser());

            return  addressRepository.save(existingAddress);
        } else {
            throw new EcommerceException("Address with given id not exist: " + id, HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public Address deleteAddress(long id) {
        Address deletedAddress = findAddressById(id);
        addressRepository.delete(deletedAddress);
        return deletedAddress;
    }
}
