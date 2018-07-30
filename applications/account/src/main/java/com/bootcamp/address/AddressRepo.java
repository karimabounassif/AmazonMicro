package com.bootcamp.address;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepo extends JpaRepository<Address, Integer> {

    public List<Address> findAllByAccountId(Integer id);
}
