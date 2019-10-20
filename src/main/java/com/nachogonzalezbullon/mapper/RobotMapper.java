package com.nachogonzalezbullon.mapper;

import com.nachogonzalezbullon.dto.RobotDTO;
import com.nachogonzalezbullon.exceptions.RobotInitializationException;
import com.nachogonzalezbullon.model.Orientation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Maps a simple input to {@link RobotDTO}.
 *
 * @author nachoglezbul on 20/10/2019
 */
public class RobotMapper {

    /**
     * The regex that must accomplish the input of a robot.
     * A pair of numbers and a character representing the orientation separated with spaces.
     */
    private static final Pattern ROBOT_PATTERN = Pattern.compile("^(\\d+)\\s+(\\d+)\\s+([NESW])\\s*$|^$");

    /**
     * Maps the given input to a valid {@link RobotDTO}
     *
     * @param input the String that has been read from the input.
     * @return a valid {@link RobotDTO}
     * @throws RobotInitializationException if the input is invalid.
     */
    public static RobotDTO mapRobotInput(String input) throws RobotInitializationException {
        final Matcher matcher = ROBOT_PATTERN.matcher(input);

        if (matcher.find()) {
            if (matcher.group(1) == null) {
                return null;
            }

            final int coordinateX = Integer.parseInt(matcher.group(1));
            final int coordinateY = Integer.parseInt(matcher.group(2));
            final String orientationInput = matcher.group(3);

            final Orientation orientation = Orientation.valueOfAlias(orientationInput);
            return new RobotDTO(coordinateX, coordinateY, orientation);
        } else {
            throw new RobotInitializationException("Invalid input");
        }
    }
}
