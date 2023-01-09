angular.module('appShop', ['ngStorage'])
    .controller('indexController', function($scope, $http, $localStorage) {
    const contextAuthPath='http://localhost:8180/app/auth';
    const contextFrontPath='http://localhost:8000';
    const contextOrderPath='http://localhost:8181/app/api/v1/shop';
    const contextCorePath='http://localhost:8180/app/api/v1/shop';

    if($localStorage.marketUser) {
        try {
            let jwt = $localStorage.marketUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Gate().getTime() / 1000);
            if (currentTime > payload.exp) {
                console.log("Token expired!");
                $scope.clearUser();
            }
        } catch (e) {
        }
        $http.defaults.headers.common.Authorization =  'Bearer ' + $localStorage.marketUser.token;
    }

    $scope.tryToAuth = function() {
        $http.post(contextAuthPath, $scope.user)
        .then(function successCallback(response){
            if(response.data.token) {
                $http.defaults.headers.common.Authorization =  'Bearer ' + response.data.token;
                $localStorage.marketUser = {username: $scope.user.username, token: response.data.token};

                $scope.user.username = null;
                $scope.user.password = null;
            }
        }, function errorCallback(response){
        });
    };

    $scope.tryToLogOut = function() {
        $scope.clearUser();
        $scope.user = null;
//        $location.path('/');
    }

    $scope.clearUser = function() {
        delete $localStorage.marketUser;
        $https.defaults.headers.common.Authorization ="";
    }

    $scope.isUserLoggedIn = function() {
        if($localStorage.marketUser) {
            return true;
        } else {
            return false;
        }
    }

    $scope.mapProductsCount = new Map();

    $scope.loadProducts = function() {
        $http.get(contextCorePath + '/mainPage')
        .then(function (response){
            $scope.productList = response.data;
        });
    };

    $scope.newOrder = function() {
        $http.get(contextOrderPath + '/new_order')
        .then(function (response){
            $scope.loadProducts();
        });
    };

    $scope.changeProductCount = function(productId, productCount) {
        $http({
            url: contextOrderPath + '/productsCount',
            method: 'POST',
            params: {
                productId: productId,
                productCount: productCount
            }
        }).then(function(response) {
//            console.log(response);
            $scope.loadProducts();
        });
    }

    $scope.purgeProduct = function(productId) {
            $http({
                url: contextOrderPath + '/products',
                method: 'DELETE',
                params: {
                    productId: productId
                }
            }).then(function(response) {
                $scope.loadProducts();
            });
        }

    $scope.getProductCount = function(productId) {
            $http({
                url: contextOrderPath + '/productsCount',
                method: 'GET',
                params: {
                    productId: productId
                }
            }).then(function(response) {
                $scope.productCount = response.data;
                $scope.mapProductsCount.set(productId,$scope.productCount);
//                console.log(productId);
//                console.log($scope.mapProductsCount);
            });
        }

    $scope.loadProducts();
});