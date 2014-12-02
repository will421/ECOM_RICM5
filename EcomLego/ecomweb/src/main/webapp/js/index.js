var app = angular.module('appMain', ['ui.bootstrap','ngResource'], function($httpProvider) {
  // Use x-www-form-urlencoded Content-Type
  $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';

  /**
   * The workhorse; converts an object to x-www-form-urlencoded serialization.
   * @param {Object} obj
   * @return {String}
   */ 
  var param = function(obj) {
    var query = '', name, value, fullSubName, subName, subValue, innerObj, i;

    for(name in obj) {
      value = obj[name];

      if(value instanceof Array) {
        for(i=0; i<value.length; ++i) {
          subValue = value[i];
          fullSubName = name + '[' + i + ']';
          innerObj = {};
          innerObj[fullSubName] = subValue;
          query += param(innerObj) + '&';
        }
      }
      else if(value instanceof Object) {
        for(subName in value) {
          subValue = value[subName];
          fullSubName = name + '[' + subName + ']';
          innerObj = {};
          innerObj[fullSubName] = subValue;
          query += param(innerObj) + '&';
        }
      }
      else if(value !== undefined && value !== null)
        query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&';
    }

    return query.length ? query.substr(0, query.length - 1) : query;
  };

  // Override $http service's default transformRequest
  $httpProvider.defaults.transformRequest = [function(data) {
    return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;
  }];
});
/*
Main dropdown menu controller
*/
/*
app.controller('DropdownCtrl', function ($scope, $log, LaPoste) {

    console.log("HERE");
    LaPoste.query(
        function(data){
                       console.log("HERE");
                       console.log(data);
                      }
    );
    
    $scope.items = [
        'The first choice!',
        'And another choice for you.',
        'but wait! A third!'
    ];
    
    
    $scope.status = {
        isopen: false
    };

    $scope.toggled = function (open) {
        $log.log('Dropdown is now: ', open);
    };
});
*/
app.controller('DropdownCtrl', function ($scope, $log, $http) {

    console.log("HERE");
    $scope.items = [
        'The first choice!',
        'And another choice for you.',
        'but wait! A third!'
    ];
    
    $http.get('http://127.0.0.1:5000/todos/todo1').
        success(function(data, status, headers, config) {
            $scope.items[0] = data;
        }).error(function(data, status, headers, config){
            console.log("GET FAILED");
    });
    /*
    $http.post('http://127.0.0.1:5000/todos', {'msg1':'SWAGGER'}).
        success(function(data, status, headers, config) {
           console.log('POST OK');
        }).error(function(data, status, headers, config){
            console.log("POST FAILED");
            console.log(headers);
    });
    */
    
    /*         headers: {
           'Content-Type': undefined
         },
         http://victorblog.com/2012/12/20/make-angularjs-http-service-behave-like-jquery-ajax/
         
         */
    var req = {
         method: 'POST',
         url: 'http://127.0.0.1:5000/todos',

        
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
         data: { msg : "SWAGGER"}.toJSON
    }
    
    $http(req).success(function(data, status, headers, config) {
        console.log('POST OK !!');
        console.log(data);
        }).error(function(data, status, headers, config){
    });
    
    
    $scope.status = {
        isopen: false
    };

    $scope.toggled = function (open) {
        $log.log('Dropdown is now: ', open);
    };
});


/*
Carousel controller
*/
app.controller('CarouselDemoCtrl', function ($scope) {
    $scope.myInterval = 5000;
    $scope.slides = [
        {
            image: 'img/lego_sample_project_1.jpg'
        },
        {
            image: 'img/lego_sample_project_2.jpg'
        },
        {
            image: 'img/lego_sample_project_3.jpg'
        },
        {
            image: 'img/lego_sample_project_4.jpg'
        }
    ];
});





