package com.sebi.resolver;

import com.sebi.componentbeans.VehicleComponentBean;
import com.sebi.utility.VehicleScanUtil;

import java.io.File;

public class VehicleResolver {

    public VehicleComponentBean resolve(String path) {
        VehicleComponentBean componentBean = new VehicleComponentBean();

        File file;

        try {
            file = new File(path);

            if (file.exists()) {
                componentBean.setRegNumbers(VehicleScanUtil.getRegistrationNumbers(path));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return componentBean;
    }

}
