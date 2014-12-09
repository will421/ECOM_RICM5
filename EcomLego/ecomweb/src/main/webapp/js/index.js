var app = angular.module('appMain', ['ui.bootstrap', 'ngResource', 'ui.router', 'ngCart'], function ($httpProvider) {
    // Use x-www-form-urlencoded Content-Type
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';

    /**
     * The workhorse; converts an object to x-www-form-urlencoded serialization.
     * @param {Object} obj
     * @return {String}
     */
    var param = function (obj) {
        var query = '', name, value, fullSubName, subName, subValue, innerObj, i;

        for (name in obj) {
            value = obj[name];

            if (value instanceof Array) {
                for (i = 0; i < value.length; ++i) {
                    subValue = value[i];
                    fullSubName = name + '[' + i + ']';
                    innerObj = {};
                    innerObj[fullSubName] = subValue;
                    query += param(innerObj) + '&';
                }
            }
            else if (value instanceof Object) {
                for (subName in value) {
                    subValue = value[subName];
                    fullSubName = name + '[' + subName + ']';
                    innerObj = {};
                    innerObj[fullSubName] = subValue;
                    query += param(innerObj) + '&';
                }
            }
            else if (value !== undefined && value !== null)
                query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&';
        }

        return query.length ? query.substr(0, query.length - 1) : query;
    };

    // Override $http service's default transformRequest
    $httpProvider.defaults.transformRequest = [function (data) {
            return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;
        }];
});
/**
 * Controller menu navigation principal (Dropdown)
 */
app.controller('DropdownCtrl', function ($scope, $log, $http) {
    $scope.items = [
        'The first choice!',
        'And another choice for you.',
        'but wait! A third!'
    ];
    /*
     $http.get('http://127.0.0.1:5000/todos/todo1').
     success(function (data, status, headers, config) {
     $scope.items[0] = data;
     }).error(function (data, status, headers, config) {
     console.log("GET FAILED");
     });
     
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
    $scope.status = {
        isopen: false
    };

    $scope.toggled = function (open) {
        $log.log('Dropdown is now: ', open);
    };
});


/**
 * Controller carousel
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

/**
 * Configuration Angular UI Router
 */
app.config(function ($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/home');

    $stateProvider

            // HOME STATES AND NESTED VIEWS ========================================
            .state('#', {
                url: '/home',
                templateUrl: 'partial-home.html'
            })

            .state('produits', {
                url: 'produits',
                templateUrl: 'partial-produits.html'
            })

            .state('imprimer-commanderundevis', {
                url: 'imprimer-commanderundevis',
                templateUrl: 'partial-imprimer-commanderundevis.html'
            })

            .state('imprimer-creerunmodele', {
                url: 'imprimer-creerunmodele',
                templateUrl: 'partial-imprimer-creerunmodele.html'
            })

            .state('imprimer-exportervotremodele', {
                url: 'imprimer-exportervotremodele',
                templateUrl: 'partial-imprimer-exportervotremodele.html'
            })

            .state('imprimer-autres', {
                url: 'imprimer-autres',
                templateUrl: 'partial-imprimer-autres.html'
            })

            .state('panier', {
                url: 'panier',
                templateUrl: 'panier.html'
            })



            // ABOUT PAGE AND MULTIPLE NAMED VIEWS =================================
            .state('about', {
                // we'll get to this in a bit       
            });
});


/**
 * Controller panier d'achat
 */
app.controller("PanierCtrl", function($scope){
    console.log("CHECKING OUT");
    $scope.showCart = function(){
        console.log("CHECKED OUT");
    }
});