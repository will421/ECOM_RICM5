angular.module('ui.bootstrap.demo', ['ui.bootstrap']);
angular.module('ui.bootstrap.demo').controller('DropdownCtrl', function ($scope, $log) {
  $scope.items = [
    'The first choice!',
    'And another choice for you.',
    'but wait! A third!'
  ];

  $scope.status = {
    isopena: false,
	isopenb: false,
	isopenc: false,
	isopend: false,
  };

  $scope.toggled = function(open) {
    $log.log('Dropdown is now: ', open);
  };
});