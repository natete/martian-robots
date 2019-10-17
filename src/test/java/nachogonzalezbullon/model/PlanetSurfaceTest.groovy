package nachogonzalezbullon.model

import spock.lang.Specification

class PlanetSurfaceTest extends Specification {

    def "validate valid planet"() {
        when:
        def surface = new PlanetSurface(new Position(1, 1))

        then:
        surface != null
    }

    def "validate invalid planet"() {
        when:
        def position = new Position(x, y)
        def surface = new PlanetSurface(position)

        then:
        surface == null
        def error = thrown(expectedException)
        error.message == expectedMessage

        where:
        x  | y  | expectedException           | expectedMessage
        0  | 0  | ExceptionInInitializerError | 'A planet has to be two dimensional'
        1  | 0  | ExceptionInInitializerError | 'A planet has to be two dimensional'
        0  | 1  | ExceptionInInitializerError | 'A planet has to be two dimensional'
        -1 | 1  | ExceptionInInitializerError | 'A planet has to be two dimensional'
        1  | -1 | ExceptionInInitializerError | 'A planet has to be two dimensional'
    }

    def "it should validate if the position is inbounds"() {
        given:
        def mars = new PlanetSurface(new Position(5, 5))

        expect:
        mars.isInbounds(new Position(x, y)) == isInbounds

        where:
        x   | y   | isInbounds
        1   | 1   | true
        100 | 1   | false
        1   | 100 | false
        -1  | 1   | false
        1   | -1  | false
    }
}
