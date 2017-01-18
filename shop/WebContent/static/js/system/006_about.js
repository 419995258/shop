var aboutAppmodule = angular.module('aboutApp', []);

aboutAppmodule.controller('aboutController', function($scope, $http, $rootScope, $stateParams, $state,$sce) {

	$scope.Items = {};
	$scope.Items.id = 1;
	var txt1 = '';
	
	
	////////////////////////////////////////////////////////判断当前页	
	function init() {		
		if ($state.current.name == 'about') {
			$('.menu_btn').removeClass('active');
			$('#btn_about').addClass('active');
		};
		query();
	};
	
	init();
	
	
	// ////////////////////////////////////////////////////////实例化编辑器
	var um = UM.getEditor('myEditor');
	// 切换页面后重新初始化
	$scope.$on('$destroy', function() {
		um.destroy();
	});
	// 查看详情
	function query() {
		$http({
			method : "POST",
			url : "../otherManageC/showAboutData",
			data : $scope.Items
		}).success(function(data, status) {
			$scope.Items = data;
			um.setContent($scope.Items.content);
		}).error(function(data, status) {
		});
	}
	;

	// 更新
	$scope.alter = function() {
		$('#deleteModal').modal('show');
		um.ready(function() {
			/*
			 * //设置编辑器的内容 ckum1.setContent('hello'); //获取html内容，返回: <p>hello</p>
			 * var html = ue.getContent();
			 */
			// 获取纯文本内容，返回: hello
			txt1 = um.getContent();
			$scope.Items.content = txt1;
		});

		$http({
			method : "PUT",
			url : "../otherManageC/updateAboutData",
			data : $scope.Items,
		}).success(function(data, status) {
			if (data.success) {
				$('#addModal').modal('hide');
				
				query();
			} else {
				$('#addModal').modal('hide');
			}
		}).error(function(data, status) {
			alert('保存失败');
		});
	};
	
});