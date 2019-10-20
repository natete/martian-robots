package com.nachogonzalezbullon.mapper

import com.nachogonzalezbullon.dto.PositionDTO
import com.nachogonzalezbullon.exceptions.PositionInitializationException
import spock.lang.Specification

class PositionMapperTest extends Specification {

    def "map valid input for creating a planet"() {
        expect:
        PositionMapper.mapPosition(input) == position

        where:
        input        | position
        "20 20"      | new PositionDTO(20, 20)
        "20   20"    | new PositionDTO(20, 20)
        "20   20   " | new PositionDTO(20, 20)
    }

    def "map invalid input for creating a planet"() {
        when:
        PositionMapper.mapPosition(input)

        then:
        def error = thrown(expectedException)
        error.message == expectedMessage

        where:
        input     | expectedException               | expectedMessage
        ""        | PositionInitializationException | "Invalid input"
        "ten ten" | PositionInitializationException | "Invalid input"
        "20"      | PositionInitializationException | "Invalid input"
        "20 foo"  | PositionInitializationException | "Invalid input"
    }
}