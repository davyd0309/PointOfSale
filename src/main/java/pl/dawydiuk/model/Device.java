package pl.dawydiuk.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.dawydiuk.dto.ProductDto;
import pl.dawydiuk.model.enumClass.EDeviceStatus;

/**
 * Created by Konrad on 05.08.2017.
 */
@EqualsAndHashCode
@ToString
public class Device {


    @Setter
    @Getter
    protected ProductDto productDto;

    @Setter
    @Getter
    protected EDeviceStatus status = EDeviceStatus.OFF;

}
