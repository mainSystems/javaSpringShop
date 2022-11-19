angular.module('appShop', []).controller('indexController', function($scope, $http) {
    const contextPath='http://localhost:8180/app/shop';

$scope.map = new Map();

    $scope.loadProducts = function() {
        $http.get(contextPath + '/mainPage')
        .then(function (response){
            console.log(response);
            $scope.productList = response.data;
        });
    };

    $scope.newCart = function() {
        $http.get(contextPath + '/new_cart')
        .then(function (response){
            console.log(response);
        });
    };

    $scope.changeProductCount = function(productId, productCount) {
        $http({
            url: contextPath + '/changeProductCount',
            method: 'GET',
            params: {
                productId: productId,
                productCount: productCount
            }
        }).then(function(response) {
            $scope.loadProducts();
        });
    }

    $scope.getProductCount = function(productId) {
            $http({
                url: contextPath + '/getProductCount',
                method: 'GET',
                params: {
                    productId: productId
                }
            }).then(function(response) {
                console.log(response);
                $scope.productCount = response.data;
                console.log(productId);
                $scope.map.set(productId,$scope.productCount);
                console.log($scope.map);
            });
        }


    $scope.loadProducts();
});