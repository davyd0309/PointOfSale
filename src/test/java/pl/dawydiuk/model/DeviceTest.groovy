package pl.dawydiuk.model

import pl.dawydiuk.dto.ProductDto
import pl.dawydiuk.model.enumClass.EDeviceStatus
import spock.lang.Specification
import spock.lang.Unroll

import javax.validation.constraints.Null

/**
 * Created by Konrad on 06.08.2017.
 */
class DeviceTest extends Specification {


    def "SetProductDto"() {
        given:
        Device device = new Device();
        ProductDto productDto = new ProductDto("Prod_77758", "Książka", 100);
        when:
        device.setProductDto(productDto);
        then:
        device.getProductDto().equals(productDto);

    }

    @Unroll()
    def "SetStatus"() {
        given:
        Device device = new Device();
        when:
        device.setStatus(status);
        then:
        device.getStatus().equals(status)
        where:
        status << [EDeviceStatus.ON, EDeviceStatus.OFF]


    }
}
