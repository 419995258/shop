var loginLogAppmodule = angular.module('loginLogApp', []);

loginLogAppmodule.controller('loginLogController', function($scope, $http,
		$rootScope, $stateParams, $state) {

	// //////////////////////////////////////////////////////判断当前页
	$scope.pageSize = 10;// 每页多少条
	$scope.pages = 1;// 总页数
	$scope.maxSize = 10; // 一次可现实多少分页标签，超过maxSize后则以...代替
	$scope.totalItems = 1; // 总共多少条记录
	$scope.currentPage = 1; // 当前页码
	$scope.bigTotalItems = 180;
	$scope.bigCurrentPage = 1;
	var resultVo = {};
	$scope.log = {};
	
	$scope.add = {};

	$scope.init = function() {
		if ($state.current.name == 'loginLog') {
			$('.menu_btn').removeClass('active');
			$('#loginLog').addClass('active');
		}
		;
		 ckLogined();
		queryLog();
		queryAdd();
	};
	$scope.init();

	/** ******************************************查询所有信息************************************* */
	function queryLog() {
		resultVo.pageSize = $scope.pageSize;
		resultVo.currentpage = $scope.currentPage;

		// 查询所有信息
		$http({
			method : "POST",
			url : "../userC/queryLog",
			data : resultVo
		}).success(function(data, status) {
			$scope.log = data.rows;
			$scope.currentPage = data.pageNum; // 当前页码
			$scope.pageSize = data.pageSize;// 每页多少条
			$scope.pages = data.pages;// 总页数
			$scope.totalItems = data.total;// 总共多少条记录
		});
	}
	;
	/** *********************************************搜索翻页************************************************ */
	$scope.pageChanged = function() {
		queryLog();
	};
	
	/** ******************************************验证登录**************************************** */
	// 验证登录
	function ckLogined() {
		$http({
			method : "POST",
			url : "../userC/ckLogined",
		}).success(function(data, status) {
			if (!data.success) {
				location.href = "adminLogin.html";
			}
		});
	}
	
	/** ******************************************查询访问人数，交易额度**************************************** */
	function queryAdd(){
		$http({
			method : "POST",
			url : "../userC/queryAdd",
		}).success(function(data, status) {
			$scope.add = data.result.add;
			console.log("查询成功");
		});
	};

});