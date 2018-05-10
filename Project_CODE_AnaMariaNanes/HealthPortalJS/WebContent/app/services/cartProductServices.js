(function() {
	var cartProductServices = angular.module('cartProductServices', []);

	cartProductServices.factory('CartProductFactory', [ '$http', 'config', function($http, config) {		
	
	var privateAddCartProduct = function(productId,userId,data,_config) {
		return $http.post(config.API_URL + '/cartProduct/added/' + productId + "/user/" + userId, data, _config ); 
	};
	
	var privateDeleteByShoppingCart = function(cartId){
		return $http.delete(config.API_URL + '/cartProduct/delete/shoppingCart/' + cartId);
	};
	
	var privateDeleteById = function(cartProdId){
		return $http.delete(config.API_URL + '/cartProduct/' + cartProdId);
	};
	
	
	 return {
         addProductToCart : function(productId,userId,data,_config) {                 
	         return privateAddCartProduct(productId,userId,data,_config);
         },
	 
	     deleteByShoppingCart: function(cartId){
	    	 return privateDeleteByShoppingCart(cartId);
	     },
	     
	     deleteById : function(cartProdId){
	    	 return  privateDeleteById(cartProdId);
	     }
                
      };

		} ]);

})();