package fr.parisnanterre.miage.poa.pbt.impl;

    import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by vijay on 01-11-18.
 */
public class PostalParcelTest {


    @Test
    public void deliveryCostsShouldBeMaxWhenWeightIsLargerThan20() {
        PostalParcel postalParcel = new PostalParcel("uuid",23);
        assertThat(postalParcel.deliveryCosts(), equalTo(PostalParcel.MAX_DELIVERY_COSTS));
    }

    @Test
    public void deliveryCostsShouldBeMinWhenWeightIsLessThanOrEqualTo20() {
        PostalParcel postalParcel = new PostalParcel("uuid", 19);
        assertThat(postalParcel.deliveryCosts(), equalTo(PostalParcel.MIN_DELIVERY_COSTS));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenWeightIsBelowOne() {
        PostalParcel postalParcel = new PostalParcel("uuid", -100);
    }
}
