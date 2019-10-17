package nachogonzalezbullon.model

import spock.lang.Specification

class PositionTest extends Specification {

    def "validate valid position"() {
        when:
        def position = new Position(x, y)

        then:
        position != null

        where:
        x  | y
        0  | 0
        50 | 0
        0  | 50
        50 | 50
    }

    def "validate invalid position"() {
        when:
        def position = new Position(x, y)

        then:
        position == null
        def error = thrown(expectedException)
        error.message == expectedMessage

        where:
        x  | y  | expectedException           | expectedMessage
        51 | 0  | ExceptionInInitializerError | 'The maximum value for any coordinate is 50'
        0  | 51 | ExceptionInInitializerError | 'The maximum value for any coordinate is 50'
    }
}
