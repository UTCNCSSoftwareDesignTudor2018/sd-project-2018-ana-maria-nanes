(function() {
	var userDiseaseServices = angular.module('userDiseaseServices', []);

	userDiseaseServices.factory('UserDiseaseFactory', ['$http','$window','config',
			function($http,$window, config) {
		
		     var privateDeleteByUser = function(userId){
			          return $http.delete(config.API_URL + '/userDisease/user/' + userId);
		      };
		      
		      var privateDeleteUserDisease = function(id){
		               return $http.delete(config.API_URL + '/userDisease/' + id);
	          };
	          
	          var privateAddUserDisease = function(userId,diseaseId){
	               return $http.post(config.API_URL + '/userDisease/user/' + userId + "/disease/" + diseaseId);
              };
		
				return {					
					deleteByUser : function(userId){
						return privateDeleteByUser(userId);
					},
					
					deleteUserDisease: function(id){
						return privateDeleteUserDisease(id);
					},
					
					addUserDisease : function(userId, diseaseId){
						return privateAddUserDisease(userId,diseaseId);
					}
				};

			} ]);

})();