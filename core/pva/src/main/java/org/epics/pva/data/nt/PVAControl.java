/*
 * Copyright (C) 2023 European Spallation Source ERIC.
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 */
package org.epics.pva.data.nt;

import org.epics.pva.data.PVADouble;
import org.epics.pva.data.PVAStructure;

/**
 * Normative control type
 * 
 * control_t :=
 * <ul>
 * <li>structure
 * <ul>
 * <li>double limitLow
 * <li>double limitHigh
 * <li>double minStep
 * 
 */
public class PVAControl extends PVAStructure {
    public static final String CONTROL_NAME_STRING = "control";
    public static final String CONTROL_T = "control_t";

    /**
     * Setting all parameters
     * 
     * @param limitLow
     * @param limitHigh
     * @param minStep
     */
    public PVAControl(double limitLow, double limitHigh, double minStep) {
        this(new PVADouble("limitLow", limitLow),
                new PVADouble("limitHigh", limitHigh),
                new PVADouble("minStep", minStep));
    }

    /**
     * Setting all parameters
     * 
     * @param limitLow
     * @param limitHigh
     * @param minStep
     */
    public PVAControl(PVADouble limitLow, PVADouble limitHigh, PVADouble minStep) {
        super(CONTROL_NAME_STRING, CONTROL_T,
                limitLow,
                limitHigh,
                minStep);
    }

    /**
     * Conversion from structure to PVAControl
     * 
     * @param structure Potential "control_t" structure
     * @return PVAControl or <code>null</code>
     */
    public static PVAControl fromStructure(PVAStructure structure) {
        if (structure != null && structure.getStructureName().equals(CONTROL_T)) {
            final PVADouble limitLow = structure.get("limitLow");
            final PVADouble limitHigh = structure.get("limitHigh");
            final PVADouble minStep = structure.get("minStep");
            return new PVAControl(limitLow, limitHigh, minStep);
        }
        return null;
    }

    /**
     * Get Control from a PVAStructure
     * 
     * @param structure Structure containing Control
     * @return PVAControl or <code>null</code>
     */
    public static PVAControl getControl(PVAStructure structure) {
        var controlStructure = structure.get(CONTROL_NAME_STRING);
        if (controlStructure != null) {
            return fromStructure((PVAStructure) controlStructure);
        }
        return null;
    }

}
