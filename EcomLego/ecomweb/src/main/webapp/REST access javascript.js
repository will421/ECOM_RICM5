    $http.get('http://127.0.0.1:8080/ecomweb/resources/hello').
            success(function (data, status, headers, config) {
                $scope.items[0] = data;
                console.log(data);
            }).error(function (data, status, headers, config) {
        console.log("GET FAILED");
    });
    /*
     var req = {
     method: 'POST',
     url: 'http://127.0.0.1:5000/todos',
     headers: {'Content-Type': 'application/x-www-form-urlencoded'},
     data: {msg: "SWAGGER"}.toJSON
     }
     
     $http(req).success(function (data, status, headers, config) {
     console.log('POST OK !!');
     console.log(data);
     }).error(function (data, status, headers, config) {
     });
     
     */