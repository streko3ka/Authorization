package ru.netology.authorization;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthorizationApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    private static final GenericContainer<?> developerVersion = new GenericContainer<>("developerVersion")
            .withExposedPorts(8080);
    private static final GenericContainer<?> productVersion = new GenericContainer<>("productVersion")
            .withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        developerVersion.start();
        productVersion.start();
    }


    @Test
    void devProfileTest() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + developerVersion.getMappedPort(8080) + "/profile", String.class);
        Assertions.assertEquals("Current profile is dev", forEntity.getBody());
    }

    @Test
    void prodProfileTest() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + productVersion.getMappedPort(8081) + "/profile", String.class);
        Assertions.assertEquals("Current profile is production", forEntity.getBody());
    }
}