(function() {
	var herbServices = angular.module('herbServices', []);

	herbServices.factory('HerbFactory', [ '$http', 'config',     
			function($http, config) {

		var privateAllHerbs = function() {
			return $http.get(config.API_URL + '/product/herb/all');
		};
		
		 return {
				      findAll : function() {                 
					      return privateAllHerbs()
				          }
		         };
		  	
			} ]);

})();