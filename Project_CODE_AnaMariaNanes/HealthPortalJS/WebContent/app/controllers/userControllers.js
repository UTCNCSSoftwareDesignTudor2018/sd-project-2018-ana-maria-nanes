(function() {

	var usersModule = angular.module('userControllers', [ 'ngRoute' ])

	usersModule.config(function($routeProvider) {
		$routeProvider.when('/user/:id', {
			templateUrl : 'app/views/user/user-mainpage.html',
			controller : 'UserController',
			controllerAs : "userCtrl"
		}).when('/user/:id/fruits', {
			templateUrl : 'app/views/user/user-fruits-list.html',
			controller : 'UserFruitsListController',
			controllerAs : "userFruitsAllCtrl"
		}).when('/user/:id/herbs', {
			templateUrl : 'app/views/user/user-herbs-list.html',
			controller : 'UserHerbListController',
			controllerAs : "userHerbAllCtrl"
		}).when('/user/:id/vegetables', {
			templateUrl : 'app/views/user/user-vegetables-list.html',
			controller : 'UserVegetableListController',
			controllerAs : "userVegetableAllCtrl"
		}).when('/user/:id/hospitals', {
			templateUrl : 'app/views/user/user-hospitals-list.html',
			controller : 'UserHospitalListController',
			controllerAs : "userHospitalAllCtrl"
		}).when('/user/:id/diseases', {
			templateUrl : 'app/views/user/user-diseases-list.html',
			controller : 'UserDiseaseListController',
			controllerAs : "userDiseaseAllCtrl"
		}).when('/user/:id/shoppingCart', {
			templateUrl : 'app/views/user/user-cart.html',
			controller : 'UserCartController',
			controllerAs : "userCartAllCtrl"
		})
	});

	usersModule
			.controller(
					'UserController',
					[
							'$scope',
							'$routeParams',
							'$window',
							'UserFactory',
							function($scope, $routeParams, $window, UserFactory) {

								var id = $routeParams.id;
								var promise = UserFactory.findById(id);
								$scope.user = null;
								promise.success(function(data) {
									$scope.user = data;
								}).error(
										function(data, status, header, config) {
											alert(status);
										});

								$scope.UpdateData = function() {

									var data = $scope.user;

									var _config = {
										headers : {
											'Content-Type' : 'application/json;charset=utf-8;'
										}
									}

									UserFactory
											.updateUser(id, data, _config)
											.success(
													function() {
														$scope.feedbackMessage = "The user account info was successfully updated.";
													})
											.error(
													function() {
														$scope.feedbackMessage = "An error occured while updating the user account info.";
													})
								};

								$scope.DeleteData = function() {
									UserFactory
											.deleteUserById($scope.user.userId);
									$window
											.alert("The account has been deleted.");

								};
							} ]);

	usersModule.controller('UserFruitsListController', [ '$scope',
			'$routeParams', '$window', 'FruitFactory', 'UserFactory',
			function($scope, $routeParams, $window, FruitFactory, UserFactory) {

				var id = $routeParams.id;

				$scope.fruits = [];
				$scope.message = "Fruit is nature's candy.";

				var promise = UserFactory.findById(id);
				$scope.user = null;
				promise.success(function(data) {
					$scope.user = data;
				}).error(function(data, status, header, config) {
					alert(status);
				});

				var promise = FruitFactory.findAll();
				promise.success(function(data) {
					$scope.fruits = data;
				}).error(function(data, status, header, config) {
					alert(status);
				});
			} ]);

	usersModule.controller('UserHerbListController', [ '$scope',
			'$routeParams', '$window', 'HerbFactory', 'UserFactory',
			function($scope, $routeParams, $window, HerbFactory, UserFactory) {

				var id = $routeParams.id;

				$scope.herbs = [];
				$scope.message = "Herbalism is nature's way of healing us.";

				var promise = UserFactory.findById(id);
				$scope.user = null;
				promise.success(function(data) {
					$scope.user = data;
				}).error(function(data, status, header, config) {
					alert(status);
				});

				var promise = HerbFactory.findAll();
				promise.success(function(data) {
					$scope.herbs = data;
				}).error(function(data, status, header, config) {
					alert(status);
				});
			} ]);

	usersModule.controller('UserVegetableListController', [
			'$scope',
			'$routeParams',
			'$window',
			'VegetableFactory',
			'UserFactory',
			function($scope, $routeParams, $window, VegetableFactory,
					UserFactory) {

				var id = $routeParams.id;

				$scope.vegetables = [];
				$scope.message = "Vegetables are the guide to a healthy life.";

				var promise = UserFactory.findById(id);
				$scope.user = null;
				promise.success(function(data) {
					$scope.user = data;
				}).error(function(data, status, header, config) {
					alert(status);
				});

				var promise = VegetableFactory.findAll();
				promise.success(function(data) {
					$scope.vegetables = data;
				}).error(function(data, status, header, config) {
					alert(status);
				});
			} ]);

	usersModule.controller('UserHospitalListController', [
			'$scope',
			'$routeParams',
			'$window',
			'HospitalFactory',
			'UserFactory',
			function($scope, $routeParams, $window, HospitalFactory,
					UserFactory) {

				var id = $routeParams.id;

				$scope.hospitals = [];
				$scope.message = "Find the best hospital for you.";

				var promise = UserFactory.findById(id);
				$scope.user = null;
				promise.success(function(data) {
					$scope.user = data;
				}).error(function(data, status, header, config) {
					alert(status);
				});

				var promise = HospitalFactory.findAll();
				promise.success(function(data) {
					$scope.hospitals = data;
				}).error(function(data, status, header, config) {
					alert(status);
				});
			} ]);

	usersModule
			.controller(
					'UserDiseaseListController',
					[
							'$scope',
							'$routeParams',
							'$window',
							'DiseaseFactory',
							'UserFactory',
							function($scope, $routeParams, $window,
									DiseaseFactory, UserFactory) {

								var id = $routeParams.id;

								$scope.diseases = [];
								$scope.message = "Any kind of disease is to be treated accordingly.";

								var promise = UserFactory.findById(id);
								$scope.user = null;
								promise.success(function(data) {
									$scope.user = data;
								}).error(
										function(data, status, header, config) {
											alert(status);
										});

								var promise = DiseaseFactory.findAll();
								promise.success(function(data) {
									$scope.diseases = data;
								}).error(
										function(data, status, header, config) {
											alert(status);
										});
							} ]);

	usersModule.controller('UserCartController', [
			'$scope',
			'$route',
			'$routeParams',
			'$window',
			'UserFactory',
			'ShoppingCartFactory',
			'ProductFactory',
			'CartProductFactory',
			function($scope, $route, $routeParams, $window, UserFactory,
					ShoppingCartFactory, ProductFactory, CartProductFactory) {

				var userId = $routeParams.id;

				$scope.products = [];

				var promise = UserFactory.findById(userId);
				$scope.user = null;
				promise.success(function(data) {
					$scope.user = data;
				}).error(function(data, status, header, config) {
					alert(status);
				});

				var products = [];
				var promise = UserFactory.findShoppingCart(userId);
				promise.success(function(data) {
					$scope.products = data;

				}).error(function(data, status, header, config) {
					alert(status);
				});

				var promise = ShoppingCartFactory
						.findShoppingCartOfUser(userId);
				promise.success(function(data) {
					$scope.shoppingCart = data;
				}).error(function(data, status, header, config) {
					alert(status);
				});

				$scope.PaceOrder = function() {

					$window.alert("The order has been placed.");
					CartProductFactory.deleteByShoppingCart($scope.shoppingCart.cartId);
					
					var products = [];
					var promise = UserFactory.findShoppingCart(userId);
					promise.success(function(data) {
						$scope.products = data;

					}).error(function(data, status, header, config) {
						alert(status);
					});
					
					$window.location.reload();

				}

				$scope.RemoveFromShoppingCart = function(cartProdId) {
					CartProductFactory.deleteById(cartProdId);
                    $window.location.reload();
				}

			} ]);

})();