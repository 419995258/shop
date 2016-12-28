var centerAppmodule = angular.module('centerApp', []);

centerAppmodule.controller('centerController', function($scope, $http, $rootScope, $stateParams, $state) {		
	
	////////////////////////////////////////////////////////判断当前页
	$scope.init = function() {
		if ($state.current.name == 'center') {
			$('.qz_content > a').removeClass('active');
			$('#btn_center').addClass('active');			
		};	
		ckLogined();
	};

	$scope.init();
	
	$scope.userInfo = {};

	
	/** ******************************************验证登录**************************************** */
	// 验证登录
	function ckLogined() {
		$http({
			method : "POST",
			url : "../userC/ckUserLogined",
		}).success(function(data, status) {
			if (!data.success) {
				$state.go("login", $scope.$parent.paramTemp, { reload: true });
			}else{
				$scope.userInfo = data.result.user;
			}
		});
	}
	
	/** ****************************************更新用户信息**************************************** */
	$scope.updateUserInfo = function() {
		$http({
			method : "PUT",
			url : "../userC/updateUserInfo",
			data:$scope.userInfo
		}).success(function(data, status) {
			if (data.success) {
				alert("更新成功,请重新登录");
				loginOut();
			}
		});
	};
	/** ******************************************登出**************************************** */
	function loginOut(){
		$http({
			method : "POST",
			url : "../userC/logout",
		}).success(function(data, status) {
			location.href="001_index.html";
			
		});
	};
});