angular.module('appShop', []).controller('indexController', function($scope, $http) {
    const contexPath='http://localhost:8180/app/shop';

    $scope.initCartCountProducts=0;

    $scope.loadProducts = function() {
        $http.get(contexPath + '/mainPage')
        .then(function (response){
        $scope.productList = response.data;
        });
    };

    $scope.loadProducts();
});