package com.nachogonzalezbullon.input;

import com.nachogonzalezbullon.dto.PositionDTO;
import com.nachogonzalezbullon.dto.RobotDTO;
import com.nachogonzalezbullon.exceptions.InstructionsInitializationException;
import com.nachogonzalezbullon.exceptions.PositionInitializationException;
import com.nachogonzalezbullon.exceptions.RobotInitializationException;
import com.nachogonzalezbullon.mapper.InstructionsMapper;
import com.nachogonzalezbullon.mapper.PositionMapper;
import com.nachogonzalezbullon.mapper.RobotMapper;
import com.nachogonzalezbullon.model.Instruction;

import java.util.Collection;
import java.util.Scanner;

/**
 * @author nachoglezbul on 19/10/2019
 */
public class SimulatorCommandLineInputProcessor implements SimulatorInputProcessor {


    @Override
    public PositionDTO getPlanetUpperRightCoordinates() {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Insert upper right coordinates of the planet");
        System.out.println("(two numbers between 1 and 50 separated by a space)");
        return readPlanetInput(scanner);
    }

    private PositionDTO readPlanetInput(Scanner scanner) {
        try {
            return PositionMapper.mapPosition(scanner.nextLine());
        } catch (PositionInitializationException e) {
            System.out.println("You must insert two numbers from 1 to 50 separated by a space (e.g. 20 30)");
            return readPlanetInput(scanner);
        }
    }

    @Override
    public RobotDTO getRobot() {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Insert Coordinates and orientation of the robot");
        System.out.println("(two numbers between 1 and 50 and a letter to determine the orientation separated by a space)");

        return readRobotInput(scanner);
    }

    private RobotDTO readRobotInput(Scanner scanner) {
        try {
            return RobotMapper.mapRobotInput(scanner.nextLine());
        } catch (RobotInitializationException e) {
            System.out.println("You must insert two numbers from 1 to 50 separated by a space (e.g. 20 30)");
            return readRobotInput(scanner);
        }
    }

    @Override
    public Collection<Instruction> getInstructions() {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Insert the instructions for the robot");
        System.out.println("(a list of L (left) R (right) F (forward) instructions without spaces)");
        return readInstructionsInput(scanner);
    }

    private Collection<Instruction> readInstructionsInput(Scanner scanner) {
        try {
            return InstructionsMapper.mapInstructions(scanner.nextLine());
        } catch (InstructionsInitializationException e) {
            System.out.println("(a list of L (left) R (right) F (forward) instructions without spaces)");
            return readInstructionsInput(scanner);
        }
    }
}
