var cardAppmodule = angular.module('cardApp', []);

cardAppmodule.controller('cardController', function($scope, $http, $rootScope,
		$stateParams, $state) {

	// //////////////////////////////////////////////////////判断当前页
	$scope.pageSize = 10;// 每页多少条
	$scope.pages = 1;// 总页数
	$scope.maxSize = 10; // 一次可现实多少分页标签，超过maxSize后则以...代替
	$scope.totalItems = 1; // 总共多少条记录
	$scope.currentPage = 1; // 当前页码
	$scope.bigTotalItems = 180;
	$scope.bigCurrentPage = 1;

	$scope.selectItems = [];
	$scope.queryText = '';

	var delIdTemp = '';
	var XIdTemp = '';
	var resultVo = {};
	$scope.jkdIndex = ''; // 点击索引
	$scope.xqItems = {}; // 详情弹出框填入的数组

	$scope.cardItems = {};
	$scope.card = {};
	$scope.tsText = '';

	$scope.init = function() {
		if ($state.current.name == 'card') {
			$('.qz_content > a').removeClass('active');
			$('#btn_card').addClass('active');
		}
		;
		ckLogined();
		queryCard();
	};

	$scope.init();
	/*
	 * $scope.goodsitems = [
	 * {'goodsName':'舍得','goodsUrl':'../static/img/01.jpg','goodsPrice':'99.99','cardNumber':613},
	 * {'goodsName':'感悟亲情','goodsUrl':'../static/img/02.jpg','goodsPrice':'76.00','cardNumber':40},
	 * {'goodsName':'舍得','goodsUrl':'../static/img/01.jpg','goodsPrice':'99.99','cardNumber':63},
	 * {'goodsName':'感悟亲情','goodsUrl':'../static/img/02.jpg','goodsPrice':'76.00','cardNumber':40},
	 * {'goodsName':'舍得','goodsUrl':'../static/img/01.jpg','goodsPrice':'99.99','cardNumber':63},
	 * {'goodsName':'感悟亲情','goodsUrl':'../static/img/02.jpg','goodsPrice':'76.00','cardNumber':40},];
	 */

	/** ******************************************查询所有信息************************************* */
	function queryCard() {
		resultVo.pageSize = $scope.pageSize;
		resultVo.currentpage = $scope.currentPage;
		resultVo.type = $scope.type;
		resultVo.queryText = $scope.queryText;

		// 查询所有信息
		$http({
			method : "POST",
			url : "../cardC/queryCard",
			data : resultVo
		}).success(function(data, status) {
			$scope.cardItems = data.rows;
			$scope.currentPage = data.pageNum; // 当前页码
			$scope.pageSize = data.pageSize;// 每页多少条
			$scope.pages = data.pages;// 总页数
			$scope.totalItems = data.total;// 总共多少条记录
		});
	}
	;

	$scope.pageChanged = function() {
		queryCard();
	};

	/** ******************************************修改购物信息************************************* */
	$scope.openFixPage = function(index) {
		$http({
			method : "PUT",
			url : "../cardC/updateCard",
			data : $scope.cardItems[index]
		}).success(function(data, status) {
			if (data.success) {
				queryCard();
				console.log("修改成功");
			} else {
				alert("修改失败");
			}
		});
	};
	
	/** ******************************************删除购物信息************************************* */
	$scope.openDelPage = function(index) {
		$http({
			method : "PUT",
			url : "../cardC/deleteCard",
			data : $scope.cardItems[index]
		}).success(function(data, status) {
			if (data.success) {
				queryCard();
				console.log("删除成功");
			} else {
				alert("删除失败");
			}
		});
	};
	
	/** ******************************************立即下单************************************* */
	$scope.insertOrder = function(){
		$scope.card.cardList = $scope.cardItems;
		$http({
			method : "PUT",
			url : "../cardC/insertOrder",
			data : $scope.card
		}).success(function(data, status) {
			if (data.success) {
				//queryCard();
				alert("下单成功");
				queryCard();
				console.log("下单成功");
			} else {
				alert(data.message);
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