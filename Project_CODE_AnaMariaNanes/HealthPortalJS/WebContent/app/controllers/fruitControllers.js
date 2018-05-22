(function() {

	var fruitsModule = angular.module('fruitControllers', [ 'ngRoute' ])

	fruitsModule.config(function($routeProvider) {
		$routeProvider.when('/fruits/admin', {
			templateUrl : 'app/views/fruit/fruits-admin-list.html',
			controller : 'FruitAdminListController',
			controllerAs : "fruitAdminListCtrl"
		}).when('/fruit/form', {
			templateUrl : 'app/views/fruit/fruit-form.html',
			controller : 'FruitAddNewController',
			controllerAs : "fruitAddNewCtrl"
		}).when('/fruit/admin/:fruitId', {
			templateUrl : 'app/views/fruit/fruit-admin-details.html',
			controller : 'FruitController',
			controllerAs : "fruitCtrl"
		}).when('/fruit/admin/update/:fruitId', {
			templateUrl : 'app/views/fruit/fruit-update.html',
			controller : 'FruitUpdateController',
			controllerAs : "fruitUpdateCtrl"
		}).when('/fruit/:fruitId/user/:id', {
			templateUrl : 'app/views/fruit/fruit-user-details.html',
			controller : 'FruitUserDetailsController',
			controllerAs : "fruitUserDetailsCtrl"
		})
	});

	fruitsModule.controller('FruitAdminListController', [ '$scope',
			'$routeParams', '$window', 'FruitFactory',
			function($scope, $routeParams, $window, FruitFactory) {

				$scope.fruits = [];
				$scope.message = "Fruit is nature's candy.";

				var promise = FruitFactory.findAll();
				promise.success(function(data) {
					$scope.fruits = data;
				}).error(function(data, status, header, config) {
					alert(status);
				});

			} ]);

	fruitsModule
			.controller(
					'FruitAddNewController',
					[
							'$scope',
							'$http',
							'$routeParams',
							'$window',
							'FruitFactory',
							function($scope, $http, $routeParams, $window,
									FruitFactory) {

								$scope.message = "Fruit is nature's candy.";

								$scope.SendData = function() {
									var data = {

										productName : $scope.productName,
										type : "fruit",
										benefits : $scope.benefits,
										distributor : $scope.distributor,
										price : $scope.price,
										stock : $scope.stock,
										diseaseList : $scope.diseaseList,
										readMoreLink : $scope.readMoreLink,
									};

									var _config = {
										headers : {
											'Content-Type' : 'application/json;charset=utf-8;'
										}
									}

									$http
											.post(
													'http://localhost:8081/health-portal'
															+ '/product/added',
													data, _config)
											.success(
													function() {
														
														
														 if($scope.stock < 10 || $scope.price <= 0){
															 $scope.feedbackMessage = "An error occured! The fruit was not inserted in the system!";
												            }
											                else{
											                	$scope.feedbackMessage = "The fruit was successfully inserted in the system.";												            	
											                }

														$scope.productName = "";
														$scope.benefits = "";
														$scope.distributor = "";
														$scope.price = "";
														$scope.stock = "";
														$scope.diseaseList = "";
														$scope.readMoreLink = ""

													})
											.error(
													function() {
														$scope.feedbackMessage = "An error occured! The fruit was not inserted in the system!";

														$scope.productName = "";
														$scope.benefits = "";
														$scope.distributor = "";
														$scope.price = "";
														$scope.stock = "";
														$scope.diseaseList = "";
														$scope.readMoreLink = ""

													})
								};
							} ]);

	fruitsModule.controller('FruitController', [ '$scope', '$window',
			'$routeParams', 'FruitFactory',
			function($scope, $window, $routeParams, FruitFactory) {

				$scope.message = "Fruit is nature's candy.";

				var fruitId = $routeParams.fruitId;
				var promise = FruitFactory.findFruitById(fruitId);
				$scope.fruit = null;
				promise.success(function(data) {
					$scope.fruit = data;
				}).error(function(data, status, header, config) {
					alert(status);
				});

				$scope.DeleteData = function() {
					FruitFactory.deleteFruitById(fruitId);
					$window.alert("The fruit has been deleted.");
				};

			} ]);

	fruitsModule
			.controller(
					'FruitUpdateController',
					[
							'$scope',
							'$routeParams',
							'FruitFactory',
							function($scope, $routeParams, FruitFactory) {

								var id = $routeParams.fruitId;

								$scope.message = "Fruit is nature's candy.";

								var promise = FruitFactory.findFruitById(id);
								$scope.fruit = null;
								promise.success(function(data) {
									$scope.fruit = data;
								}).error(
										function(data, status, header, config) {
											alert(status);
										});

								$scope.message = "";

								$scope.UpdateData = function() {
									var data = $scope.fruit;

									var _config = {
										headers : {
											'Content-Type' : 'application/json;charset=utf-8;'
										}
									}

									FruitFactory
											.updateFruit(id, data, _config)
											.success(
													function() {												
														
														if($scope.fruit.stock < 10 || $scope.fruit.price <= 0){
															    $scope.feedbackMessage = "An error occured while updating the fruit info.";
												            }
											                else{
											                	$scope.feedbackMessage = "The fruit info was successfully updated.";											            	
											                }
													})
											.error(
													function() {
														$scope.feedbackMessage = "An error occured while updating the fruit info.";
													})
								};
							} ]);

	fruitsModule
			.controller(
					'FruitUserDetailsController',
					[
							'$scope',
							'$http',
							'$window',
							'$routeParams',
							'FruitFactory',
							'UserFactory',
							'CartProductFactory',
							'WishProductFactory',
							function($scope,$http, $window, $routeParams,
									FruitFactory, UserFactory,
									CartProductFactory, WishProductFactory) {

								$scope.message = "Fruit is nature's candy.";

								var fruitId = $routeParams.fruitId; // fruitId
								var promise = FruitFactory
										.findFruitById(fruitId);
								$scope.fruit = null;
								promise.success(function(data) {
									$scope.fruit = data;
								}).error(
										function(data, status, header, config) {
											alert(status);
										});

								var userId = $routeParams.id; // userId
								var promise = UserFactory.findById(userId);
								$scope.user = null;
								promise.success(function(data) {
									$scope.user = data;
								}).error(
										function(data, status, header, config) {
											alert(status);
										});

								$scope.AddInShoppingCart = function(stock) { // add fruit

									if ($scope.quantity <= stock) {

										var data = null;
										var _config = {
											headers : {
												'Content-Type' : 'application/json;charset=utf-8;'
											}
										}

										var data = {

											quantity : $scope.quantity
										};

										CartProductFactory
												.addProductToCart(fruitId,
														userId, data, _config)
												.success(
														function() {
															$window
																	.alert("Fruit has been added to the shopping cart.");
															$scope.quantity = "";
														})
												.error(
														function() {
															$window
																	.alert("An error occured.");
															$scope.quantity = "";
														})
									} else {
										$window.alert("Not enough pieces in stock.");
										$scope.quantity = "";
									}
								};
								
								$scope.AddInWishList = function() { // add fruit in wish list


									var productData = {

											wishProdId : ""
										};
									
										var _config = {
											headers : {
												'Content-Type' : 'application/json;charset=utf-8;'
											}
										}
									
						
										WishProductFactory
												.addProductToWishList(fruitId,userId, productData, _config)
												.success(
														function() {
															$window.alert("Fruit has been added to the wish list.");
														})
												.error(
														function() {
															$window.alert("An error occured.");
														})
								};


							} ]);
})();