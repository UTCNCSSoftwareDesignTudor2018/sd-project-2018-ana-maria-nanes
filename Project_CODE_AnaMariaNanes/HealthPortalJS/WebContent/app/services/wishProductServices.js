(function() {
	var wishProductServices = angular.module('wishProductServices', []);

	wishProductServices.factory('WishProductFactory', ['$http','$window','config',
			function($http,$window, config) {

				var privateWishProductDelete = function(wishProdId) {
					return $http.delete(config.API_URL + '/wishListProduct/' + wishProdId);
				};
				
				var privateDeleteByWishList = function(wishListId){
					return $http.delete(config.API_URL + '/wishListProduct/wishList/' + wishListId);
				};
				
				var privateAddWishProduct= function(productId,userId,data,_config) {
					return $http.post(config.API_URL + '/wishListProduct/added/' + productId + "/user/" + userId, data, _config ); 
				
				};

				return {
					deleteById : function(wishProdId) {
						     return privateWishProductDelete(wishProdId);
					},
					
					deleteByWishList: function(wishListId){
					         return privateDeleteByWishList(wishListId);
					 },
					 
					 addProductToWishList : function(productId,userId,data,_config){
						     return privateAddWishProduct(productId,userId,data,_config);
					 }

				};

			} ]);

})();