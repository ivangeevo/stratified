package org.btwr.stratified.strata;

import net.minecraft.util.math.MathHelper;
import org.btwr.stratified.strata.bedrock_sheets.BedrockSheets;

/**
 * Y bands for each strata, in equal 16-block steps from BTW CE's strata 2 start
 * (15 blocks below sea level, Y 48): strata 2 at 48 -> 33, strata 3 at 32 -> 17,
 * and strata 4 from the modern diamond cap (Y 16) down.
 * <p>Strata 3 and 4 are both deepslate. In normal worlds that's all strata 4 is; in the
 * "Bedrock Sheets" world type it's also where the sheets generate ({@link BedrockSheets}).
 * <p>Each column gets a deterministic 0/1 jitter on the block bands, like BTW's {@code rand.nextInt(2)}.
 */
public final class StrataLayout {
    public static final int STRATA_1 = 0;
    public static final int STRATA_2 = 1;
    public static final int STRATA_3 = 2;

    /** Highest Y (before jitter) that belongs to strata 2. */
    public static final int STRATA_2_TOP = 48;
    /** Highest Y (before jitter) that belongs to strata 3. */
    public static final int STRATA_3_TOP = 32;
    /** Highest Y that the optional bedrock sheets can reach. Strata 4 has no blocks of its own. */
    public static final int STRATA_4_TOP = 16;

    /** Highest Y any strata other than 1 can reach, jitter included. */
    public static final int MAX_STRATA_Y = STRATA_2_TOP + 1;

    private StrataLayout() {}

    public static int getStrata(int x, int y, int z) {
        if (y > MAX_STRATA_Y) {
            return STRATA_1;
        }

        int jitter = MathHelper.idealHash(x * 31 + z * 0x9E3779B9);

        if (y <= STRATA_3_TOP + ((jitter >>> 8) & 1)) {
            return STRATA_3;
        }

        if (y <= STRATA_2_TOP + ((jitter >>> 16) & 1)){
            return STRATA_2;
        }

        return STRATA_1;
    }
}