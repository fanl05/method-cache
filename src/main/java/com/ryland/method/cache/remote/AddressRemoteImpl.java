package com.ryland.method.cache.remote;

import com.ryland.method.cache.anno.Cache;
import com.ryland.method.cache.dto.Address;
import com.ryland.method.cache.dto.Location;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class AddressRemoteImpl implements AddressRemote {

    @Override
    @Cache
    public List<String> getAddressNames() {
        List<String> names = new ArrayList<>();
        names.add("上海");
        names.add("北京");
        names.add("深圳");
        names.add("南京");
        return names;
    }

    @Override
    @Cache
    public String getAddressNameByCode(Integer code) {
        return "南京";
    }

    @Override
    @Cache
    public Location getLocation(Address address) {
        Location location = new Location();
        location.setLon(100D);
        location.setLat(90D);
        return location;
    }

    @Override
    @Cache
    public Location getLocation(Integer code, String name) {
        Location location = new Location();
        location.setLon(100D);
        location.setLat(90D);
        return location;
    }

    @Override
    @Cache
    public void save(Address address) {
        log.debug("{}", address);
    }
}
