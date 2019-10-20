package com.nachogonzalezbullon.input;

import com.nachogonzalezbullon.dto.PositionDTO;
import com.nachogonzalezbullon.dto.RobotDTO;
import com.nachogonzalezbullon.model.Instruction;

import java.util.Collection;

/**
 * @author nachoglezbul on 20/10/2019
 */
public interface SimulatorInputProcessor {
    PositionDTO getPlanetUpperRightCoordinates();

    RobotDTO getRobot();

    Collection<Instruction> getInstructions();
}
