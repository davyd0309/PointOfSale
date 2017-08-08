package pl.dawydiuk.service

import pl.dawydiuk.model.BarcodeScanner
import pl.dawydiuk.model.Device
import pl.dawydiuk.model.MonitorLcd
import pl.dawydiuk.model.Printer
import pl.dawydiuk.model.enumClass.EDeviceStatus
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by Konrad on 06.08.2017.
 */
class DeviceServiceImplTest extends Specification {

    @Shared
    DeviceService deviceService

    @Shared
    Device device

    @Shared
    BarcodeScanner barcodeScanner

    @Shared
    MonitorLcd monitorLcd

    @Shared
    Printer printer

    def setupSpec() {
        device = new Device()
        barcodeScanner = new BarcodeScanner()
        monitorLcd = new MonitorLcd()
        printer = new Printer()
        deviceService = new DeviceServiceImpl()
    }

    @Unroll
    def "should device is on or off - 'onOrOffDevice'"() {
        when:
        deviceService.onOrOffDevice(device, status);
        then:
        device.equals(device)
        device.getStatus().name() == status
        where:
        device         | status
        barcodeScanner | "ON"
        barcodeScanner | "OFF"
        monitorLcd     | "ON"
        monitorLcd     | "OFF"
        printer        | "ON"
        printer        | "OFF"

    }

    @Unroll
    def "should device where status is #estatus should be #result - 'checkIfDeviceIsOn'"() {
        when:
        device.setStatus(estatus)
        then:
        deviceService.checkIfDeviceIsOn(device, estatus) == result
        where:
        device         | estatus           | result
        barcodeScanner | EDeviceStatus.ON  | true
        barcodeScanner | EDeviceStatus.OFF | false
        monitorLcd     | EDeviceStatus.ON  | true
        monitorLcd     | EDeviceStatus.OFF | false
        printer        | EDeviceStatus.ON  | true
        printer        | EDeviceStatus.OFF | false


    }
}
