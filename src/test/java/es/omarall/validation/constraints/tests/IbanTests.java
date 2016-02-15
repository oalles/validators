package es.omarall.validation.constraints.tests;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import es.omarall.validation.constraints.Iban;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfiguration.class })
public class IbanTests extends AbstractJUnit4SpringContextTests {

    // private Logger LOG = LoggerFactory.getLogger(E164Tests.class);

    @Autowired
    private Validator validator;

    private String[] validIbans = { "AL47212110090000000235698741",
            "AD1200012030200359100100", "AT611904300234573201",
            "ES8023100001180000012345", "BE62510007547061" };
    private String[] twoInvalidIbans = { "AL47212110090000000235698742",
            "AD1200012030200359100100", "AT611904300234573201",
            "ES8023100001180000012346", "BE62510007547061" };

    /**
     * Class to test
     */
    public class IbanNumberWrapper {

        @Iban
        private final String iban;

        public IbanNumberWrapper(String iban) {
            this.iban = iban;
        }
    }

    @Before
    public void beforeTest() throws Exception {
    }

    @Test
    public void testAllValid() throws Exception {

        Set<ConstraintViolation<IbanNumberWrapper>> constraintViolations = new HashSet<ConstraintViolation<IbanNumberWrapper>>();
        for (String iban : Arrays.asList(validIbans)) {
            constraintViolations
                    .addAll(validator.validate(new IbanNumberWrapper(iban)));
        }

        Assert.isTrue(0 == constraintViolations.size());
    }

    @Test
    public void testTwoInvalid() throws Exception {

        Set<ConstraintViolation<IbanNumberWrapper>> constraintViolations = new HashSet<ConstraintViolation<IbanNumberWrapper>>();
        for (String iban : Arrays.asList(twoInvalidIbans)) {
            constraintViolations
                    .addAll(validator.validate(new IbanNumberWrapper(iban)));
        }

        Assert.isTrue(2 == constraintViolations.size());
    }
}
