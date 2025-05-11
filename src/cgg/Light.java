// File: src/cgg/Light.java
package cgg;

import tools.Color;
import tools.Vec3;

/**
 * Interface für Lichtquellen zur Unterstützung der Phong-Methode.
 */
public interface Light {
    /**
     * Liefert die Intensität oder Farbe der Lichtquelle.
     */
    Color intensity();

    /**
     * Liefert die normierte Richtung vom Treffpunkt zur Lichtquelle.
     * Für Richtungslichtquellen unabhängig vom Treffpunkt.
     */
    Vec3 direction(Hit hit);

    /**
     * Liefert die Entfernung vom Treffpunkt bis zur Lichtquelle.
     * Für Richtungslichtquellen Double.POSITIVE_INFINITY.
     */
    double distance(Hit hit);
}
