var allOrder2Appmodule = angular.module('allOrder2App', []);

allOrder2Appmodule.controller('allOrder2Controller', function($scope, $http, $rootScope, $stateParams, $state) {		
	
	////////////////////////////////////////////////////////判断当前页
	$scope.pageSize = 10;// 每页多少条
	$scope.pages = 1;// 总页数
	$scope.maxSize = 10; // 一次可现实多少分页标签，超过maxSize后则以...代替
	$scope.totalItems = 1; // 总共多少条记录
	$scope.currentPage = 1; // 当前页码
	$scope.bigTotalItems = 180;
	$scope.bigCurrentPage = 1;
	var delIdTemp = '';
	var XIdTemp = '';
	var resultVo = {};
	$scope.jkdIndex = ''; // 点击索引
	$scope.xqItems = {}; // 详情弹出框填入的数组
	$scope.orderMini= {};
	$scope.order= {};
	$scope.selectItems = [];
	$scope.queryText = '';
	
	
	$scope.init = function() {
		if ($state.current.name == 'allOrder2') {
			$('.menu_btn').removeClass('active');
			$('#allOrder2').addClass('active');			
		};
		ckLogined();
		var queryTemp = [{'type':0,'name':'显示全部'},{'type':1,'name':'搜姓名'},{'type':2,'name':'搜电话'}];
		$scope.selectItems = queryTemp;
		$scope.qureyType = queryTemp[0];
		queryAllOrder();
	};
	$scope.init();
	/********************************************查询所有信息**************************************/ 
	function queryAllOrder() {
		resultVo.pageSize = $scope.pageSize;
		resultVo.currentpage = $scope.currentPage;
		resultVo.type = $scope.type;
		resultVo.queryText = $scope.queryText;
		resultVo.queryType = $scope.qureyType.type;
		
		// 查询所有信息
		$http({
			method : "POST",
			url : "../ordermanageC/queryAllOrder",
			data : resultVo
		}).success(function(data, status) {
			$scope.orderMini = data.rows;
			$scope.currentPage = data.pageNum; // 当前页码
			$scope.pageSize = data.pageSize;// 每页多少条
			$scope.pages = data.pages;// 总页数
			$scope.totalItems = data.total;// 总共多少条记录
		});
	};
	/***********************************************搜索*************************************************/
	$scope.pageChanged = function() {
		queryAllOrder();
	};
	/**********************************************查看详情*******************************************/
	$scope.openXD = function(index) {
		XIdTemp = index;
		$scope.jkdIndex = XIdTemp;
		$http({
			method : "POST",
			url : "../ordermanageC/queryAllOrderData",
			data : $scope.orderMini[$scope.jkdIndex]
		}).success(function(data, status) {
			$scope.order = data.result.data;
			XIdTemp = '';
			$('#fixModal').modal('show');
		});
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
});