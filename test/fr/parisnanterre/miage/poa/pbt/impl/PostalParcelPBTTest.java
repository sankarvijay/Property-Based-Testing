package fr.parisnanterre.miage.poa.pbt.impl;


import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import jdk.nashorn.internal.objects.annotations.Property;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.sun.javafx.fxml.expression.Expression.lessThanOrEqualTo;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.CombinableMatcher.both;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;
import static sun.jvm.hotspot.utilities.AddressOps.greaterThan;
import static sun.nio.cs.Surrogate.is;


/**
 * Created by vijay on 01-11-18.
 */
@RunWith(JUnitQuickcheck.class)
public class PostalParcelPBTTest {

    @Test
    public void delivery_costs_should_be_bax_when_weight_is_greater_than_20_edge() {
        PostalParcel postalParcel = new PostalParcel("UUID", 21);
        assertThat(postalParcel.deliveryCosts(), equalTo(PostalParcel.MAX_DELIVERY_COSTS));
        PostalParcel postalParcel1 = new PostalParcel("UUID", Integer.MAX_VALUE);
        assertThat(postalParcel1.deliveryCosts(), equalTo(PostalParcel.MAX_DELIVERY_COSTS));
    }

    @Property
    public void delivery_costs_should_be_bax_when_weight_is_greater_than_20(String uuid, @InRange(minInt = 21) int weight) {
        assumeThat(weight, greaterThan(20));
        PostalParcel postalParcel = new PostalParcel(uuid, weight);
        assertThat(postalParcel.deliveryCosts(), equalTo(PostalParcel.MAX_DELIVERY_COSTS));
    }

    @Test
    public void delivery_costs_should_be_min_when_weight_is_less_than_or_equal_to_20_edge() {
        PostalParcel postalParcel = new PostalParcel("UUID", 1);
        assertThat(postalParcel.deliveryCosts(), equalTo(PostalParcel.MIN_DELIVERY_COSTS));
        PostalParcel postalParcel1 = new PostalParcel("UUID", 20);
        assertThat(postalParcel1.deliveryCosts(), equalTo(PostalParcel.MIN_DELIVERY_COSTS));
    }

    @Property(trials = 25)
    public void delivery_costs_should_be_min_when_weight_is_less_than_or_equal_to_20(String uuid, @InRange(minInt = 1, maxInt = 20) int weight) {
        assumeThat(weight, is(both(greaterThan(0)).and(lessThanOrEqualTo(20))));

        PostalParcel postalParcel = new PostalParcel(uuid, weight);
        assertThat(postalParcel.deliveryCosts(), equalTo(PostalParcel.MIN_DELIVERY_COSTS));
    }

    @Property
    public void should_throw_illegal_argument_exception_when_weight_is_below_one_edge() {

        IllegalArgumentException illegalArgumentException = null;

        try {
            PostalParcel postalParcel = new PostalParcel("UUID", 0);
        } catch (IllegalArgumentException e) {
            illegalArgumentException = e;
        }
        assumeThat(illegalArgumentException, notNullValue());
    }

    @Property
    public void should_throw_illegal_argument_exception_when_weight_is_below_one(String uuid, @InRange(maxInt = 0) int weight) {
        assumeThat(weight, lessThanOrEqualTo(0));

        IllegalArgumentException illegalArgumentException = null;

        try {
            PostalParcel postalParcel = new PostalParcel(uuid, weight);
        } catch (IllegalArgumentException e) {
            illegalArgumentException = e;
        }
        assumeThat(illegalArgumentException, notNullValue());
    }

}
