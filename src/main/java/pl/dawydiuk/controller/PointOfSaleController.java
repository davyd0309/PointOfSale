package pl.dawydiuk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dawydiuk.dto.ProductDto;
import pl.dawydiuk.model.*;
import pl.dawydiuk.model.enumClass.EDeviceStatus;
import pl.dawydiuk.service.DeviceService;
import pl.dawydiuk.service.ProductService;

import java.util.Collection;
import java.util.List;

/**
 * Created by kdawydiuk on 2017-08-04.
 */

@RestController
@RequestMapping("/start")
public class PointOfSaleController {


    private ProductService productService;
    private DeviceService deviceService;

    private BarcodeScanner barcodeScanner;
    private MonitorLcd monitorLcd;
    private Printer printer;


    @Autowired
    public PointOfSaleController(ProductService productService, DeviceService deviceService) {
        this.productService = productService;
        this.deviceService = deviceService;
        barcodeScanner = new BarcodeScanner();
        monitorLcd = new MonitorLcd();
        printer = new Printer();

    }

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Product>> getAllAvailabeProducts() {
        Collection<Product> allProducts = productService.findAll();
        HttpStatus httpStatus = allProducts != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(allProducts, httpStatus);
    }

    @PostMapping(value = "barcodescanner")
    public ResponseEntity<Device> onOrOffBarcodeScanner(@RequestParam String status) {
        deviceService.onOrOffDevice(barcodeScanner, status);
        return new ResponseEntity<Device>(HttpStatus.OK);
    }

    @PostMapping(value = "monitorlcd")
    public ResponseEntity<Device> onOrOffMonitorLcd(@RequestParam String status) {
        deviceService.onOrOffDevice(monitorLcd, status);
        return new ResponseEntity<Device>(HttpStatus.OK);
    }

    @PostMapping(value = "printer")
    public ResponseEntity<Device> onOrOffPrinter(@RequestParam String status) {
        deviceService.onOrOffDevice(printer, status);
        return new ResponseEntity<Device>(HttpStatus.OK);
    }


    @GetMapping(value = "scan/{barcode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> scanProduct(@PathVariable String barcode) {

        if (!deviceService.checkIfDeviceIsOn(barcodeScanner, EDeviceStatus.ON) ||
                !deviceService.checkIfDeviceIsOn(monitorLcd, EDeviceStatus.ON) ||
                !deviceService.checkIfDeviceIsOn(printer, EDeviceStatus.ON)) {
            pl.dawydiuk.utils.Error error = new pl.dawydiuk.utils.Error(100, "Przed rozpoczęciem skanowania proszę włączyć wszystkie urządzenia.");
            return new ResponseEntity<pl.dawydiuk.utils.Error>(error, HttpStatus.CONFLICT);
        } else {
            ProductDto productByBarcode = productService.findProductByBarcode(barcode);
            productService.addProductToShoppingCart(productByBarcode);
            HttpStatus httpStatus = productByBarcode != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(productByBarcode, httpStatus);
       }
    }

    @GetMapping(value = "print", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDto>> printAllScannedProduct() {
        return new ResponseEntity<List<ProductDto>>(productService.getAddedProducts(), HttpStatus.OK);
    }

    @PostMapping(value = "clear")
    public ResponseEntity<List<ProductDto>> clearAllScannedProduct() {
        productService.clearAllScannedProduct();
        return new ResponseEntity<List<ProductDto>>(HttpStatus.OK);
    }


}
