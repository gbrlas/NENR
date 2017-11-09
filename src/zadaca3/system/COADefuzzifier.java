package zadaca3.system;

import zadaca1.zadatak1.DomainElement;
import zadaca1.zadatak1.IDomain;
import zadaca1.zadatak3.Operations;
import zadaca3.utilities.FuzzyConclusion;

import java.util.List;

public class COADefuzzifier implements Defuzzifier {
    
    public int defuzzify(List<FuzzyConclusion> conclusions) throws Exception {
        FuzzyConclusion res = null;

        if (conclusions.size() == 1) {
            res = conclusions.get(0);
        } else if (conclusions.size() > 1) {
            for (int i = 1; i < conclusions.size() - 1; i++) {
                res.setSet(Operations.binaryOperation(conclusions.get(i).getSet(), res.getSet(),
                        Operations.zadehOr()));
            }
        }

        IDomain domain = conclusions.get(0).getSet().getDomain();
        double a = 0;
        double b = 0;

        for (DomainElement element : domain) {
            double value = res.getSet().getValueAt(element);

            a += element.getComponentValue(0) * value;
            b += value;
        }

        return (int) (a / b);
    }
}
