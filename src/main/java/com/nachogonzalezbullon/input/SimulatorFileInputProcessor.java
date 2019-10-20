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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Scanner;

/**
 * @author nachoglezbul on 20/10/2019
 */
public class SimulatorFileInputProcessor implements SimulatorInputProcessor {

    private final Scanner scanner;

    public SimulatorFileInputProcessor(final String filePath) throws RuntimeException {
        final File file = new File(filePath);
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Invalid file path");
        }
    }

    @Override
    public PositionDTO getPlanetUpperRightCoordinates() {
        try {
            return PositionMapper.mapPosition(scanner.nextLine());
        } catch (PositionInitializationException e) {
            throw new RuntimeException("The data in the file is invalid");
        }
    }

    @Override
    public RobotDTO getRobot() {
        try {
            if (scanner.hasNextLine()) {
                return RobotMapper.mapRobotInput(scanner.nextLine());
            } else {
                return null;
            }
        } catch (RobotInitializationException e) {
            throw new RuntimeException("The data in the file is invalid");
        }
    }

    @Override
    public Collection<Instruction> getInstructions() {
        try {
            return InstructionsMapper.mapInstructions(scanner.nextLine());
        } catch (InstructionsInitializationException e) {
            throw new RuntimeException("The data in the file is invalid");
        }
    }
}
