/**
 * Created by kdawydiuk on 2017-08-04.
 */
app.controller('posController', function ($scope, $http) {


    //load all available products
    $scope.allProducts = [];
    $http({
        method: 'GET',
        url: 'start/all'
    }).then(function (result) {
        $scope.allProducts = result.data;
    }, function (error) {

    });


    $scope.monitor = "";
    $scope.shoppingCartSum = 0;
    $scope.scanBarcode = function () {
        $http({
            url: 'start/scan/' + $('#inBarcode').val(),
            method: "GET"
        })
            .then(function (result) {
                $scope.monitor = "Dodano : " + result.data.barCode + " -- " + result.data.name + " -- " + result.data.price;
                $scope.shoppingCartSum++;
            },
            function (result) {
                if (result.data.code == 100) {
                    alert(result.data.message);
                } else {
                    $scope.monitor = "Podanego kodu nie znaleziono w bazie.";

                }

            });
    };


    $('#inScanner').change(function () {
        var isScaner = $('#inScanner').is(":checked");
        if (isScaner) {
            $http({
                url: 'start/barcodescanner',
                method: 'POST',
                params: {status: "ON"}
            }).then(function (result) {
                console.log("'start/barcodescanner' OK ON")
            }, function (result) {
                console.log("'start/barcodescanner' ERROR ON")
            });
        } else {
            $http({
                url: 'start/barcodescanner',
                method: 'POST',
                params: {status: 'OFF'}
            }).then(function (result) {
                console.log("'start/barcodescanner' OK OFF")
            }, function (result) {
                console.log("'start/barcodescanner' ERROR OFF")
            });
        }

    });

    $('#inMonitor').change(function () {
        var isMonitor = $('#inMonitor').is(":checked");
        if (isMonitor) {
            $http({
                url: 'start/monitorlcd',
                method: 'POST',
                params: {status: "ON"}
            }).then(function (result) {
                console.log("'start/monitorlcd' OK ON")
            }, function (result) {
                console.log("'start/monitorlcd' ERROR ON")
            });
        } else {
            $http({
                url: 'start/monitorlcd',
                method: 'POST',
                params: {status: 'OFF'}
            }).then(function (result) {
                console.log("'start/monitorlcd' OK OFF")
            }, function (result) {
                console.log("'start/monitorlcd' ERROR OFF")
            });
        }

    });


    $('#inPrinter').change(function () {
        var isPrinter = $('#inPrinter').is(":checked");
        if (isPrinter) {
            $http({
                url: 'start/printer',
                method: 'POST',
                params: {status: "ON"}
            }).then(function (result) {
                console.log("'start/printer' OK ON")
            }, function (result) {
                console.log("'start/printer' ERROR ON")
            });
        } else {
            $http({
                url: 'start/printer',
                method: 'POST',
                params: {status: 'OFF'}
            }).then(function (result) {
                console.log("'start/printer' OK OFF")
            }, function (result) {
                console.log("'start/printer' ERROR OFF")
            });
        }

    });


    $scope.shoppingCart = [];
    $scope.printShoppingCart = function () {
        $http({
            url: 'start/print',
            method: "GET"
        })
            .then(function (result) {
                $scope.shoppingCart = result.data;
                var text = [];
                var sum = 0;
                $scope.shoppingCart.forEach(function (value, key) {
                    text.push(key + 1 + ': ' + value.barcode + " - " + value.name + " - " + value.price + "\n");
                    sum = sum + value.price;
                });
                alert("Do drukarki wysłano listę zakupów\n" + text +"\n" +"Sum for payment: " + sum );
                console.log(text);
            },
            function (result) {

            });
    }

});