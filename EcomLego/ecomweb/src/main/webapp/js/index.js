var app = angular.module('appMain', ['ui.bootstrap', 'ngResource', 'ui.router', 'ngCart', 'angularFileUpload', 'restservice'], function ($httpProvider) {
    // Use x-www-form-urlencoded Content-Type

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
            image: 'img/lego_sample_project_1.jpg',
            text: 'Creation de George44'

        },
        {
            image: 'img/lego_sample_project_2.jpg',
            text: 'Creation de Rad84'


        },
        {
            image: 'img/lego_sample_project_3.jpg',
            text: 'Creation de Leou41'

        },
        {
            image: 'img/lego_sample_project_4.jpg',
            text: 'Creation de Alice'

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
            .state('contactez-nous', {
                url: 'contactez-nous',
                templateUrl: 'partial-contactez-nous.html'
            })



            // ABOUT PAGE AND MULTIPLE NAMED VIEWS =================================
            .state('about', {
                // we'll get to this in a bit       
            });
});


/**
 * Controller panier d'achat
 * $scope is a MUST for REST service to work
 */
app.controller("PanierCtrl", ['$scope', 'Rest', function ($scope, Rest) {
        console.log("CHECKING OUT");
        $scope.showCart = function () {
            console.log("CHECKED OUT");
        }
    }]);

/**
 * File upload controller
 */
app.controller('FileUpload', ['$scope', 'FileUploader', function ($scope, FileUploader) {
        var uploader = $scope.uploader = new FileUploader({
            url: 'upload.php'
        });

        // FILTERS

        uploader.filters.push({
            name: 'customFilter',
            fn: function (item /*{File|FileLikeObject}*/, options) {
                return this.queue.length < 10;
            }
        });

        // CALLBACKS

        uploader.onWhenAddingFileFailed = function (item /*{File|FileLikeObject}*/, filter, options) {
            console.info('onWhenAddingFileFailed', item, filter, options);
        };
        uploader.onAfterAddingFile = function (fileItem) {
            console.info('onAfterAddingFile', fileItem);
        };
        uploader.onAfterAddingAll = function (addedFileItems) {
            console.info('onAfterAddingAll', addedFileItems);
        };
        uploader.onBeforeUploadItem = function (item) {
            console.info('onBeforeUploadItem', item);
        };
        uploader.onProgressItem = function (fileItem, progress) {
            console.info('onProgressItem', fileItem, progress);
        };
        uploader.onProgressAll = function (progress) {
            console.info('onProgressAll', progress);
        };
        uploader.onSuccessItem = function (fileItem, response, status, headers) {
            console.info('onSuccessItem', fileItem, response, status, headers);
        };
        uploader.onErrorItem = function (fileItem, response, status, headers) {
            console.info('onErrorItem', fileItem, response, status, headers);
        };
        uploader.onCancelItem = function (fileItem, response, status, headers) {
            console.info('onCancelItem', fileItem, response, status, headers);
        };
        uploader.onCompleteItem = function (fileItem, response, status, headers) {
            console.info('onCompleteItem', fileItem, response, status, headers);
        };
        uploader.onCompleteAll = function () {
            console.info('onCompleteAll');
        };

        console.info('uploader', uploader);
    }]);


/**
 * Controller page des produits
 */
app.controller("ProduitsCtrl", ['$scope', 'Rest', function ($scope, Rest) {

        $scope.resultProduits = [
            {'Produit1': 'shitlego'},
            {'Produit2': 'shitlego2'},
            {'Produit3': 'shitlego3'},
            {'Produit4': 'shitlego4'},
            {'Produit5': 'shitlego5'},
            {'Produit6': 'shitlego6'}
        ]

        $scope.getAllProduits = function () {
            console.log("my ass");
        }
        $scope.getProduitsParPrixCroissant = function () {

        }
        $scope.getProduitsParPrixDecroissant = function () {

        }
        $scope.getProduitsParCouleur = function (col) {

        }
        $scope.getProduitsParTheme = function (theme) {

        }
        $scope.getProduitsParNom = function (nom) {

        }
    }]);


/**
 * Controller inscription
 */
app.controller("InscriptionCtrl", ['$scope', 'Rest', function ($scope, Rest) {
        $scope.newSingleUser = {
            'mail': '',
            'mdp': '',
            'mdpRetype': '',
            'name': '',
            'surname': '',
            'numTel': '',
            'numFix': '',
            'adrLivraison': '',
            'adrFacturation': '',
            'newsletter': false
        }

        $scope.newSingleUserRestPost = new function () {
            console.log("Sending POST to REST server with new user");
            /**
             * TEST POST 
             */
            Rest.POSTNEWSINGLEUSER.post();
            console.log(posted);
            console.log(posted.$promise.then(function (data) {
                console.log(data);
            }));
        }

        /*
         * TEST GET
         */
//    var test = Rest.GETTEST.query();
//    console.log(test);
//    /*
//     * Need to resolve the returned values 
//     */
//    console.log(test.$promise.then(function(data) {
//       console.log(data.msg);
//       console.log(data);
//   }));

    }]);