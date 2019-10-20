package com.nachogonzalezbullon.input

import com.nachogonzalezbullon.dto.PositionDTO
import com.nachogonzalezbullon.dto.RobotDTO
import com.nachogonzalezbullon.model.Instruction
import com.nachogonzalezbullon.model.Orientation
import org.junit.Rule
import org.junit.contrib.java.lang.system.TextFromStandardInputStream
import spock.lang.Specification

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream

class SimulatorCommandLineInputProcessorTest extends Specification {

    @Rule
    TextFromStandardInputStream systemInMock = emptyStandardInputStream()

    SimulatorCommandLineInputProcessor inputProcessor

    def setup() {
        inputProcessor = new SimulatorCommandLineInputProcessor()
    }

    def "read input for creating a planet"() {
        expect:
        systemInMock.provideLines(line)
        inputProcessor.getPlanetUpperRightCoordinates() == position

        where:
        line         | position
        "20 20"      | new PositionDTO(20, 20)
        "20   20"    | new PositionDTO(20, 20)
        "20   20   " | new PositionDTO(20, 20)
        "foo\n20 20" | new PositionDTO(20, 20)
    }

    def "read input for creating a robot"() {
        expect:
        systemInMock.provideLines(line)
        inputProcessor.getRobot() == robot

        where:
        line                | robot
        "20 20 N"           | new RobotDTO(20, 20, Orientation.NORTH)
        "foo 20 N\n20 20 N" | new RobotDTO(20, 20, Orientation.NORTH)
        "\n"                | null
    }

    def "read input for instructions"() {
        expect:
        systemInMock.provideLines(line)
        inputProcessor.getInstructions() == instructions

        where:
        line             | instructions
        "LRF"            | [Instruction.LEFT, Instruction.RIGHT, Instruction.FORWARD] as List
        "LGF\nLRFG\nLRF" | [Instruction.LEFT, Instruction.RIGHT, Instruction.FORWARD] as List
    }
}
