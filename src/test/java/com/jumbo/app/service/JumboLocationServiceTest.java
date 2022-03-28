package com.jumbo.app.service;

import com.jumbo.app.model.Store;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class JumboLocationServiceTest {

    @Autowired
    private JumboLocationService jumboLocationService;

    @BeforeEach
    public void setUp() {
        // add stores
        Store testStore1 = new Store();
        testStore1.setLatitude(1.0);
        testStore1.setLongitude(1.0);
        testStore1.setUuid("1");
        testStore1.setAddressName("City 1");

        Store testStore2 = new Store();
        testStore2.setLatitude(1.0);
        testStore2.setLongitude(2.0);
        testStore2.setUuid("2");
        testStore2.setAddressName("City 2");

        Store testStore3 = new Store();
        testStore3.setLatitude(2.0);
        testStore3.setLongitude(2.0);
        testStore3.setUuid("3");
        testStore3.setAddressName("City 3");

        jumboLocationService.addStore(testStore1);
        jumboLocationService.addStore(testStore2);
        jumboLocationService.addStore(testStore3);
    }

    @Test
    public void test_NullStoreThrowException () {
        Throwable exception = assertThrows(RuntimeException.class, () -> jumboLocationService.getClosetsPosition(null));
        Assertions.assertEquals("StoreNotFound", exception.getMessage());
    }

    @Test
    public void test_NotExistentStoreThrowException() {
        Throwable exception = assertThrows(RuntimeException.class, () -> jumboLocationService.getClosetsPosition("EMPTY"));
        Assertions.assertEquals("StoreNotFound", exception.getMessage());
    }

    @Test
    public void test_ExistentStoreByUUID() {
        List<Store> stores = jumboLocationService.getClosetsPosition("lxIKYx4XqGsAAAFIemMYwKxK");
        Assertions.assertEquals(5, stores.size());
    }

    @Test
    public void test_ExistentStoreByCoordinates() {
        List<Store> stores = jumboLocationService.getClosetsPosition(1, 2);
        Assertions.assertEquals(5, stores.size());
    }

    @Test
    public void test_CorrectResultsByUUID() {
        List<Store> stores = jumboLocationService.getClosetsPosition("lxIKYx4XqGsAAAFIemMYwKxK");
        Assertions.assertEquals("sIsKYx4X910AAAFKl_RHt8ey", stores.get(0).getUuid());
    }

    @Test
    public void test_CorrectResultsByUUIDAddedStores() {
        List<Store> stores = jumboLocationService.getClosetsPosition("1");
        Assertions.assertEquals("2", stores.get(0).getUuid());
        Assertions.assertEquals("3", stores.get(1).getUuid());
    }

    @Test
    public void test_CorrectResultsByCoordinatesAddedStores() {
        List<Store> stores = jumboLocationService.getClosetsPosition(1.0, 2.0);
        Assertions.assertEquals("3", stores.get(0).getUuid());
    }

    @Test
    public void test_CreateStoreThrowException() {
        Store testStore = new Store();
        testStore.setLatitude(6.0);
        testStore.setLongitude(12.0);
        testStore.setUuid("4");

        Throwable exception = assertThrows(RuntimeException.class, () -> jumboLocationService.addStore(testStore));
        Assertions.assertEquals("AddressCannotBeEmpty", exception.getMessage());
    }

    @Test
    public void test_CreateStore() {
        Store testStore4 = new Store();
        testStore4.setLatitude(6.0);
        testStore4.setLongitude(12.0);
        testStore4.setUuid("4");
        testStore4.setAddressName("City 4");

        Store storeCreated = jumboLocationService.addStore(testStore4);
        Assertions.assertEquals(storeCreated.getUuid(), testStore4.getUuid());
    }

}
