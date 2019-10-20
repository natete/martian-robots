package com.nachogonzalezbullon;

import com.nachogonzalezbullon.dto.PositionDTO;
import com.nachogonzalezbullon.dto.RobotDTO;
import com.nachogonzalezbullon.exceptions.PlanetException;
import com.nachogonzalezbullon.exceptions.PositionInitializationException;
import com.nachogonzalezbullon.input.SimulatorCommandLineInputProcessor;
import com.nachogonzalezbullon.input.SimulatorFileInputProcessor;
import com.nachogonzalezbullon.input.SimulatorInputProcessor;
import com.nachogonzalezbullon.model.Instruction;
import com.nachogonzalezbullon.model.Planet;
import com.nachogonzalezbullon.model.Position;
import com.nachogonzalezbullon.model.Robot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Simulator implementation of the Planet project.
 *
 * @author nachoglezbul on 19/10/2019
 */
public class Simulator {

    private final SimulatorInputProcessor inputProcessor;
    private final Collection<Robot> robots;

    /**
     * Creates an instance of th Simulator.
     * It will use the {@link SimulatorFileInputProcessor} to process a file if an input path is provided
     * otherwise it will use the {@link  SimulatorCommandLineInputProcessor} to process input from command line.
     *
     * @param filePath the file path of the file to be processed if any.
     * @throws RuntimeException if the provided path does not contain a valid file.
     */
    public Simulator(final String filePath) {
        if (filePath == null) {
            this.inputProcessor = new SimulatorCommandLineInputProcessor();
        } else {
            this.inputProcessor = new SimulatorFileInputProcessor(filePath);
        }

        robots = new ArrayList<>();
    }

    /**
     * Simulates the execution of the instructions coming from the Earth.
     *
     * @throws RuntimeException if an execution file is provided and does not contain a valid input.
     */
    public void simulate() {
        final Planet planet = processPlanet();

        processRobots(planet);

        robots.forEach(System.out::println);
    }

    /**
     * Reads the planet from the given input.
     *
     * @return the {@link Planet} where the simulation will take place.
     * @throws RuntimeException if an execution file is provided and does not contain a valid input.
     */
    private Planet processPlanet() {
        final PositionDTO planetUpperRightCoordinates = inputProcessor.getPlanetUpperRightCoordinates();
        try {
            final Position position = new Position(planetUpperRightCoordinates.getX(), planetUpperRightCoordinates.getY());
            return new Planet(position);
        } catch (PlanetException e) {
            return processPlanet();
        }
    }

    /**
     * Processes the robots and their instructions read from the given input.
     *
     * @param planet the {@link Planet} where the simulation will take place.
     * @throws RuntimeException if an execution file is provided and does not contain a valid input.
     */
    private void processRobots(Planet planet) {
        Robot robot = getRobot();

        while (robot != null) {
            robots.add(robot);

            final Collection<Instruction> instructions = getInstructions();

            processInstructions(instructions, robot, planet);

            robot = getRobot();
        }
    }

    /**
     * Gets a {@link Robot} from the given input.
     *
     * @return the {@link Robot}.
     * @throws RuntimeException if an execution file is provided and does not contain a valid input.
     */
    private Robot getRobot() {
        final RobotDTO robotDTO = inputProcessor.getRobot();

        if (robotDTO != null) {

            try {
                return new Robot(new Position(robotDTO.getX(), robotDTO.getY()), robotDTO.getOrientation());
            } catch (PlanetException e) {
                return getRobot();
            }
        } else {
            return null;
        }
    }

    /**
     * Gets a collection of {@link Instruction} from the given input.
     *
     * @return the collection of {@link Instruction}.
     * @throws RuntimeException if an execution file is provided and does not contain a valid input.
     */
    private Collection<Instruction> getInstructions() {
        return inputProcessor.getInstructions();
    }


    /**
     * Processes the given collection of {@link Instruction} for the given {@link Robot} in the given {@link Planet}.
     *
     * @param instructions the collection of {@link Instruction} that have to be processed.
     * @param robot        the  {@link Robot} that have to accomplish the instructions.
     * @param planet       the {@link Planet} where the instructions take place.
     */
    private void processInstructions(Collection<Instruction> instructions, Robot robot, Planet planet) {
        final Iterator<Instruction> iterator = instructions.iterator();

        while (iterator.hasNext() && robot.isAlive()) {
            final Instruction instruction = iterator.next();
            final Position oldPosition = new Position(robot.getPosition());

            try {
                robot.obey(instruction);

                if (!planet.isInbounds(robot.getPosition())) {
                    handleRobotGoingOffEdge(robot, oldPosition, planet);
                }
            } catch (PositionInitializationException e) {
                handleRobotGoingOffEdge(robot, oldPosition, planet);
            }
        }
    }

    /**
     * Handles a robot going of edge setting it back to the las position that it occupied.
     * If the position does not contain a scent it also kills it and leaves the scent in the position.
     *
     * @param robot       the {@link Robot} that went off edge.
     * @param oldPosition the last {@link Position} that the robot occupied before going off edge.
     * @param planet      the {@link Planet} where the simulation takes place.
     */
    private void handleRobotGoingOffEdge(Robot robot, Position oldPosition, Planet planet) {
        robot.moveTo(oldPosition);

        if (!planet.isScentPosition(oldPosition)) {
            planet.addScentPosition(oldPosition);
            robot.die();
        }
    }
}
