package com.skb.test.inventoryws.inventory;

import com.skb.test.inventoryws.testutils.InventoryWsTestUtil;
import com.skb.test.inventoryws.testutils.TestConstants;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InventoryControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void getInventory_success() {

        ResponseEntity<InventoryItem> response = addInventory();
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        InventoryItem inventoryItem = response.getBody();
        String id = inventoryItem.getId();

        // Populate Basic Auth header
        MultiValueMap<String, String> headers = createBasicAuthHeader();

        URI getPublisherUri = null;
        try {
            getPublisherUri = new URI(TestConstants.INVENTORY_URI + "/" + id);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        // Add Authorization Token and create request entity
        ResponseEntity<InventoryItem> inventoryItemRE = testRestTemplate.exchange(getPublisherUri, HttpMethod.GET, new HttpEntity<Object>(headers), InventoryItem.class);

        Assert.assertEquals(HttpStatus.OK, inventoryItemRE.getStatusCode());
        InventoryItem inventoryItemBody = inventoryItemRE.getBody();
        Assert.assertNotNull(inventoryItemBody.getId());
        Assert.assertTrue(inventoryItemBody.getName().contains(TestConstants.TEST_INVENTORY_NAME));

    }

    @Test
    public void addInventory_success() {

        ResponseEntity<InventoryItem> response = addInventory();
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        InventoryItem inventoryItem = response.getBody();
        Assert.assertNotNull(inventoryItem);
        Assert.assertNotNull(inventoryItem.getId());
        Assert.assertTrue(inventoryItem.getName().contains(TestConstants.TEST_INVENTORY_NAME));

    }

    private ResponseEntity<InventoryItem> addInventory() {

        MultiValueMap<String, String> headers = createBasicAuthHeader();

        URI inventoryApiUri = null;
        try {
            inventoryApiUri = new URI(TestConstants.INVENTORY_URI);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        // Add Authorization Token and create request entity
        HttpEntity<InventoryItem> inventoryItemReq = new HttpEntity<>(InventoryWsTestUtil.createInventoryItem(), headers);

        // Send the request
        return testRestTemplate.exchange(inventoryApiUri, HttpMethod.POST, inventoryItemReq, InventoryItem.class);
    }

    /**
     * Creates Basic Auth header
     * @return
     */
    private MultiValueMap<String, String> createBasicAuthHeader() {

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        String creds = TestConstants.LOGIN_USERNAME + ":" + TestConstants.LOGIN_PASSWORD;
        String authHeader = Base64.getEncoder().encodeToString(creds.getBytes());
        headers.add("Authorization", "Basic " + authHeader);

        return headers;
    }
}