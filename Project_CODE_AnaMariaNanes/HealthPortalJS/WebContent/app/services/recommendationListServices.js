(function() {
	var recommendationListServices = angular.module('recommendationListServices', []);

	recommendationListServices.factory('RecommendationListFactory', [ '$http', 'config',     
			function($http, config) {
		
		  var privateRecommendationListOfUser = function(userId) {
			    return $http.get(config.API_URL + '/recommendationList/user/' + userId);    
		  };
		  
		  var privateAddRecommendedList = function(userId,data,_config) {
				return $http.post(config.API_URL + '/recommendationList/added/' + userId, data, _config ); 
			
			};
		  
		  return {
		      	
			      findRecommendationListOfUser : function(userId){
					   return privateRecommendationListOfUser(userId);
				  }, 
				  
				  addRecommendationList : function(userId,data,_config){
					  return   privateAddRecommendedList(userId,data,_config);
				  },
         };

			} ]);

})();