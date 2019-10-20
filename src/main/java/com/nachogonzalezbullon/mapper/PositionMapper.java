package com.nachogonzalezbullon.mapper;

import com.nachogonzalezbullon.dto.PositionDTO;
import com.nachogonzalezbullon.exceptions.PositionInitializationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Maps a simple input to {@link PositionDTO}.
 *
 * @author nachoglezbul on 20/10/2019
 */
public class PositionMapper {

    /**
     * The regex that must accomplish the input of a position. A pair of numbers separated by a space.
     */
    private static final Pattern POSITION_PATTERN = Pattern.compile("^(\\d+)\\s+(\\d+)\\s*$");

    /**
     * Maps the given input to a valid {@link PositionDTO}.
     *
     * @param input the String that has been read from the input.
     * @return a valid {@link PositionDTO} that matches the expected input (a couple of numbers with a space separating).
     * @throws PositionInitializationException if the input is invalid.
     */
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
