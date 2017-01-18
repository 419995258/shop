var orderAppmodule = angular.module('orderApp', []);

orderAppmodule.controller('orderController', function($scope, $http, $rootScope, $stateParams, $state) {		
	
	////////////////////////////////////////////////////////判断当前页
	$scope.order = {};
	$scope.pageSize = 10;// 每页多少条
	$scope.pages = 1;// 总页数
	$scope.maxSize = 10; // 一次可现实多少分页标签，超过maxSize后则以...代替
	$scope.totalItems = 1; // 总共多少条记录
	$scope.currentPage = 1; // 当前页码
	$scope.bigTotalItems = 180;
	$scope.bigCurrentPage = 1;
	var resultVo = {};
	$scope.queryText = '';
	var XIdTemp = '';
	$scope.jkdIndex = ''; // 点击索引
	$scope.orderItem= {};
	
	
	
	$scope.init = function() {
		if ($state.current.name == 'order') {
			$('.qz_content > a').removeClass('active');
			$('#btn_order').addClass('active');			
		};	
		
		ckLogined();
		console.log("123");
		queryAllGoodsBuyByUserId();
	};

	$scope.init();
	
	
	
	/** ******************************************验证登录**************************************** */
	// 验证登录
	function ckLogined() {
		$http({
			method : "POST",
			url : "../userC/ckUserLogined",
		}).success(function(data, status) {
			if (!data.success) {
				$state.go("login", $scope.$parent.paramTemp, { reload: true });
			}
		});
	}
	/**********************************************查看详情*******************************************/
	$scope.openXD = function(index) {
		XIdTemp = index;
		$scope.jkdIndex = XIdTemp;
		$http({
			method : "POST",
			url : "../ordermanageC/queryOrderData",
			data : $scope.order[$scope.jkdIndex]
		}).success(function(data, status) {
			$scope.orderItem = data.result.data;
			XIdTemp = '';
			$('#fixModal').modal('show');
		});
	};
	
	/********************************************查询所有信息**************************************/ 
	function queryAllGoodsBuyByUserId() {
		resultVo.pageSize = $scope.pageSize;
		resultVo.currentpage = $scope.currentPage;
		resultVo.type = $scope.type;
		resultVo.queryText = $scope.queryText;
		
		// 查询所有信息
		$http({
			method : "POST",
			url : "../cardC/queryAllGoodsBuyByUserId",
			data : resultVo
		}).success(function(data, status) {
			$scope.order = data.rows;
			$scope.currentPage = data.pageNum; // 当前页码
			$scope.pageSize = data.pageSize;// 每页多少条
			$scope.pages = data.pages;// 总页数
			$scope.totalItems = data.total;// 总共多少条记录
		});
	};
	/***********************************************搜索分页*************************************************/
	$scope.pageChanged = function() {
		queryAllGoodsBuyByUserId();
	};
});