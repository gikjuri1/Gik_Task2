import org.junit.Assert;
import org.junit.jupiter.api.Test;
import task2.Fraction;
import task2.Fractionable;

public class UnitTest {
    @Test
    void testSetNum() {
        Fraction fr=new Fraction(15,3);
        fr.doubleValue();
        fr.setNum(6);
        Assert.assertEquals(fr.cache,0.0,0.1);
    }
}
