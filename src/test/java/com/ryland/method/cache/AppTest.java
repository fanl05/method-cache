package com.ryland.method.cache;

import com.ryland.method.cache.config.AppConfig;
import com.ryland.method.cache.dto.Address;
import com.ryland.method.cache.remote.AddressRemote;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppTest {

    private AddressRemote addressRemote;

    @Before
    public void setUp() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        addressRemote = ctx.getBean(AddressRemote.class);
    }

    @Test
    public void testMethodCache1() {
        addressRemote.getAddressNames();
        addressRemote.getAddressNames();
    }

    @Test
    public void testMethodCache2() {
        addressRemote.getAddressNameByCode(25);
        addressRemote.getAddressNameByCode(25);
    }

    @Test
    public void testMethodCache3() {
        Address address = new Address();
        address.setCode(25);
        address.setName("南京");
        addressRemote.getLocation(address);
        addressRemote.getLocation(address);
    }

    @Test
    public void testMethodCache4() {
        addressRemote.getLocation(25, "南京");
        addressRemote.getLocation(25, "南京");
    }

    @Test
    public void testMethodCache5() {
        Address address = new Address();
        address.setCode(25);
        address.setName("南京");
        addressRemote.save(address);
        addressRemote.save(address);
    }

}
