angular.module('ui.bootstrap.demo', ['ui.bootstrap']);
angular.module('ui.bootstrap.demo').controller('CarouselDemoCtrl', function ($scope) {
    $scope.myInterval = 5000;
    var slides = $scope.slides = [];

    slides.push({})
    $scope.addSlide = function () {
        var newWidth = 600 + slides.length + 1;
        slides.push({
            image: 'img/poring.png',
            text: ''
        }, {
            image: 'img/poporing.png',
            text: ''
        }

        );
    };
    $scope.addSlide();

});