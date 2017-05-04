var homeAppmodule = angular.module('homeApp', []);

homeAppmodule.controller('homeController', function($scope, $http, $rootScope, $stateParams, $state) {		
	
	////////////////////////////////////////////////////////判断当前页
	
	
	$scope.pageSize = 24;// 每页多少条
	$scope.pages = 1;// 总页数
	$scope.maxSize = 5; // 一次可现实多少分页标签，超过maxSize后则以...代替
	$scope.totalItems = 1; // 总共多少条记录
	$scope.currentPage = 1; // 当前页码
	$scope.bigTotalItems = 180;
	$scope.bigCurrentPage = 1;
	var delIdTemp = '';
	var XIdTemp = '';
	var resultVo = {};
	$scope.jkdIndex = ''; // 点击索引
	$scope.xqItems = {}; // 详情弹出框填入的数组
	$scope.queryText = '';
	$scope.selectItems = [];//商品的类别
	$scope.goodsMini = {};
	
	
	var queryTemp = [{'type':66,'name':'全部商品'},{'type':0,'name':'未分类'},{'type':1,'name':'应季水果'},{'type':2,'name':'干果'}];
	$scope.selectItems = queryTemp;
	$scope.qureyType = queryTemp[0];
	$scope.init = function() {
		if ($state.current.name == 'home') {
			$('.qz_content > a').removeClass('active');
			$('#btn_home').addClass('active');			
		};	
		queryGoods();
	};

	$scope.init();
	
	/** ******************************************查询所有信息************************************* */
	function queryGoods() {
		resultVo.pageSize = $scope.pageSize;
		resultVo.currentpage = $scope.currentPage;
		resultVo.type = $scope.type;
		resultVo.queryText = $scope.queryText;
		resultVo.queryType = $scope.qureyType.type;
		// 查询所有信息
		$http({
			method : "POST",
			url : "../goodsC/showIndexGoods",
			data : resultVo
		}).success(function(data, status) {
			$scope.goodsMini = data.rows;
			$scope.currentPage = data.pageNum; // 当前页码
			$scope.pageSize = data.pageSize;// 每页多少条
			$scope.pages = data.pages;// 总页数
			$scope.totalItems = data.total;// 总共多少条记录
		});
	};

	$scope.pageChanged = function() {
		queryGoods();
	};
	
	/** ******************************************添加商品到购物车************************************* */
	$scope.addCard = function(index){
		ckLogined();
		//$scope.jkdIndex = index;
		//console.log($scope.goodsMini[$scope.jkdIndex]);	
		console.log($scope.goodsMini[index]);

		// 添加商品到购物车
		$http({
			method : "PUT",
			url : "../cardC/insertCard",
			data : $scope.goodsMini[index]
		}).success(function(data, status) {
			if(data.success){
				alert("添加成功");
			}else {
				alert("添加失败");
			}
		});
	};
	
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
	
	
});