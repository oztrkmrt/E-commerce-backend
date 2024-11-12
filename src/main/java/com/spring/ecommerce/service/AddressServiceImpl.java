package com.spring.ecommerce.service;

import com.spring.ecommerce.entity.Address;
import com.spring.ecommerce.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        //TODO: exception handling :((
        return null;
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
            //TODO: Exception handling gerekiyor buraya
            throw new RuntimeException("Address not found: " + updatedAddress);
        }

    }

    @Override
    public Address deleteAddress(long id) {
        Address deletedAddress = findAddressById(id);
        addressRepository.delete(deletedAddress);
        return deletedAddress;
    }
}
