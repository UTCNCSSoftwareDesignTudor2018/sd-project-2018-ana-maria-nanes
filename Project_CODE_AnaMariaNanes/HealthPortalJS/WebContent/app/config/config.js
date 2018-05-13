(function() {
	var configModule = angular.module('configModule', []);

	configModule.constant('config', {
		API_URL : 'http://localhost:8081/health-portal'
	});

})();