package com.ryland.method.cache.remote;

import com.ryland.method.cache.dto.Address;
import com.ryland.method.cache.dto.Location;

import java.util.List;

public interface AddressRemote {

    List<String> getAddressNames();

    String getAddressNameByCode(Integer code);

    Location getLocation(Address address);

    Location getLocation(Integer code, String name);

    void save(Address address);

}
