(function() {
	
	var fruitsModule = angular.module('fruitControllers', [ 'ngRoute' ])

	fruitsModule.config(function($routeProvider) {
		$routeProvider.when('/fruits/admin', {
			templateUrl : 'app/views/fruit/fruits-admin-list.html',
			controller : 'FruitAdminListController',
			controllerAs : "fruitAdminListCtrl"
	   })
	   
	});
	
	fruitsModule.controller('FruitAdminListController', [ '$scope','$routeParams','$window', 'FruitFactory',
		function($scope,$routeParams, $window, FruitFactory) {
			
		     $scope.fruits = [];
		     $scope.message = "Fruit is nature's candy.";
		
		     var promise = FruitFactory.findAll();					
		     promise.success(function(data) {
			      $scope.fruits= data;
		     }).error(function(data, status, header, config) {
			      alert(status);
		     });
					
		} ]);
	
})();