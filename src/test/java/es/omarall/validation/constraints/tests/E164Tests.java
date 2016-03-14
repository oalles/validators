package es.omarall.validation.constraints.tests;

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

import es.omarall.validation.constraints.E164;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfiguration.class })
public class E164Tests extends AbstractJUnit4SpringContextTests {

    // private Logger LOG = LoggerFactory.getLogger(E164Tests.class);

    @Autowired
    private Validator validator;

    /**
     * Class to test
     */
    public class PhoneNumberWrapper {

        @E164
        private final String phoneNumber;

        public PhoneNumberWrapper(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
    }

    @Before
    public void beforeTest() throws Exception {
    }

    @Test
    public void testNullNumberIsValid() throws Exception {

        PhoneNumberWrapper w = new PhoneNumberWrapper(null);

        Set<ConstraintViolation<PhoneNumberWrapper>> constraintViolations = validator
                .validate(w);
        Assert.isTrue(0 == constraintViolations.size());
    }

    @Test
    public void testE164IsValid() throws Exception {
        PhoneNumberWrapper w = new PhoneNumberWrapper("+34600000000");
        Set<ConstraintViolation<PhoneNumberWrapper>> constraintViolations = validator
                .validate(w);
        Assert.isTrue(0 == constraintViolations.size());
    }

    @Test
    public void testNoPlusSignIsInvalid() throws Exception {

        PhoneNumberWrapper w = new PhoneNumberWrapper("34600000000");
        Set<ConstraintViolation<PhoneNumberWrapper>> constraintViolations = validator
                .validate(w);
        Assert.isTrue(1 == constraintViolations.size());
    }

    @Test
    public void testNoPlusSignedNumberBut00IsInvalid() throws Exception {
        PhoneNumberWrapper w = new PhoneNumberWrapper("0034600000000");
        Set<ConstraintViolation<PhoneNumberWrapper>> constraintViolations = validator
                .validate(w);
        Assert.isTrue(1 == constraintViolations.size());
    }

    @Test
    public void testNumberWithPlusSignIsInvalid() throws Exception {
        // InternationFormat, no E164
        PhoneNumberWrapper w = new PhoneNumberWrapper("+34 600 00 00 00");
        Set<ConstraintViolation<PhoneNumberWrapper>> constraintViolations = validator
                .validate(w);
        Assert.isTrue(1 == constraintViolations.size());
    }

}
