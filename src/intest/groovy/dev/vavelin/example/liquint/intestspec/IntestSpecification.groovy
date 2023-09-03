package dev.vavelin.example.liquint.intestspec

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("INTEST")
class IntestSpecification extends Specification {
    @Autowired
    private DbAdmin dbAdmin

    @LocalServerPort
    private Integer port

    private TestRestTemplate testRestTemplate = new TestRestTemplate()

    def setup() {
    }

    def cleanup() {
        dbAdmin.deleteAllData()
    }

    protected DbAdmin getDbAdmin() {
        return dbAdmin
    }

    protected TestRestTemplate getTestRestTemplate() {
        return testRestTemplate
    }

    protected Integer getPort() {
        return port
    }
}