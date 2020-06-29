package com.skb.test.inventoryws.inventory;

import com.skb.test.inventoryws.converter.InventoryConverter;
import com.skb.test.inventoryws.converter.ManufacturerConverter;
import com.skb.test.inventoryws.exception.InventoryAlreadyExistException;
import com.skb.test.inventoryws.exception.InventoryNotFoundException;
import com.skb.test.inventoryws.manufacturer.ManufacturerEntity;
import com.skb.test.inventoryws.manufacturer.ManufacturerRepository;
import com.skb.test.inventoryws.testutils.InventoryWsTestUtil;
import com.skb.test.inventoryws.testutils.TestConstants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InventoryServiceTest {

    @Mock
    InventoryRepository inventoryRepository;

    @Mock
    ManufacturerRepository manufacturerRepository;

    ManufacturerConverter manufacturerConverter = new ManufacturerConverter();
    InventoryConverter inventoryConverter = new InventoryConverter(manufacturerConverter);

    InventoryService inventoryService;

    @Before
    public void setUp() {
        inventoryService = new InventoryService(inventoryRepository, manufacturerRepository, inventoryConverter);
    }

    @Test
    public void getAllInventory_success() {

        // Using doReturn().when() because while using when().thenReturn() got error:
        // ArrayList cannot be returned by toString() toString() should return String

        int skip = 0;
        int limit = 10;
        doReturn(InventoryWsTestUtil.createInventoryEntities(skip, limit)).when(inventoryRepository).findAll(any(Pageable.class));

        List<InventoryItem> inventoryItems = inventoryService.getAllInventory(skip, 10, TestConstants.API_TRACE_ID);
        assertEquals(inventoryItems.size(), limit);
    }

    @Test
    public void getAllInventory_noResult() {

        int skip = 0;
        int limit = 0;

        doReturn(InventoryWsTestUtil.createInventoryEntities(skip, limit)).when(inventoryRepository).findAll(any(Pageable.class));

        List<InventoryItem> inventoryItems = inventoryService.getAllInventory(0, 10, TestConstants.API_TRACE_ID);
        assertEquals(inventoryItems.size(), limit);
    }

    @Test
    public void getInventory_success() throws Exception {

        //Optional<InventoryEntity> inventoryEntityOptional = InventoryWsTestUtil.createInventoryEntityOptional();
        when(inventoryRepository.findByInventoryId(anyString()))
                .thenReturn(InventoryWsTestUtil.createInventoryEntityOptional());

        //doReturn(inventoryEntityOptional).when(inventoryRepository).findByInventoryId(anyString());
        InventoryItem inventory = inventoryService.getInventory(TestConstants.TEST_INVENTORY_ID, TestConstants.API_TRACE_ID);

        verify(inventoryRepository, times(1)).findByInventoryId(TestConstants.TEST_INVENTORY_ID);
        assertNotNull(inventory);
        assertNotNull(inventory.getId());
        
    }

    @Test(expected = InventoryNotFoundException.class)
    public void getInventory_failure() throws Exception {

        when(inventoryRepository.findByInventoryId(anyString()))
                .thenReturn(Optional.empty());
        inventoryService.getInventory(TestConstants.TEST_INVENTORY_ID, TestConstants.API_TRACE_ID);
        verify(inventoryRepository, times(1)).findByInventoryId(TestConstants.TEST_INVENTORY_ID);
    }

    @Test
    public void addInventoryItem_success() throws Exception {

        when(inventoryRepository.save(any(InventoryEntity.class)))
                .thenReturn(InventoryWsTestUtil.createInventoryEntity());
        InventoryItem inventoryItem = InventoryWsTestUtil.createInventoryItem();
        inventoryService.addInventoryItem(inventoryItem, TestConstants.API_TRACE_ID);

        verify(inventoryRepository, times(1)).save(any(InventoryEntity.class));
        assertNotNull(inventoryItem.getId());
        assertTrue(inventoryItem.getName().contains(TestConstants.TEST_INVENTORY_NAME));
    }

    @Test(expected = InventoryAlreadyExistException.class)
    public void addInventoryItem_failure_manufacturerWithDifferentPhone() throws Exception {

        when(manufacturerRepository.findByName(anyString()))
                .thenReturn(InventoryWsTestUtil.createManufacturerEntityDifferentPhoneOptional());
        InventoryItem inventoryItem = InventoryWsTestUtil.createInventoryItem();
        inventoryService.addInventoryItem(inventoryItem, TestConstants.API_TRACE_ID);

        verify(inventoryRepository, times(0)).save(any(InventoryEntity.class));

    }

    @Test(expected = InventoryAlreadyExistException.class)
    public void addInventoryItem_failure_manufacturerWithDifferentHomepage() throws Exception {

        when(manufacturerRepository.findByName(anyString()))
                .thenReturn(InventoryWsTestUtil.createManufacturerEntityDifferentHomepageOptional());
        InventoryItem inventoryItem = InventoryWsTestUtil.createInventoryItem();
        inventoryService.addInventoryItem(inventoryItem, TestConstants.API_TRACE_ID);

        verify(inventoryRepository, times(0)).save(any(InventoryEntity.class));

    }

    @Test(expected = InventoryAlreadyExistException.class)
    public void addInventoryItem_failure_inventoryWithSameNameAndManufacturer() throws Exception {

        when(manufacturerRepository.findByName(anyString()))
                .thenReturn(InventoryWsTestUtil.createManufacturerEntityOptional());

        /*when(inventoryRepository.findByNameAndManufacturerEntity(anyString(), any(ManufacturerEntity.class)))
                .thenReturn(InventoryWsTestUtil.createInventoryEntityOptional());*/

        InventoryItem inventoryItem = InventoryWsTestUtil.createInventoryItem();
        inventoryService.addInventoryItem(inventoryItem, TestConstants.API_TRACE_ID);

        verify(inventoryRepository, times(0)).save(any(InventoryEntity.class));

    }
}