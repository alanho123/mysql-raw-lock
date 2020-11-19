package com.hqs.dblock;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DblockApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class DblockApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

	@Test
	public void browseCatalogTest() {
        log.info("###### browseCatalogTest");
	    String url = "http://localhost:8888/catalog";
	    for(int i = 0; i < 100; i++) {
	        final int num = i;
            log.info("###### browseCatalogTest {}", i);
	        new Thread(() -> {
                MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
                params.add("catalogId", "1");
                params.add("user", "user" + num);
                String result = testRestTemplate.postForObject(url, params, String.class);
                System.out.println("-------------" + result);

            }
            ).start();
        }
	}

    @Test
    public void browseCatalogTestRetry() {
        log.info("###### browseCatalogTestRetry");
        String url = "http://localhost:8888/catalogRetry";
        for(int i = 0; i < 100; i++) {
            final int num = i;
            log.info("###### browseCatalogTestRetry {}", i);
            new Thread(() -> {
                MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
                params.add("catalogId", "1");
                params.add("user", "user" + num);
                String result = testRestTemplate.postForObject(url, params, String.class);
                System.out.println("-------------" + result);
            }
            ).start();
        }
    }
}

