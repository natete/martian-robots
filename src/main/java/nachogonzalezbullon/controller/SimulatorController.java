package nachogonzalezbullon.controller;

import nachogonzalezbullon.dto.PositionDTO;
import nachogonzalezbullon.dto.RobotDTO;
import nachogonzalezbullon.model.Instruction;
import nachogonzalezbullon.model.Orientation;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author nachoglezbul on 19/10/2019
 */
public class SimulatorController {

    private static final Pattern PLANET_PATTERN = Pattern.compile("^(\\d+)\\s+(\\d+)\\s*$");
    private static final Pattern ROBOT_PATTERN = Pattern.compile("^(\\d+)\\s+(\\d+)\\s+([NESW])\\s*$");
    private static final Pattern INSTRUCTIONS_PATTERN = Pattern.compile("^([LRF]+)\\s*$");

    public PositionDTO getPlanetUpperRightCoordinates() {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Insert upper right coordinates of the planet");
        System.out.println("(two numbers between 1 and 50 separated by a space)");
        return readPlanetInput(scanner);
    }

    private PositionDTO readPlanetInput(Scanner scanner) {
        final String input = scanner.nextLine();

        final Matcher matcher = PLANET_PATTERN.matcher(input);

        if (matcher.find()) {
            final int coordinateX = Integer.parseInt(matcher.group(1));
            final int coordinateY = Integer.parseInt(matcher.group(2));

            return new PositionDTO(coordinateX, coordinateY);
        } else {
            System.out.println("You must insert two numbers from 1 to 50 separated by a space (e.g. 20 30)");
            return readPlanetInput(scanner);
        }
    }

    public RobotDTO getRobot() {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Insert Coordinates and orientation of the robot");
        System.out.println("(two numbers between 1 and 50 and a letter to determine the orientation separated by a space)");
        return readRobotInput(scanner);
    }

    private RobotDTO readRobotInput(Scanner scanner) {
        final String input = scanner.nextLine();

        final Matcher matcher = ROBOT_PATTERN.matcher(input);

        if (matcher.find()) {
            final int coordinateX = Integer.parseInt(matcher.group(1));
            final int coordinateY = Integer.parseInt(matcher.group(2));
            final String orientationInput = matcher.group(3);

            final Orientation orientation = Orientation.valueOfAlias(orientationInput);
            return new RobotDTO(coordinateX, coordinateY, orientation);
        } else {
            System.out.println("You must insert two numbers from 1 to 50 separated by a space (e.g. 20 30)");
            return readRobotInput(scanner);
        }
    }

    public List<Instruction> getInstructions() {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Insert the instructions for the robot");
        System.out.println("(a list of L (left) R (right) F (forward) instructions without spaces)");
        return readInstructionsInput(scanner);
    }

    private List<Instruction> readInstructionsInput(Scanner scanner) {
        final String input = scanner.nextLine();

        final Matcher matcher = INSTRUCTIONS_PATTERN.matcher(input);

        if (matcher.find()) {
            final String instructionsInput = matcher.group(1);

            return Arrays.stream(instructionsInput.trim().split(""))
                    .map(Instruction::valueOfAlias)
                    .collect(Collectors.toList());
        } else {
            System.out.println("(a list of L (left) R (right) F (forward) instructions without spaces)");
            return readInstructionsInput(scanner);
        }
    }
}
