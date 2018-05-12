(function() {
	var userServices = angular.module('userServices', []);

	userServices.factory('UserFactory', [ '$http', 'config',     
			function($http, config) {

				var privateUserById = function(id) {
					return $http.get(config.API_URL + '/user/' + id); 
				};
				
				var privateUserByUsername =  function(username){
					return $http.get(config.API_URL + '/user/account/' + username); 
				};
				
				var privateDeleteUserById = function(userId){
					return $http.delete(config.API_URL + '/user/' + userId);
				};
				
				var privateUpdateUser = function(id,data,_config){
					return $http.put(config.API_URL + '/user/' + id, data, _config);
				};
				
				var privateUserShoppingCart =  function(id){
					return  $http.get(config.API_URL + '/user/' + id + "/shoppingCart"); 
				};
				
				var privateUserWishList =  function(id){
					return  $http.get(config.API_URL + '/user/' + id + "/wishList"); 
				}
				
				return {
					 findById : function(id) {                 
						return privateUserById(id);
					  },
				
				     findByUsername: function(username){
				    	return privateUserByUsername(username);
				      },
				      
				     updateUser : function(id,data,_config){
							return privateUpdateUser(id,data,_config);
						},
						
				     deleteUserById : function(userId){         
								return privateDeleteUserById(userId);
					     },
					     
					 	
					 findShoppingCart : function(userId){         
							return privateUserShoppingCart(userId);
						  },
						  
				      findWishList : function(userId){         
							return privateUserWishList(userId);
						  },
				   
				      };
			} ]);

})();