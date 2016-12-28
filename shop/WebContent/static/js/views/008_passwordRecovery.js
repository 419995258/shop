var passwordRecoveryAppmodule = angular.module('passwordRecoveryApp', []);

passwordRecoveryAppmodule.controller('passwordRecoveryController', function($scope, $http, $rootScope, $stateParams, $state) {		
	
	////////////////////////////////////////////////////////判断当前页
	$scope.init = function() {
		if ($state.current.name == 'passwordRecovery') {
			$('.qz_content > a').removeClass('active');
			$('#btn_center').addClass('active');			
		};	
	};

	$scope.init();
	
	$scope.items = '';
	$scope.tsText = '';
	
	$scope.subNew = function() {
		$scope.tsText = '';
		
		//校验
		if(verifyIsNull($scope.items.username)) {
			$scope.tsText = '请填写邮箱';
			return;
		};
		username = $scope.items.username;
		var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if(!filter.test(username)) {
			$scope.tsText = '请填写正确的邮箱';

			return;
		};	
		
		$http({
			method : "PUT",
			url : "../userC/recoveryPass",
			data : username,
		}).success(function(data, status) {
			if (data.success) {	
				$state.go("passwordRecovery2", {}, { reload: true });
			}else{
				$scope.tsText = data.message;
			}
		});
		
		
	};
	
	
});