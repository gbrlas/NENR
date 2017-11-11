package zadaca3.logic;

import zadaca1.zadatak1.DomainElement;
import zadaca1.zadatak1.IDomain;
import zadaca1.zadatak3.Operations;
import zadaca3.utilities.FuzzyConclusion;

import java.util.List;

/**
 * Class which represents a COA (Center Of Area) defuzzifier.
 *
 * @author goran
 * @version 1.0
 */
public class COADefuzzifier implements Defuzzifier {

    public int defuzzify(List<FuzzyConclusion> conclusions) throws Exception {
        FuzzyConclusion res = null;

        if (conclusions.size() > 1) {
            res = new FuzzyConclusion(Operations.binaryOperation(conclusions.get(0).getConclusionSet(),
                    conclusions.get(1).getConclusionSet(), Operations.zadehOr()));
        }

        for (int i = 2; i < conclusions.size() - 1; i++) {
            res.setConclusionSet(Operations.binaryOperation(conclusions.get(i).getConclusionSet(),
                    res.getConclusionSet(), Operations.zadehOr()));
        }

        IDomain domain = conclusions.get(0).getConclusionSet().getDomain();
        double a = 0;
        double b = 0;

        for (DomainElement element : domain) {
            double value = res.getConclusionSet().getValueAt(element);

            a += element.getComponentValue(0) * value;
            b += value;
        }

        return (int) (a / b);
    }
}
