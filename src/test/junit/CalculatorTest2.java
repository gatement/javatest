import static org.junit.Assert.assertEquals;
import org.junit.Test;
import lgh.Calculator2;

public class CalculatorTest2
{
  @Test
  public void evaluatesExpression()
  {
    Calculator2 calculator = new Calculator2();
    int sum = calculator.evaluate("1+2+3");
    assertEquals(-6, sum);
  }
}
