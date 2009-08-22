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
    // Prefixes can be ignored since the algorithm is not affected by them.
    // PREFIXES = Arrays.asList("DR.", "MR.", "MRS.", "MS.", "MS");
    // Some common suffixes can be accommodated - Add more if needed.
    private static final List<String> SUFFIXES = Arrays.asList("JR.", "II", "III", "PHD.", "DDS.");

    public List<String> generateVariants(String displayName, String currentUniqueName) {

        // Normalize white space: no tabs, leading, trailing, multiples, etc.
        displayName = (displayName == null) ? "" : displayName.replaceAll("\\s+", " ").trim();

        Set<String> variants = new LinkedHashSet<String>();

        String suffix = "";
        StringBuilder builder = new StringBuilder(255);

        // Split on white space
        List<String> part = new ArrayList<String>(Arrays.asList(displayName.split(",? ")));

        int last = part.size() - 1;
        if (SUFFIXES.contains(part.get(last).toUpperCase()) || SUFFIXES.contains((part.get(last) + ".").toUpperCase())) {
            suffix = SPACE + part.get(last);
            part.remove(last);
            last -= 1;
        }

        // First option - Include normalized display name
        variants.add(displayName);

        // Second option (default) - Last, First [Middle ...] [Suffix]
        if (part.size() >= 2) {
            builder.setLength(0);
            builder.append(part.get(last)).append(COMMA);
            String sep = "";
            for (int i = 0; i < last; i++) {
                builder.append(sep).append(part.get(i));
                sep = SPACE;
            }
            builder.append(suffix);
            variants.add(builder.toString());
        }

        // Third option - Two Last, First [Middle...] [Suffix]
        if (part.size() >= 3) {
            builder.setLength(0);
            builder.append(part.get(last - 1)).append(SPACE).append(part.get(last)).append(COMMA);
            String sep = "";
            for (int i = 0; i < last - 1; i++) {
                builder.append(sep).append(part.get(i));
                sep = SPACE;
            }
            builder.append(suffix);
            variants.add(builder.toString());
        }

        ArrayList<String> variantList = new ArrayList<String>(variants);

        // If there already is a different name saved with this user, then
        // include it as the first option
        if (currentUniqueName != null && !currentUniqueName.equals("") && !variants.contains(currentUniqueName)) {
            variantList.add(0, currentUniqueName);
        }

        return variantList;
    }

    public class Variant {
        private String name;
        private int type;

        public Variant(CharSequence name, int type) {
            this.name = name.toString();
            this.type = type;
        }

        public String toString() {
            return name;
        }

        public int getType() {
            return type;
        }
    }
}
