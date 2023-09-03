package dev.vavelin.example.liquint.demo

import dev.vavelin.example.liquint.demo.ui.handler.ErrorResponse
import dev.vavelin.example.liquint.intestspec.IntestSpecification
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import org.springframework.http.HttpStatus

class GetEmployeeEndpointTest extends IntestSpecification {

    private static final EXPECTED_EMPLOYEE_RESPONSE_JSON = """
            {
                "firstName":"John",
                "lastName":"Doe",
                "jobTitle":"Manager",
                "hireDate":"2023-09-01",
                "salary":60000
            }
        """

    def "should retrieve the employee with given id from database"() {
        given:
        dbAdmin.runSqlFile("db/runtime-sql/insert-employees.sql")

        when:
        def responseEntity = testRestTemplate.getForEntity(
                "http://localhost:$port/employee/1",
                String.class
        )

        then:
        JSONAssert.assertEquals(EXPECTED_EMPLOYEE_RESPONSE_JSON, responseEntity.body, JSONCompareMode.LENIENT)

    }

    def "should throw an error when employee with the given id does not exist"() {
        given:
        dbAdmin.runSqlFile("db/runtime-sql/insert-employees.sql")

        when:
        def responseEntity = testRestTemplate.getForEntity(
                "http://localhost:$port/employee/5",
                ErrorResponse.class
        )

        then:
        responseEntity.statusCode == HttpStatus.NOT_FOUND
        responseEntity.body.code().toString() == "ID_NOT_EXIST"
        responseEntity.body.message() == "Employee with the given id not found"
    }
}
