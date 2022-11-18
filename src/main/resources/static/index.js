angular.module('appShop', []).controller('indexController', function($scope, $http) {
    const contextPath='http://localhost:8180/app/shop';


    $scope.loadProducts = function() {
        $http.get(contextPath + '/mainPage')
        .then(function (response){
            console.log(response);
            $scope.productList = response.data;
        });
    };


//    $scope.loadCart = function() {
//        $http.get(contextPath + '/list_cart')
//        .then(function (response){
//            console.log(response);
//            console.log(angular.equals(response.data, {}));
//            if(angular.equals(response.data, {})){
////                $scope.loadCart = {
////                                        "Product{id=0, title='Product_0', cost=0.0}": 00,
////                                        "Product{id=1, title='Product_1', cost=0.0}": 00
////                                    }
////                console.log($scope.loadCart);
//            }
//            $scope.loadCart = response.data;
//        });
//    };

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
            });
        }

//    $scope.getProductCount();
//    $scope.loadCart();
    $scope.loadProducts();
});