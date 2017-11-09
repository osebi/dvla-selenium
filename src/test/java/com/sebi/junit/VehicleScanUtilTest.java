package com.sebi.junit;

import com.sebi.componentbeans.VehicleComponentBean;
import com.sebi.resolver.VehicleResolver;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class VehicleScanUtilTest {

    private VehicleComponentBean componentBean;
    private VehicleResolver vehicleResolver;

    private final static String FILE_PATH = "register1.csv";
    private final static String REG_NO = "GJ63 WGG";

    @Before
    public void setup() {
        componentBean = new VehicleComponentBean();
        vehicleResolver = new VehicleResolver();
    }

    @Test
    public void shouldReturnRegistrationNumberSize() {
        componentBean = vehicleResolver.resolve(FILE_PATH);
        assertThat(componentBean.getRegNumbers().size(), is(equalTo(3)));
    }

    @Test
    public void shouldReturnOneRegistrationNumber() {
        componentBean = vehicleResolver.resolve(FILE_PATH);
        assertThat(componentBean.getRegNumbers().get(0), is(equalTo(REG_NO)));
    }



}
