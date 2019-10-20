package com.nachogonzalezbullon.mapper;

import com.nachogonzalezbullon.exceptions.InstructionsInitializationException;
import com.nachogonzalezbullon.model.Instruction;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Maps a simple input to {@link Instruction}.
 *
 * @author nachoglezbul on 20/10/2019
 */
public class InstructionsMapper {

    /**
     * The regex that must accomplish the input of the instructions. A list of one or more L, R or F characters.
     */
    private static final Pattern INSTRUCTIONS_PATTERN = Pattern.compile("^([LRF]+)\\s*$");

    /**
     * Maps the given string to a collection of {@link Instruction}.
     *
     * @param input the String that has been read from the input.
     * @return the collection of {@link Instruction}.
     * @throws InstructionsInitializationException if any of the instruction is not valid (it is not either L, R or F).
     */
    public static Collection<Instruction> mapInstructions(final String input) throws InstructionsInitializationException {

        final Matcher matcher = INSTRUCTIONS_PATTERN.matcher(input);

        if (matcher.find()) {
            final String instructionsInput = matcher.group(1);

            return Arrays.stream(instructionsInput.trim().split(""))
                    .map(Instruction::valueOfAlias)
                    .collect(Collectors.toList());
        } else {
            throw new InstructionsInitializationException("Invalid input");
        }
    }
}
