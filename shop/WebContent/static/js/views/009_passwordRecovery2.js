var passwordRecovery2Appmodule = angular.module('passwordRecovery2App', []);

passwordRecovery2Appmodule.controller('passwordRecovery2Controller', function($scope, $http, $rootScope, $stateParams, $state) {		
	
	////////////////////////////////////////////////////////判断当前页
	$scope.init = function() {		
		if ($state.current.name == 'passwordRecovery2') {
			$('.qz_content > a').removeClass('active');
			$('#btn_Center').addClass('active');			
		};	
	};
	$scope.init();
	
	
});