angular.module('phonecatFilters', ['ngCookies', 'pascalprecht.translate']);

var systemIndexApp=angular.module('systemIndexApp', ['ui.router','ui.bootstrap','phonecatFilters','tm.pagination','ngCookies','pascalprecht.translate','allGoodsApp','allOrderApp','allOrder2App','allUserApp','loginLogApp','aboutApp','newsApp'
]).config(
		function($stateProvider, $urlRouterProvider) {
			$urlRouterProvider.otherwise('/allGoods');
			$stateProvider.state('allGoods', {//全部商品
				url : '/allGoods',
				templateUrl : '002_allGoods.html',
				cache:'false',
				controller: 'allGoodsController'
			})
			.state('allOrder', {//待处理订单
				url : '/allOrder',
				templateUrl : '003_allOrder.html',
				cache:'false',
				controller: 'allOrderController'
			})
			.state('allOrder2', {//待处理订单
				url : '/allOrder2',
				templateUrl : '003_allOrder2.html',
				cache:'false',
				controller: 'allOrder2Controller'
			})
			.state('allUser', {//全部用户
				url : '/allUser',
				templateUrl : '004_allUser.html',
				cache:'false',
				controller: 'allUserController'
			})
			.state('loginLog', {//登录日志
				url : '/loginLog',
				templateUrl : '005_loginLog.html',
				cache:'false',
				controller: 'loginLogController'
			})
			.state('about', {//关于超市
				url : '/about',
				templateUrl : '006_about.html',
				cache:'false',
				controller: 'aboutController'
			})
			.state('news', {//新闻编辑
				url : '/news',
				templateUrl : '007_news.html',
				cache:'false',
				controller: 'newsController'
			});
		})

	.factory('DataInfo', function() {
	return {
		name: "1"
	};
});


systemIndexApp.controller('systemIndexController', function($scope, $http,$cookies, $rootScope,$state,$stateParams) {
//	ckLogined();
//	queryAdd();
	
	
	/** ******************************************验证登录**************************************** */
	/*// 验证登录
	function ckLogined() {
		$http({
			method : "POST",
			url : "../userC/ckLogined",
		}).success(function(data, status) {
			if (!data.success) {
				location.href = "adminLogin.html";
			}
		});
	}*/
	
	/** ******************************************查询访问人数，交易额度**************************************** */
	/*function queryAdd(){
		$http({
			method : "POST",
			url : "../userC/queryAdd",
		}).success(function(data, status) {
			$scope.add = data.result.add;
			console.log("查询成功");
		});
	};*/
});