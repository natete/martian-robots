package com.nachogonzalezbullon.input;

import com.nachogonzalezbullon.dto.PositionDTO;
import com.nachogonzalezbullon.dto.RobotDTO;
import com.nachogonzalezbullon.model.Instruction;

import java.util.Collection;

/**
 * Processes input for the simulation.
 * @author nachoglezbul on 20/10/2019
 */
public interface SimulatorInputProcessor {

    /**
     * Gets the position of the upper right corner to define a planet.
     *
     * @return the {@link PositionDTO} that represents the upper right corner coordinates inserted by the user.
     */
    PositionDTO getPlanetUpperRightCoordinates();

    /**
     * Gets a robot.
     *
     * @return the {@link RobotDTO} with the data inserted by the user.
     */
    RobotDTO getRobot();

    /**
     * Gets the position of the upper right corner to define a planet.
     *
     * @return the {@link PositionDTO} that represents the upper right corner coordinates inserted by the user.
     */
    Collection<Instruction> getInstructions();
}
