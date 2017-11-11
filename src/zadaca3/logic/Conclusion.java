package zadaca3.logic;

/**
 * Interface used for making conclusions.
 *
 * @author goran
 * @version 1.0
 */
public interface Conclusion {
    /**
     * @param left       Distance from the left shore.
     * @param right      Distance from the right shore.
     * @param leftAngle  45 degree angle distance from the left shore.
     * @param rightAngle 45 degree angle distance from the right shore.
     * @param speed      Boat speed.
     * @param direction  Boat direction.
     * @return Integer value representing the defuzzification fo the conlcusion.
     * @throws Exception
     */
    int conclude(int left, int right, int leftAngle, int rightAngle, int speed, int direction) throws Exception;
}
