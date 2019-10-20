package com.nachogonzalezbullon.model

import com.nachogonzalezbullon.exceptions.RobotInitializationException
import spock.lang.Specification

class RobotTest extends Specification {

    def "validate valid robot"() {
        when:
        def robot = new Robot(new Position(0, 0), Orientation.NORTH)

        then:
        robot != null
    }

    def "validate invalid robot"() {
        when:
        def robot = new Robot(position, orientation)

        then:
        robot == null
        def error = thrown(expectedException)
        error.message == expectedMessage

        where:
        position           | orientation      | expectedException            | expectedMessage
        null               | Orientation.EAST | RobotInitializationException | 'A robot needs an initial position'
        new Position(0, 0) | null             | RobotInitializationException | 'A robot needs an initial orientation'
    }

    def "validate instructions for a robot with orientation north"() {
        given:
        def robot = new Robot(new Position(0, 0), Orientation.NORTH)

        when:
        robot.obey(instruction)

        then:
        robot.getPosition() == newPosition
        robot.getOrientation() == newOrientation

        where:
        instruction         | newPosition        | newOrientation
        Instruction.FORWARD | new Position(0, 1) | Orientation.NORTH
        Instruction.RIGHT   | new Position(0, 0) | Orientation.EAST
        Instruction.LEFT    | new Position(0, 0) | Orientation.WEST
    }

    def "validate instructions for a robot with orientation east"() {
        given:
        def robot = new Robot(new Position(0, 0), Orientation.EAST)

        when:
        robot.obey(instruction)

        then:
        robot.getPosition() == newPosition
        robot.getOrientation() == newOrientation

        where:
        instruction         | newPosition        | newOrientation
        Instruction.FORWARD | new Position(1, 0) | Orientation.EAST
        Instruction.RIGHT   | new Position(0, 0) | Orientation.SOUTH
        Instruction.LEFT    | new Position(0, 0) | Orientation.NORTH
    }

    def "validate instructions for a robot with orientation south"() {
        given:
        def robot = new Robot(new Position(0, 0), Orientation.SOUTH)

        when:
        robot.obey(instruction)

        then:
        robot.getPosition() == newPosition
        robot.getOrientation() == newOrientation

        where:
        instruction         | newPosition         | newOrientation
        Instruction.FORWARD | new Position(0, -1) | Orientation.SOUTH
        Instruction.RIGHT   | new Position(0, 0)  | Orientation.WEST
        Instruction.LEFT    | new Position(0, 0)  | Orientation.EAST
    }

    def "validate instructions for a robot with orientation west"() {
        given:
        def robot = new Robot(new Position(0, 0), Orientation.WEST)

        when:
        robot.obey(instruction)

        then:
        robot.getPosition() == newPosition
        robot.getOrientation() == newOrientation

        where:
        instruction         | newPosition         | newOrientation
        Instruction.FORWARD | new Position(-1, 0) | Orientation.WEST
        Instruction.RIGHT   | new Position(0, 0)  | Orientation.NORTH
        Instruction.LEFT    | new Position(0, 0)  | Orientation.SOUTH
    }
}
