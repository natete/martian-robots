package com.nachogonzalezbullon.mapper;

import com.nachogonzalezbullon.dto.PositionDTO;
import com.nachogonzalezbullon.exceptions.PositionInitializationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author nachoglezbul on 20/10/2019
 */
public class PositionMapper {

    private static final Pattern POSITION_PATTERN = Pattern.compile("^(\\d+)\\s+(\\d+)\\s*$");

    public static PositionDTO mapPosition(final String input) throws PositionInitializationException {
        final Matcher matcher = POSITION_PATTERN.matcher(input);

        if (matcher.find()) {
            final int coordinateX = Integer.parseInt(matcher.group(1));
            final int coordinateY = Integer.parseInt(matcher.group(2));

            return new PositionDTO(coordinateX, coordinateY);
        } else {
            throw new PositionInitializationException("Invalid input");
        }
    }
}
