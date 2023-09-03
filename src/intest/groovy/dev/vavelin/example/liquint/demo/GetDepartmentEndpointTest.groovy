package dev.vavelin.example.liquint.demo

import dev.vavelin.example.liquint.demo.ui.handler.ErrorResponse
import dev.vavelin.example.liquint.intestspec.IntestSpecification
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import org.springframework.http.HttpStatus

class GetDepartmentEndpointTest extends IntestSpecification {

    private static final EXPECTED_DEPARTMENT_ID_1_RESPONSE_JSON = """
            {
                "name": "HR Department",
                "managerId": null,
                "location": "New York"
            }
        """

    private static final EXPECTED_DEPARTMENT_ID_2_RESPONSE_JSON = """
            {
                "name": "IT Department",
                "managerId": 3,
                "location": "San Francisco"
            }
        """

    def "should retrieve the department from database"() {
        given:
        dbAdmin.runSqlFile("db/runtime-sql/insert-departments.sql")

        when:
        def responseEntity = testRestTemplate.getForEntity(
                "http://localhost:$port/department/$departmentId",
                String.class
        )

        then:
        JSONAssert.assertEquals(expectedResponse, responseEntity.body, JSONCompareMode.LENIENT)

        where:
        departmentId || expectedResponse
        1            || EXPECTED_DEPARTMENT_ID_1_RESPONSE_JSON
        2            || EXPECTED_DEPARTMENT_ID_2_RESPONSE_JSON


    }

    def "should throw an error when department with the given id does not exist"() {
        given:
        dbAdmin.runSqlFile("db/runtime-sql/insert-departments.sql")

        when:
        def responseEntity = testRestTemplate.getForEntity(
                "http://localhost:$port/department/3",
                ErrorResponse.class
        )

        then:
        responseEntity.statusCode == HttpStatus.NOT_FOUND
        responseEntity.body.code().toString() == "ID_NOT_EXIST"
        responseEntity.body.message() == "Department with the given id not found"
    }
}
