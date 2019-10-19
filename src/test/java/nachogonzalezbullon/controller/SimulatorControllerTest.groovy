package nachogonzalezbullon.controller

import nachogonzalezbullon.controller.SimulatorController
import nachogonzalezbullon.dto.PositionDTO
import nachogonzalezbullon.dto.RobotDTO
import nachogonzalezbullon.model.Instruction
import nachogonzalezbullon.model.Orientation
import org.junit.Rule
import org.junit.contrib.java.lang.system.TextFromStandardInputStream
import spock.lang.Specification

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream

class SimulatorControllerTest extends Specification {

    @Rule
    TextFromStandardInputStream systemInMock = emptyStandardInputStream()

    SimulatorController controller

    def setup() {
        controller = new SimulatorController()
    }

    def "read input for creating a planet"() {
        expect:
        systemInMock.provideLines(line)
        controller.getPlanetUpperRightCoordinates() == position

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
        controller.getRobot() == robot

        where:
        line                | robot
        "20 20 N"           | new RobotDTO(20, 20, Orientation.NORTH)
        "foo 20 N\n20 20 N" | new RobotDTO(20, 20, Orientation.NORTH)
    }

    def "read input for instructions"() {
        expect:
        systemInMock.provideLines(line)
        controller.getInstructions() == instructions

        where:
        line             | instructions
        "LRF"            | [Instruction.LEFT, Instruction.RIGHT, Instruction.FORWARD] as List
        "LGF\nLRFG\nLRF" | [Instruction.LEFT, Instruction.RIGHT, Instruction.FORWARD] as List
    }
}
