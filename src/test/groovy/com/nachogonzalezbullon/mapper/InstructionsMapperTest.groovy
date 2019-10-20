package com.nachogonzalezbullon.mapper

import com.nachogonzalezbullon.exceptions.InstructionsInitializationException
import com.nachogonzalezbullon.model.Instruction
import spock.lang.Specification

class InstructionsMapperTest extends Specification {

    def "map valid input for instructions"() {
        expect:
        InstructionsMapper.mapInstructions(input) == instructions

        where:
        input | instructions
        "LRF" | [Instruction.LEFT, Instruction.RIGHT, Instruction.FORWARD] as List
        "L"   | [Instruction.LEFT] as List
    }

    def "map invalid input for instructions"() {
        when:
        InstructionsMapper.mapInstructions(input)

        then:
        def error = thrown(expectedException)
        error.message == expectedMessage

        where:
        input  | expectedException                   | expectedMessage
        ""     | InstructionsInitializationException | "Invalid input"
        "\n"   | InstructionsInitializationException | "Invalid input"
        "l"    | InstructionsInitializationException | "Invalid input"
        "FLRB" | InstructionsInitializationException | "Invalid input"
    }
}
