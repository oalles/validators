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

import es.omarall.validation.constraints.Cif;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfiguration.class })
public class CIFTests extends AbstractJUnit4SpringContextTests {

    @Autowired
    private Validator validator;

    private String[] validCIFs = { "B63272603", "B86672318", "B84675529",
            "B85759330" };
    private String[] twoInvalidCifs = { "B63272604", "C86672319", "B84675529",
            "B85759330" };

    /**
     * Class to test
     */
    public class CIFWrapper {

        @Cif
        private final String cif;

        public CIFWrapper(String cif) {
            this.cif = cif;
        }
    }

    @Before
    public void beforeTest() throws Exception {
    }

    @Test
    public void testAllValid() throws Exception {

        Set<ConstraintViolation<CIFWrapper>> constraintViolations = new HashSet<ConstraintViolation<CIFWrapper>>();
        for (String cif : Arrays.asList(validCIFs)) {
            constraintViolations
                    .addAll(validator.validate(new CIFWrapper(cif)));
        }

        Assert.isTrue(0 == constraintViolations.size());
    }

    @Test
    public void testTwoInvalid() throws Exception {

        Set<ConstraintViolation<CIFWrapper>> constraintViolations = new HashSet<ConstraintViolation<CIFWrapper>>();
        for (String cif : Arrays.asList(twoInvalidCifs)) {
            constraintViolations
                    .addAll(validator.validate(new CIFWrapper(cif)));
        }

        Assert.isTrue(2 == constraintViolations.size());
    }
}
