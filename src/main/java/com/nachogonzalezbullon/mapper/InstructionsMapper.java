package com.nachogonzalezbullon.mapper;

import com.nachogonzalezbullon.exceptions.InstructionsInitializationException;
import com.nachogonzalezbullon.model.Instruction;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author nachoglezbul on 20/10/2019
 */
public class InstructionsMapper {

    private static final Pattern INSTRUCTIONS_PATTERN = Pattern.compile("^([LRF]+)\\s*$");

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
