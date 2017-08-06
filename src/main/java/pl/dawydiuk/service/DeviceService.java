package pl.dawydiuk.service;

import pl.dawydiuk.model.Device;
import pl.dawydiuk.model.enumClass.EDeviceStatus;

/**
 * Created by Konrad on 05.08.2017.
 */
public interface DeviceService {

    void onOrOffDevice(Device device,String status);
    boolean checkIfDeviceIsOn(Device device ,EDeviceStatus status);
}
