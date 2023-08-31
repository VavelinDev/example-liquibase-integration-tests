package dev.vavelin.example.liquint

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("INTEST")
class ExampleLiquibaseIntegrationTestsApplicationTest extends Specification {

    @Autowired
    private ApplicationContext applicationContext

    def "load context"() {
        expect:
        println("Application: " + applicationContext.getId())
    }
}
