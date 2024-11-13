package com.spring.ecommerce.controller;

import com.spring.ecommerce.dto.AddressRequest;
import com.spring.ecommerce.entity.Address;
import com.spring.ecommerce.entity.User;
import com.spring.ecommerce.service.AddressService;
import com.spring.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@AllArgsConstructor
public class AddressController {

    private AddressService addressService;
    private UserService userService;

    @GetMapping()
    public List<Address> findAll(){
        return addressService.findAllAddresses();
    }

    @GetMapping("/{id}")
    public Address findById(@PathVariable long id){
        return addressService.findAddressById(id);
    }

    @PostMapping
    public Address saveAddress(@RequestBody AddressRequest addressRequest){
        User foundUser = userService.findUserEntityById(addressRequest.getUserId());

        Address address = new Address();
        address.setTitle(addressRequest.getTitle());
        address.setDescription(addressRequest.getDescription());
        address.setUser(foundUser);

        return addressService.saveAddress(address);
    }

    @PutMapping("/{id}")
    public Address updateAddress(@PathVariable long id, @RequestBody Address address){
        return addressService.updateAddress(id, address);
    }

    @DeleteMapping("/{id}")
    public Address deleteAddress(@PathVariable long id){
        return addressService.deleteAddress(id);
    }

}
