angular.module('appShop', [])
    .controller('indexController', function($scope, $http) {
    const contextPath='http://localhost:8180/app/shop';

    $scope.mapProductsCount = new Map();

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
            $scope.loadProducts();
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
                $scope.productCount = response.data;
                $scope.mapProductsCount.set(productId,$scope.productCount);
//                console.log(productId);
                console.log($scope.mapProductsCount);
            });
        }

    $scope.loadProducts();
});