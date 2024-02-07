package com.faol.JWT.security.address.service;

import com.faol.JWT.security.address.entity.Address;
import com.faol.JWT.security.address.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressServiceInt {

    @Autowired
    AddressRepository repo;

    @Override
    public List<Address> getAllAddresses() {
        return repo.findAll();
    }

    @Override
    public Optional<Address> getAddressById(Long address_id) {
        return repo.findById(address_id);
    }

    @Override
    public Address insertNewAddress(Address address) {

        if (address.getStreet() != null) {
            repo.save(address);
        }
        return address;
    }

    @Override
    public Optional<Address> updateAddress(Long address_id, Address newAddress) {

        Optional<Address> result = repo.findById(address_id);

        if (result.isPresent()) {

            Address address = result.orElseThrow();
            address.setNumber(newAddress.getNumber());
            address.setStreet(newAddress.getStreet());
            address.setPostcode(newAddress.getPostcode());
            repo.save(address);
            return Optional.of(address);

        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteAddress(Long address_id) {

        Optional<Address> result = repo.findById(address_id);
        if (result.isPresent()) {
            repo.deleteById(result.get().getAddress_id());
        }
    }

    @Override
    public void deleteAllAddresses() {

        repo.deleteAll();
    }
}


