var registerAppmodule = angular.module('registerApp', []);

registerAppmodule.controller('registerController', function($scope, $http, $rootScope, $stateParams, $state) {		
	
	////////////////////////////////////////////////////////判断当前页
	$scope.init = function() {
		if ($state.current.name == 'register') {
			$('.qz_content > a').removeClass('active');
			$('#btn_center').addClass('active');			
		};	
	};

	$scope.init();
	
	
	$scope.items = {};
	$scope.tsText = '';

	
	/************************************************注册新用户************************************************/
	$scope.subNew = function() {
		

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
		if(verifyIsNull($scope.items.name)) {
			$scope.tsText = '请填写昵称';
			return;
		};
		if(verifyIsNull($scope.items.password)) {
			$scope.tsText = '请填写密码';
			return;
		};
		if(verifyIsNull($scope.items.passwordAgain)) {
			$scope.tsText = '请再次填写密码';
			return;
		};
		if($scope.items.password != $scope.items.passwordAgain) {
			$scope.tsText = '两次输入的密码不一致';
			return;
		};
		
		console.log("ok");
		$http({
			method : "PUT",
			url : "../userC/registerUser",
			data : $scope.items,
		}).success(function(data, status) {
			if (data.success) {
				alert("注册成功");
				$state.go("login", {}, {reload : true});
			} else {
				$scope.tsText = '邮箱已经被注册！';
				return;
			}
			
		});

	};
});