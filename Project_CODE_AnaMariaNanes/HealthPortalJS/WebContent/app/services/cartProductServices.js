(function() {
	var cartProductServices = angular.module('cartProductServices', []);

	cartProductServices.factory('CartProductFactory', [ '$http', 'config', function($http, config) {		
	
	var privateAddCartProduct = function(productId,userId,data,_config) {
		return $http.post(config.API_URL + '/cartProduct/added/' + productId + "/user/" + userId, data, _config ); 
	};
	
	 return {
         addProductToCart : function(productId,userId,data,_config) {                 
	         return privateAddCartProduct(productId,userId,data,_config);
         }
                
      };

		} ]);

})();