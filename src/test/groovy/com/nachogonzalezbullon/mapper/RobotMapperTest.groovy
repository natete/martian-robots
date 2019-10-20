package com.nachogonzalezbullon.mapper

import com.nachogonzalezbullon.dto.RobotDTO
import com.nachogonzalezbullon.exceptions.RobotInitializationException
import com.nachogonzalezbullon.model.Orientation
import spock.lang.Specification

class RobotMapperTest extends Specification {

    def "map valid input for creating a robot"() {
        expect:
        RobotMapper.mapRobotInput(input) == robot

        where:
        input     | robot
        "20 20 N" | new RobotDTO(20, 20, Orientation.NORTH)
        "\n"      | null
    }

    def "map invalid input for creating a robot"() {
        when:
        RobotMapper.mapRobotInput(input)

        then:
        def error = thrown(expectedException)
        error.message == expectedMessage

        where:
        input      | expectedException            | expectedMessage
        "foo 20 N" | RobotInitializationException | "Invalid input"
        "20 20"    | RobotInitializationException | "Invalid input"
        "20 N 20"  | RobotInitializationException | "Invalid input"
        "20 20 F"  | RobotInitializationException | "Invalid input"
    }
}
