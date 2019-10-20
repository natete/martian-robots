package com.nachogonzalezbullon.mapper;

import com.nachogonzalezbullon.dto.RobotDTO;
import com.nachogonzalezbullon.exceptions.RobotInitializationException;
import com.nachogonzalezbullon.model.Orientation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author nachoglezbul on 20/10/2019
 */
public class RobotMapper {
    private static final Pattern ROBOT_PATTERN = Pattern.compile("^(\\d+)\\s+(\\d+)\\s+([NESW])\\s*$|^$");

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
