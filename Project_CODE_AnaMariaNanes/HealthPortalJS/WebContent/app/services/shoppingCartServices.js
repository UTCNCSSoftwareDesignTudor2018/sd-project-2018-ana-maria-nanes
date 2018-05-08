(function() {
	var shoppingCartServices = angular.module('shoppingCartServices', []);

	shoppingCartServices.factory('ShoppingCartFactory', [ '$http', 'config',     
			function($http, config) {
		
		  var privateShoppingCartOfUser = function(userId) {
			    return $http.get(config.API_URL + '/shoppingCart/user/' + userId);    
		  };
		  
		  return {
		      	
			      findShoppingCartOfUser : function(userId){
					   return privateShoppingCartOfUser(userId);
				},             
         };

			} ]);

})();