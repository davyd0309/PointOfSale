package pl.dawydiuk.service;

import org.springframework.stereotype.Service;
import pl.dawydiuk.dto.ProductDto;
import pl.dawydiuk.model.BarcodeScanner;
import pl.dawydiuk.model.Device;
import pl.dawydiuk.model.enumClass.EDeviceStatus;

/**
 * Created by Konrad on 05.08.2017.
 */
@Service
public class DeviceServiceImpl implements DeviceService {



    @Override
    public void onOrOffDevice(Device device,String status) {

       if(status.equals("ON"))
           device.setStatus(EDeviceStatus.ON);
       else if (status.equals("OFF"))
           device.setStatus(EDeviceStatus.OFF);
    }

    @Override
    public boolean checkIfDeviceIsOn(Device device,EDeviceStatus status) {
        return device.getStatus().equals(EDeviceStatus.ON);
    }
}
