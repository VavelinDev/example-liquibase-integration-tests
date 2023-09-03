package dev.vavelin.example.liquint

import dev.vavelin.example.liquint.intestspec.IntestSpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext

class ExampleLiquibaseIntegrationTestsApplicationTest extends IntestSpecification {

    @Autowired
    private ApplicationContext applicationContext

    def "load context"() {
        expect:
        println("Application: " + applicationContext.getId())
    }
}
