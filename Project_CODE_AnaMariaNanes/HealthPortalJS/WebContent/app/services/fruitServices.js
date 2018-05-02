(function() {
	var fruitServices = angular.module('fruitServices', []);

	fruitServices.factory('FruitFactory', [ '$http', 'config',     
			function($http, config) {

		var privateAllFruits = function() {
			return $http.get(config.API_URL + '/product/fruit/all');
		};
		
		 return {
				      findAll : function() {                 
					      return privateAllFruits()
				          }
		         };
		  	
			} ]);

})();