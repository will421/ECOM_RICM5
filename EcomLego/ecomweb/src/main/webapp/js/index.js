var app = angular.module('appMain', ['ui.bootstrap', 'ngResource', 'ui.router', 'ngCart', 'angularFileUpload', 'restservice'], function ($httpProvider) {
    // Use x-www-form-urlencoded Content-Type
    //templateURL changes ng-view
});


/**
 * Controller menu navigation principal (Dropdown)
 * alot of useless tests
 */
app.controller('DropdownCtrl', function ($scope, $log, $modal) {
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

    $scope.items = ['item1', 'item2', 'item3'];

    $scope.open = function (size) {

        var modalInstance = $modal.open({
            templateUrl: 'template/partial/modal-login.html',
            controller: 'ModalInstanceCtrl',
            size: size,
            resolve: {
                items: function () {
                    return $scope.items;
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            $scope.selected = selectedItem;
        }, function () {
            $log.info('Modal dismissed at: ' + new Date());
        });
    };
});

/**
 * Modal controller login
 */
app.controller('ModalInstanceCtrl', function ($scope, $modalInstance, items) {

    $scope.items = items;
    $scope.selected = {
        item: $scope.items[0]
    };

    $scope.ok = function () {
        $modalInstance.close($scope.selected.item);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
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
 * Login Ctrl
 */
app.controller('LoginCtrl', function ($scope, $stateParams) {
    console.log("ENTERED ROUTE CONTROLER");
    console.log($stateParams);
    $scope.loginparams = $stateParams;
    //pour l'instant il y a $scope.loginparams.urlmail et $scope.loginparams.urlmdp
});

/**
 * Configuration Angular UI Router
 * add state to ui-sref
 */
app.config(function ($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/home');

    $stateProvider

            // HOME STATES AND NESTED VIEWS ========================================
            .state('#', {
                url: '/home',
                templateUrl: 'template/partial/partial-home.html',
            })
            //methode deeegeeeuuulaaassseee~~
            .state('home', {
                //file:///C:/Users/Pingu/Desktop/ECOM_RICM5/EcomLego/ecomweb/src/main/webapp/index.html#/home/mailde@user.com?urlmdp=passwr0D
                url: '/home/:urlmail?urlmdp',
                templateUrl: 'template/partial/partial-home.html',
                controller: 'LoginCtrl'
            })
            .state('produits', {
                url: 'produits',
                templateUrl: 'template/partial/partial-produits.html'
            })

            .state('imprimer-commanderundevis', {
                url: 'imprimer-commanderundevis',
                templateUrl: 'template/partial/partial-imprimer-commanderundevis.html'
            })

            .state('imprimer-creerunmodele', {
                url: 'imprimer-creerunmodele',
                templateUrl: 'template/partial/partial-imprimer-creerunmodele.html'
            })

            .state('imprimer-chargervotremodele', {
                url: 'imprimer-chargervotremodele',
                templateUrl: 'template/partial/partial-imprimer-chargervotremodele.html'
            })

            .state('imprimer-autres', {
                url: 'imprimer-autres',
                templateUrl: 'template/partial/partial-imprimer-autres.html'
            })

            .state('panier', {
                url: 'panier',
                templateUrl: 'template/partial/panier.html'
            })

            .state('inscription', {
                url: 'inscription',
                templateUrl: 'template/partial/partial-inscription.html'
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

        $scope.resultProduits = Rest.getAllProduits.query();

        $scope.critere = {
            'crit': null
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
            'nom': '',
            'prenom': '',
            'numTel': '',
            'numFix': '',
            'adrLivraison': '',
            'adrFacturation': '',
            'newsletter': false
        }

        $scope.newSingleUserRestPost = function () {
            console.log("NEW USER CREATED");
//            console.log($scope.newSingleUser);
            Rest.createUser($scope.newSingleUser);
        }

    }]);
app.directive('equals', function () {
    return {
        restrict: 'A', // only activate on element attribute
        require: '?ngModel', // get a hold of NgModelController
        link: function (scope, elem, attrs, ngModel) {
            if (!ngModel)
                return; // do nothing if no ng-model

            // watch own value and re-validate on change
            scope.$watch(attrs.ngModel, function () {
                validate();
            });

            // observe the other value and re-validate on change
            attrs.$observe('equals', function (val) {
                validate();
            });

            var validate = function () {
                // values
                var val1 = ngModel.$viewValue;
                var val2 = attrs.equals;

                // set validity
                ngModel.$setValidity('equals', !val1 || !val2 || val1 === val2);
            };
        }
    }
});
