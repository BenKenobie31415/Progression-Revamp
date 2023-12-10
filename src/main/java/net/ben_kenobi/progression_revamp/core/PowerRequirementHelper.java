package net.ben_kenobi.progression_revamp.core;

import java.util.HashMap;
import java.util.Map;

public abstract class PowerRequirementHelper {
    public enum Patterns {
        DAMAGE,
        KNOCKBACK,
        THORNS,
        AQUA_AFFINITY,
        RESPIRATION,
        SWEEPING,
        PROTECTION,
        OTHER_PROTECTION,
        UNBREAKING,
        FIRE_ASPECT,
        INFINITY,
        DEPTH_STRIDER
    }

    public static final Map<Patterns, Map<Integer, Integer>> patternMinMap = new HashMap<>();
    public static final Map<Patterns, Map<Integer, Integer>> patternMaxMap = new HashMap<>();

    public static void init_data() {
        patternMinMap.put(Patterns.DAMAGE, Map.of(
                1, 8,
                2, 22));
        patternMaxMap.put(Patterns.DAMAGE, Map.of(
                1, 26,
                2, 30));

        patternMinMap.put(Patterns.KNOCKBACK, Map.of(
                1, 5,
                2, 11));
        patternMaxMap.put(Patterns.KNOCKBACK, Map.of(
                1, 13,
                2, 16));

        patternMinMap.put(Patterns.THORNS, Map.of(
                1, 1,
                2, 21));
        patternMaxMap.put(Patterns.THORNS, Map.of(
                1, 30,
                2, 30));

        patternMinMap.put(Patterns.AQUA_AFFINITY, Map.of(
                1, 15));
        patternMaxMap.put(Patterns.AQUA_AFFINITY, Map.of(
                1, 30));

        patternMinMap.put(Patterns.RESPIRATION, Map.of(
                1, 23));
        patternMaxMap.put(Patterns.RESPIRATION, Map.of(
                1, 30));

        patternMinMap.put(Patterns.SWEEPING, Map.of(
                1, 1,
                2, 15));
        patternMaxMap.put(Patterns.SWEEPING, Map.of(
                1, 19,
                2, 30));

        patternMinMap.put(Patterns.PROTECTION, Map.of(
                1, 20));
        patternMaxMap.put(Patterns.PROTECTION, Map.of(
                1, 30));

        patternMinMap.put(Patterns.OTHER_PROTECTION, Map.of(
                1, 1,
                2, 22));
        patternMaxMap.put(Patterns.OTHER_PROTECTION, Map.of(
                1, 22,
                2, 30));

        patternMinMap.put(Patterns.UNBREAKING, Map.of(
                1, 13));
        patternMaxMap.put(Patterns.UNBREAKING, Map.of(
                1, 30));

        patternMinMap.put(Patterns.FIRE_ASPECT, Map.of(
                1, 18));
        patternMaxMap.put(Patterns.FIRE_ASPECT, Map.of(
                1, 26));

        patternMinMap.put(Patterns.INFINITY, Map.of(
                1, 29));
        patternMaxMap.put(Patterns.INFINITY, Map.of(
                1, 31));

        patternMinMap.put(Patterns.DEPTH_STRIDER, Map.of(
                1, 1,
                2, 15));
        patternMaxMap.put(Patterns.DEPTH_STRIDER, Map.of(
                1, 20,
                2, 30));
    }

    public static int getMinPower(Patterns pattern, int level) {
        Integer result = patternMinMap.get(pattern).get(level);
        if (result != null) {
            return result;
        }
        return 50;
    }
    public static int getMaxPower(Patterns pattern, int level) {
        Integer result = patternMaxMap.get(pattern).get(level);
        if (result != null) {
            return result;
        }
        return 50;
    }
}
