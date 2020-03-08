package expressions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlusExpression extends AbstractExpression {
    private AbstractExpression lhs;
    private AbstractExpression rhs;

    @Override
    public int interpret() {
        return lhs.interpret() + rhs.interpret();
    }

    @Override
    public String toString() {
        return String.format("(%s + %s)", lhs.toString(), rhs.toString());
    }
}
