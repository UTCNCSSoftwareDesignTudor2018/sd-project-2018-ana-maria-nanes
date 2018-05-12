(function() {
	var wishListServices = angular.module('wishListServices', []);

	wishListServices.factory('WishListFactory', [ '$http', 'config',     
			function($http, config) {
		
		  var privateWishListOfUser = function(userId) {
			    return $http.get(config.API_URL + '/wishList/user/' + userId);    
		  };
		  
		  return {
		      	
			      findWishListOfUser : function(userId){
					   return privateWishListOfUser(userId);
				},             
         };

			} ]);

})();