package com.faol.JWT.security.address.service;

import com.faol.JWT.security.address.entity.Address;

import java.util.List;
import java.util.Optional;

public interface AddressServiceInt {

    List<Address> getAllAddresses();
    Optional<Address> getAddressById(Long address_id);
    Address insertNewAddress(Address address);
    Optional<Address> updateAddress(Long address_id, Address newAddress);
    void deleteAddress(Long address_id);
    void deleteAllAddresses();
}
