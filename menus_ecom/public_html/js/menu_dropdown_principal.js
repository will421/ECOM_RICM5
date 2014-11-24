angular.module('menu_dropdown_principal').controller('DropdownCtrl', function ($scope, $log) {
    $scope.items = [
        'The first choice!',
        'And another choice for you.',
        'but wait! A third!'
    ];

    $scope.status = {
        isopen: false,
        isopenb: false,
        isopenc: false,
        isopend: false
    };
    $scope.toggled = function (open) {
        $log.log('Dropdown is now: ', open);
    };
    
    
}, ['ui.bootstrap']);