package zadaca3.logic;

import zadaca3.utilities.FuzzyConclusion;

import java.util.List;

public interface Defuzzifier {
    int defuzzify(List<FuzzyConclusion> conclusions) throws Exception;
}
