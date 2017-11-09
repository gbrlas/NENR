package zadaca3.system;

import zadaca3.utilities.FuzzyConclusion;

import java.util.List;

public interface Defuzzifier {
    int defuzzify(List<FuzzyConclusion> conclusions) throws Exception;
}
