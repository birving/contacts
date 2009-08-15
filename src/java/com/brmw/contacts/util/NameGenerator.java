package com.brmw.contacts.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Utility class to generate a list of plausible unique names, given a display
 * name.
 * 
 * @author bruce
 * 
 */
public class NameGenerator {
    private static final String SPACE = " ";
    private static final String COMMA = ", ";
    // Some common prefixes and suffixes can be accommodated
    private static final List<String> PREFIXES = Arrays.asList("DR.", "DR", "MR.", "MR", "MRS.", "MRS", "MS.", "MS");
    private static final List<String> SUFFIXES = Arrays.asList("JR.", "JR", "II", "III", "PHD.", "PHD");

    public List<String> generateVariants(String displayName, String currentUniqueName) {

        // Normalize white space: no tabs, leading, trailing, multiples, etc.
        displayName = displayName.replaceAll("\\s+", " ").trim();

        Set<String> variants = new LinkedHashSet<String>();

        String prefix = "";
        String suffix = "";
        StringBuilder builder = new StringBuilder(255);

        // Split on white space
        List<String> part = new ArrayList<String>(Arrays.asList(displayName.split(",? ")));

        if (PREFIXES.contains(part.get(0).toUpperCase()) || PREFIXES.contains((part.get(0) + ".").toUpperCase())) {
            prefix = part.get(0) + SPACE;
            part.remove(0);
        }

        int last = part.size() - 1;
        if (SUFFIXES.contains(part.get(last).toUpperCase()) || SUFFIXES.contains((part.get(last) + ".").toUpperCase())) {
            suffix = SPACE + part.get(last);
            part.remove(last);
            last -= 1;
        }

        if (part.size() > 1) {
            builder.append(part.get(last)).append(COMMA).append(prefix).append(part.get(0)).append(suffix);
            variants.add(builder.toString());
        }

        if (part.size() > 2) {
            builder.setLength(0);
            builder.append(part.get(last)).append(COMMA).append(prefix);
            String sep = "";
            for (int i = 0; i < last; i++) {
                builder.append(sep).append(part.get(i));
                sep = SPACE;
            }
            builder.append(suffix);
            variants.add(builder.toString());

            builder.setLength(0);
            builder.append(part.get(last - 1))
                    .append(SPACE)
                    .append(part.get(last))
                    .append(COMMA)
                    .append(prefix);
            sep = "";
            for (int i = 0; i < last - 1; i++) {
                builder.append(sep).append(part.get(i));
                sep = SPACE;
            }
            builder.append(suffix);
            variants.add(builder.toString());
        }

        // Include normalized display name if not already there)
        variants.add(displayName);

        ArrayList<String> variantList = new ArrayList<String>(variants);

        // If there already is a different name saved with this user, then
        // include it as the first option
        if (currentUniqueName != null && !currentUniqueName.equals("") && !variants.contains(currentUniqueName)) {
            variantList.add(0, currentUniqueName);
        }

        return variantList;
    }
}
