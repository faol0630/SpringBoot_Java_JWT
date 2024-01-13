package com.faol.JWT.security.address.controller;

import com.faol.JWT.security.address.entity.Address;
import com.faol.JWT.security.address.service.AddressServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressServiceInt addressServiceInt;

    @GetMapping("/all")
    public ResponseEntity<?> getAllAdrresses() {

        List<Address> addressList = addressServiceInt.getAllAddresses();
        HashMap<String, Object> result = new HashMap<>();
        result.put("addressList", addressList);

        if (addressList.isEmpty()) {
            result.put("message", "error, not found or empty list");
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            result.put("message", "list ok");
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }

    }

    @GetMapping("/{address_id}")
    public ResponseEntity<HashMap<String, Object>> getAdrressById(@PathVariable Long address_id) {

        Optional<Address> address = addressServiceInt.getAddressById(address_id);

        HashMap<String, Object> result = new HashMap<>();

        if (address.isPresent()) {
            result.put("address", address);
            result.put("message", "address found");
            return ResponseEntity.status(HttpStatus.OK).body(result);

        } else {
            result.put("message", "error, not found address with id " + address_id + " or does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

    }

    @PostMapping("/new")
    public ResponseEntity<?> createNewAddress(@RequestBody Address address) {

        HashMap<String, Object> result = new HashMap<>();

        if (address.getAddress_id() != null) {
            Address newAddress = addressServiceInt.insertNewAddress(address);
            result.put("newAddress", newAddress);
            result.put("message", "address created");
            return ResponseEntity.status(HttpStatus.OK).body(result);

        } else {
            Address address1 = new Address();
            result.put("address1", address1);
            result.put("message", "error creating address");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

    }

    @PutMapping("/update/{address_id}")
    public ResponseEntity<?> updateAddress(@PathVariable Long address_id, @RequestBody Address address) {

        Optional<Address> addressToEdit = addressServiceInt.getAddressById(address_id);
        HashMap<String, Object> result = new HashMap<>();

        if (addressToEdit.isPresent()) {
            Address newAddress = addressServiceInt.updateAddress(address_id, address);
            result.put("newAddress", newAddress);
            result.put("message", "address updated");
            return ResponseEntity.status(HttpStatus.OK).body(result);

        } else {

            result.put("message", "empty address");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

    }

    @DeleteMapping("/delete/{address_id}")
    public ResponseEntity<?> deleteAddressById(@PathVariable Long address_id) {

        Optional<Address> addressToDelete = addressServiceInt.getAddressById(address_id);
        HashMap<String, Object> result = new HashMap<>();

        if (addressToDelete.isPresent()) {
            addressServiceInt.deleteAddress(address_id);
            result.put("addressToDelete", addressToDelete);
            result.put("message", "address deleted");
            return ResponseEntity.status(HttpStatus.OK).body(result);

        } else {

            result.put("message", "Not found address with id " + address_id + " .Nothing to delete");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<?> deleteAllAddresses() {

        List<Address> addressList = addressServiceInt.getAllAddresses();
        HashMap<String, Object> result = new HashMap<>();

        if (!addressList.isEmpty()) {
            addressServiceInt.deleteAllAddresses();
            result.put("message", "All addresses deleted");
            return ResponseEntity.status(HttpStatus.OK).body(result);

        } else {
            result.put("message", "Not found or empty list.Nothing to delete");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

    }

}

//NO_CONTENT status does not work with hashMap when isPresent == false.
////better to use NOT_FOUND instead.