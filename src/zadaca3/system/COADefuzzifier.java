package zadaca3.system;

import zadaca1.zadatak1.DomainElement;
import zadaca1.zadatak1.IDomain;
import zadaca1.zadatak3.Operations;
import zadaca3.FuzzyConclusion;

import java.util.List;

public class COADefuzzifier {
    public int defuzzificate(List<FuzzyConclusion> conclusions) throws Exception {
        FuzzyConclusion res = null;

        if (conclusions.size() > 1) {
            res = new FuzzyConclusion(Operations.binaryOperation(conclusions.get(0).getSet(), conclusions
                    .get(1)
                    .getSet(), Operations.zadehOr()));
        }

        for (int i = 1, len = conclusions.size() - 1; i < len; i++) {

            res.setSet(Operations.binaryOperation(conclusions.get(i).getSet(), res.getSet(),
                    Operations.zadehOr()));

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
