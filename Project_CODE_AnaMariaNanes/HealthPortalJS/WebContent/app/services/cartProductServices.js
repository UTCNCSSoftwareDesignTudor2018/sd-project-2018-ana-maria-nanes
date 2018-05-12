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
	
	var privateCartProduct = function(cartProdId) {
		return $http.get(config.API_URL + '/cartProduct/' + cartProdId);    
	};
	
	var privateUpdateCartProduct = function(id,productId,cartId,data,_config){
		return $http.put(config.API_URL + '/cartProduct/' + id + "/product/" + productId + "/cart/" + cartId, data, _config);
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
	     },
	     
	     findById : function(cartProdId){
	    	 return privateCartProduct(cartProdId);
	     },
	     
	     updateCartProduct : function(id,productId,cartId,data,_config){
	    	 return privateUpdateCartProduct(id,productId,cartId,data,_config);
	     },
	                  
      };

		} ]);

})();