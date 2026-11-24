package org.btwr.stratified.strata.bedrock_sheets;

public record Sheet(
        int baseY, /* Bottom Y before bumps */
        double coverageScale, /* Horizontal scale of the coverage noise; bigger = wider sheets and gaps (MiTE used 64) */
        double coverageThreshold, /* Coverage noise must be above this for the sheet to exist; lower = more coverage */
        int minThickness,
        int maxThickness,
        double thicknessPerNoise, /* How quickly the sheet thickens from its edge, per unit of noise above the threshold */
        boolean fillsCaves
) {}