var app = angular.module('appMain', ['ui.bootstrap', 'ngResource', 'ui.router', 'ngCart','angularFileUpload'], function ($httpProvider) {
    // Use x-www-form-urlencoded Content-Type

});
/**
 * Controller menu navigation principal (Dropdown)
 */



/*
 app.controller('DropdownCtrl', function ($scope, $log, $http) {
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

            .state('imprimer-chargervotremodele', {
                url: 'imprimer-chargervotremodele',
                templateUrl: 'partial-imprimer-chargervotremodele.html'
            })

            .state('imprimer-autres', {
                url: 'imprimer-autres',
                templateUrl: 'partial-imprimer-autres.html'
            })

            .state('panier', {
                url: 'panier',
                templateUrl: 'panier.html'
            })

            .state('inscription', {
                url: 'inscription',
                templateUrl: 'partial-inscription.html'
            })



            // ABOUT PAGE AND MULTIPLE NAMED VIEWS =================================
            .state('about', {
                // we'll get to this in a bit       
            });
});


/**
 * Controller panier d'achat
 */
app.controller("PanierCtrl", function ($scope) {
    console.log("CHECKING OUT");
    $scope.showCart = function () {
        console.log("CHECKED OUT");
    }
});

/**
 * File upload controller
 */
app.controller('FileUpload', ['$scope', 'FileUploader', function($scope, FileUploader) {
        var uploader = $scope.uploader = new FileUploader({
            url: 'upload.php'
        });

        // FILTERS

        uploader.filters.push({
            name: 'customFilter',
            fn: function(item /*{File|FileLikeObject}*/, options) {
                return this.queue.length < 10;
            }
        });

        // CALLBACKS

        uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
            console.info('onWhenAddingFileFailed', item, filter, options);
        };
        uploader.onAfterAddingFile = function(fileItem) {
            console.info('onAfterAddingFile', fileItem);
        };
        uploader.onAfterAddingAll = function(addedFileItems) {
            console.info('onAfterAddingAll', addedFileItems);
        };
        uploader.onBeforeUploadItem = function(item) {
            console.info('onBeforeUploadItem', item);
        };
        uploader.onProgressItem = function(fileItem, progress) {
            console.info('onProgressItem', fileItem, progress);
        };
        uploader.onProgressAll = function(progress) {
            console.info('onProgressAll', progress);
        };
        uploader.onSuccessItem = function(fileItem, response, status, headers) {
            console.info('onSuccessItem', fileItem, response, status, headers);
        };
        uploader.onErrorItem = function(fileItem, response, status, headers) {
            console.info('onErrorItem', fileItem, response, status, headers);
        };
        uploader.onCancelItem = function(fileItem, response, status, headers) {
            console.info('onCancelItem', fileItem, response, status, headers);
        };
        uploader.onCompleteItem = function(fileItem, response, status, headers) {
            console.info('onCompleteItem', fileItem, response, status, headers);
        };
        uploader.onCompleteAll = function() {
            console.info('onCompleteAll');
        };

        console.info('uploader', uploader);
    }]);
