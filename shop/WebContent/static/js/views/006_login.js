var loginAppmodule = angular.module('loginApp', []);

loginAppmodule.controller('loginController', function($scope, $http, $rootScope, $stateParams, $state) {		
	var page = $stateParams.pageName;
	////////////////////////////////////////////////////////判断当前页
	$scope.items = {};
	
	$scope.init = function() {
		if ($state.current.name == 'login') {
			$('.qz_content > a').removeClass('active');
			$('#btn_center').addClass('active');			
		};	
	};

	$scope.init();
	
	$scope.login = function(){
		$scope.tsText = '';

		//验证登录信息
		if (verifyIsNull($scope.items.username)) {
			$scope.tsText = '请填写用户名';
			return;
		};
		if (verifyIsNull($scope.items.password)) {
			$scope.tsText = '请填写密码';
			return;
		};
		
		$http({
			method : "POST",
			url : "../userC/login",
			data : $scope.items,
		}).success(function(data, status) {
			if (data.success) {
				//$scope.$parent.user = data.result.user;
				location.href="001_index.html";
			}else{
				$scope.tsText = '账号或密码错误';
			}
		});
	};
});