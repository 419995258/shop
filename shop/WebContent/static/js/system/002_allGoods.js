var allGoodsAppmodule = angular.module('allGoodsApp', []);

allGoodsAppmodule.controller('allGoodsController', function($scope, $http,
		$rootScope, $stateParams, $state) {
	ckLogined();
	// //////////////////////////////////////////////////////判断当前页
	$scope.pageSize = 5;// 每页多少条
	$scope.pages = 1;// 总页数
	$scope.maxSize = 5; // 一次可现实多少分页标签，超过maxSize后则以...代替
	$scope.totalItems = 1; // 总共多少条记录
	$scope.currentPage = 1; // 当前页码
	$scope.bigTotalItems = 180;
	$scope.bigCurrentPage = 1;
	var delIdTemp = '';
	var XIdTemp = '';
	var resultVo = {};
	var sub = true;
	var file = false;
	$scope.jkdIndex = ''; // 点击索引
	$scope.xqItems = {}; // 详情弹出框填入的数组

	$scope.goods = {};
	$scope.goodsMini = {};

	$scope.pictures = {};

	$scope.queryText = '';

	$scope.init = function() {
		if ($state.current.name == 'allGoods') {
			$('.menu_btn').removeClass('active');
			$('#allGoods').addClass('active');
		}
		;
		queryGoods();
	};
	$scope.init();

	/** ******************************************初始化添加页面************************************* */
	$scope.openAddNewsPage = function() {
		$("#file-5").fileinput('clear');
		$("#file-6").fileinput('clear');
		$scope.goods = {};
		console.log($scope.goods.url);
		$('#addModal').modal('show');
	};

	/** ******************************************获取上传数据************************************* */
	var fileUrl = '';
	$('#file-5').on('fileuploaded', function(event, data, previewId, index) {
		var response = data.response;
		if (response.success) {
			file = true;
			console.log("file:" + file);
			fileUrl = data.response.fileUrl;

		}
	});
	$('#file-6').on('fileuploaded', function(event, data, previewId, index) {
		var response = data.response;
		if (response.success) {
			file = true;
			console.log("file:" + file);
			fileUrl = data.response.fileUrl;

		}
	});
	/** ******************************************添加商品************************************* */
	$scope.subNew = function() {
		if (sub == true) {

			// $scope.projectChildItems.fileIds = fileids;

			if (verifyIsNull($scope.goods.name)) {
				alert("请填写商品名称");
				return;
			}
			;

			if (verifyIsNull($scope.goods.price)) {
				alert("请填写商品价格");
				return;
			}
			;

			if (file != true) {
				alert("请上传文件");
				return;
			}
			;

			sub = false;
			$scope.goods.url = fileUrl;
			$http({
				method : "PUT",
				url : "../goodsmanageC/addGoods",
				data : $scope.goods,
			}).success(function(data, status) {
				if (data.success) {
					$("#file-5").fileinput('clear');
					$("#file-6").fileinput('clear');
					$scope.goods = {};
					fileUrl = [];
					$('#addModal').modal('hide');
					queryGoods();
					sub = true;
				} else {
					$('#addModal').modal('hide');
					sub = true;
				}
				file = false;
			}).error(function(data, status) {
				alert('保存失败');
			});
		}
	};

	/** ******************************************查询所有信息************************************* */
	function queryGoods() {
		resultVo.pageSize = $scope.pageSize;
		resultVo.currentpage = $scope.currentPage;
		resultVo.type = $scope.type;
		resultVo.queryText = $scope.queryText;

		// 查询所有信息
		$http({
			method : "POST",
			url : "../goodsC/showGoods",
			data : resultVo
		}).success(function(data, status) {
			$scope.goodsMini = data.rows;
			$scope.currentPage = data.pageNum; // 当前页码
			$scope.pageSize = data.pageSize;// 每页多少条
			$scope.pages = data.pages;// 总页数
			$scope.totalItems = data.total;// 总共多少条记录
		});
	}
	;

	$scope.pageChanged = function() {
		queryGoods();
	};

	/** ********************************************删除确认****************************************** */
	$scope.openDelPage = function(index) {
		delIdTemp = index;
		$('#deleteModal').modal('show');
	};

	/** ********************************************确认删除****************************************** */
	$scope.choseD = function() { // 点击按钮
		$scope.jkdIndex = delIdTemp; // 设置选中行索引

		$http({
			method : "PUT",
			url : "../goodsmanageC/deleteGoods",
			data : $scope.goodsMini[$scope.jkdIndex]
		}).success(function(data, status) {
			$scope.goodsMini.splice(delIdTemp, 1);
			delIdTemp = '';
			$('#deleteModal').modal('hide');
		}).error(function(data, status) {
		});

	};
	/** ********************************************查看详情********************************************** */
	$scope.openXPage = function(index) {
		XIdTemp = index;
		$scope.jkdIndex = XIdTemp;
		$http({
			method : "POST",
			url : "../goodsmanageC/queryGoodsData",
			data : $scope.goodsMini[$scope.jkdIndex]
		}).success(function(data, status) {
			$scope.goods = data;
			XIdTemp = '';
			// $scope.pictures = $scope.goods.url;
			$('#fixModal').modal('show');
		});
	};

	/** ******************************************修改商品************************************* */
	$scope.alter = function() {
		if (sub == true) {

			if (verifyIsNull($scope.goods.name)) {
				alert("请填写商品名称");
				return;
			}
			;

			if (verifyIsNull($scope.goods.price)) {
				alert("请填写商品价格");
				return;
			}
			;

			if (file != true) {
				alert("请上传文件");
				return;
			}
			;

			sub = false;
			$scope.goods.url = fileUrl;
			$http({
				method : "PUT",
				url : "../goodsmanageC/updateGoods",
				data : $scope.goods,
			}).success(function(data, status) {
				if (data.success) {
					$("#file-5").fileinput('clear');
					$("#file-6").fileinput('clear');
					$scope.goods = {};
					fileUrl = [];
					$('#fixModal').modal('hide');
					queryGoods();
					sub = true;
				} else {
					$('#fixModal').modal('hide');
					sub = true;
				}
				file = false;
			}).error(function(data, status) {
				alert('保存失败');
			});
		}
	};

	/** ******************************************下架商品************************************* */
	$scope.downGoods = function(index) {
		XIdTemp = index;
		$scope.jkdIndex = XIdTemp;
		$http({
			method : "PUT",
			url : "../goodsmanageC/downGoods",
			data : $scope.goodsMini[$scope.jkdIndex]
		}).success(function(data, status) {
			XIdTemp = '';
			queryGoods();
		}).error(function(data, status) {
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